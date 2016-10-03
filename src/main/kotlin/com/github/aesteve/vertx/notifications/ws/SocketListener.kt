package com.github.aesteve.vertx.notifications.ws

import com.github.aesteve.vertx.notifications.dto.Subscription
import com.github.aesteve.vertx.notifications.io.ServerMessages.INVALID_MSG_SENT
import com.github.aesteve.vertx.notifications.utils.asHandler
import com.github.aesteve.vertx.notifications.utils.write
import io.vertx.core.Handler
import io.vertx.core.Vertx
import io.vertx.core.buffer.Buffer
import io.vertx.core.http.ServerWebSocket


class SocketListener(vertx: Vertx) : Handler<ServerWebSocket> {

    override fun handle(socket: ServerWebSocket) {
        socket.handler(readMsg(socket))
        socket.endHandler({ v -> clientDisconnected(socket) })
    }

    fun readMsg(socket: ServerWebSocket): Handler<Buffer> {
        return asHandler { buffer: Buffer ->
            try {
                val subscription = buffer.to(Subscription::class)
            } catch (e: Exception) {
                socket.write(INVALID_MSG_SENT)
            }
        }
    }

    fun clientDisconnected(socket: ServerWebSocket) {

    }

}