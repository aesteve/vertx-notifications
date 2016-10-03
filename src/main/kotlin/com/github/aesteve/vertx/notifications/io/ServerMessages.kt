package com.github.aesteve.vertx.notifications.io

enum class ServerMessages(
        val msg: String,
        val status: Int,
        var explanation: String) {
    INVALID_MSG_SENT("Invalid Message Sent", 400, "A valid message is a valid subscription or an unsubscription");
}