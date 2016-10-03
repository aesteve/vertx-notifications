package com.github.aesteve.vertx.notifications.ws

import com.github.aesteve.vertx.notifications.dto.Subscription
import com.github.aesteve.vertx.notifications.utils.add
import com.github.aesteve.vertx.notifications.utils.asHandler
import com.github.aesteve.vertx.notifications.utils.remove
import io.vertx.core.Handler
import io.vertx.core.Vertx
import io.vertx.core.http.ServerWebSocket
import io.vertx.core.json.JsonObject
import java.util.*

class Broker(
    val vertx: Vertx,
    val subscriptions: MutableMap<String, Set<ServerWebSocket>> = HashMap(),
    val timers: MutableMap<String, Pair<Long, Long>> = HashMap(), // (period, timerId)
    val responses: MutableMap<String, JsonObject> = HashMap()
) {

    fun newSubscriber(subscription: Subscription, socket: ServerWebSocket) {
        subscriptions.add(subscription.id, socket)
        val timerInfo = timers[subscription.id]
        if (timerInfo == null) {
            val timerId = vertx.setTimer(subscription.period, fetchSubscription(subscription))
            timers.put(subscription.id, Pair(timerId, subscription.period))
        } else if (timerInfo.second > subscription.period) {
            timers.put(subscription.id, Pair(timerInfo.first, subscription.period))
        } // else do nothing
    }

    fun unsubscribe(subscription: Subscription, socket: ServerWebSocket) {
        subscriptions.remove(subscription.id, socket)
        val timerInfo = timers[subscription.id]
        if (timerInfo != null) {
            // FIXME : needs a reference to other subscriptions (to get the another fetch period)
        }
    }

    private fun fetchSubscription(subscription: Subscription): Handler<Long> {
        return asHandler {
            vertx.createHttpClient().get(subscription.port, subscription.host, subscription.path).end()
        }
    }

}