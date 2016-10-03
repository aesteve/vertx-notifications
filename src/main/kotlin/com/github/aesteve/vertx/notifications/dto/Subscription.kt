package com.github.aesteve.vertx.notifications.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import com.github.aesteve.vertx.notifications.utils.join
import com.github.aesteve.vertx.notifications.utils.sha256
import java.util.*

class RequestParam(val value: String) {
    constructor(boolValue: Boolean) :
        this(boolValue.toString())
    constructor(number: Number) :
        this(number.toString())
    override fun toString(): String = value.toString()
}

data class Subscription(
    val period: Long,
    val host: String,
    val port: Int = 80,
    val path: String,
    val params: Map<String, String> = HashMap(),
    val headers: Map<String, RequestParam> = HashMap()
) {

    val id by lazy {
        key.sha256
    }

    @get:JsonIgnore
    val key by lazy {
        "${this.host}|${this.port}|${params.join(":",";")}|${headers.join(":", ";")}"
    }

}