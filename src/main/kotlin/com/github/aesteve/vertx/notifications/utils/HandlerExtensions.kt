package com.github.aesteve.vertx.notifications.utils

import io.vertx.core.Handler

fun<T> asHandler(handler: (T) -> Unit): Handler<T> {
    return Handler { event -> handler(event) }
}