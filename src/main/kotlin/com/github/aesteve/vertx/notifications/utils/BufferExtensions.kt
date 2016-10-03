package com.github.aesteve.vertx.notifications.utils

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.vertx.core.buffer.Buffer

val mapper = jacksonObjectMapper()

inline fun<reified T : Any> Buffer.get() : T {
    return mapper.readValue(this.toString())
}

inline fun<reified T : Any> Buffer.to(clazz: Class<T>) : T {
    return mapper.readValue(this.toString(), clazz)
}