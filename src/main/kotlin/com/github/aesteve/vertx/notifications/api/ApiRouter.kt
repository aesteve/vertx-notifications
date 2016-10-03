package com.github.aesteve.vertx.notifications.api

import io.vertx.core.Handler
import io.vertx.core.Vertx
import io.vertx.core.http.HttpHeaders.CONTENT_TYPE
import io.vertx.core.http.HttpServerRequest
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext

class ApiRouter(vertx: Vertx) : Handler<HttpServerRequest> {

    val router by lazy {
        val router = Router.router(vertx)
        router["/api/*"].handler(this.addContentType)
        router["/api/stats"].handler(this.sendStats)
        router
    }

    override fun handle(event: HttpServerRequest?) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    val addContentType = { ctx: RoutingContext ->
        ctx.response().putHeader(CONTENT_TYPE, "application/json")
        ctx.next()
    }

    val sendStats = { ctx: RoutingContext ->
        ctx.response().end("NIY")
    }

}