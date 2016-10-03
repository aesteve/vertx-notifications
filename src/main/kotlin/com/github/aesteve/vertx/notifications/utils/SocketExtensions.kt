package com.github.aesteve.vertx.notifications.utils

import com.github.aesteve.vertx.notifications.io.ServerMessages
import io.vertx.core.http.ServerWebSocket

fun ServerWebSocket.write(msg: ServerMessages) {
    this.writeFinalTextFrame(mapper.writeValueAsString(msg))
}