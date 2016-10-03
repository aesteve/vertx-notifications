package com.github.aesteve.vertx.notifications.utils

import io.vertx.core.AsyncResult
import io.vertx.core.Future

fun<T> Future<Void>.completerWithoutResult() = { res: AsyncResult<T> ->
    if (res.succeeded()) {
        this.complete()
    } else {
        this.fail(res.cause())
    }
}