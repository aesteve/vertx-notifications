package com.github.aesteve.vertx.notifications.utils

import io.vertx.core.Handler
import io.vertx.core.http.HttpServer
import io.vertx.core.http.HttpServerRequest
import io.vertx.core.http.ServerWebSocket

var HttpServer.requestHandler: Handler<HttpServerRequest>
    get() =  this.requestHandler()
    set(value) {
        this.requestHandler(value)
    }

var HttpServer.websocketHandler: Handler<ServerWebSocket>
    get() =  this.websocketHandler()
    set(value) {
        this.websocketHandler(value)
    }
