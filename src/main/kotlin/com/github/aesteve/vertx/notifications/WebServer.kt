package com.github.aesteve.vertx.notifications

import com.github.aesteve.vertx.notifications.api.ApiRouter
import com.github.aesteve.vertx.notifications.utils.completerWithoutResult
import com.github.aesteve.vertx.notifications.utils.requestHandler
import com.github.aesteve.vertx.notifications.utils.websocketHandler
import com.github.aesteve.vertx.notifications.ws.SocketListener
import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.core.http.HttpServer
import io.vertx.core.http.HttpServerOptions

val PORT = 8181
val HOST = "localhost"

class WebServer : AbstractVerticle() {

    val options by lazy {
        HttpServerOptions()
            .setHost(HOST)
            .setPort(PORT)
    }
    val server: HttpServer by lazy {
        vertx.createHttpServer(options)
    }

    override fun start(future: Future<Void>) {
        server.websocketHandler = SocketListener(vertx)
        server.requestHandler = ApiRouter(vertx)
        server.listen(PORT, future.completerWithoutResult())
    }

    override fun stop(future: Future<Void>) {
        server.close(future.completer())
    }

}