package de.swirtz.vertx.standalone.webserver.reqhandler

import de.swirtz.vertx.standalone.webserver.verticles.WebVerticle
import io.vertx.core.Handler
import io.vertx.ext.web.RoutingContext
import org.slf4j.LoggerFactory

/**
 * Created on 28.04.2017.
 * @author: simon-wirtz
 */

class SpecialHandler : Handler<RoutingContext> {
    companion object {
        val LOG = LoggerFactory.getLogger(SpecialHandler::class.java)
    }

    override fun handle(routingContext: RoutingContext) {
        val req = routingContext.request()
        val reqNum = WebVerticle.reqCount.incrementAndGet()
        LOG.debug("$reqNum. Got request from ${req.remoteAddress()}: method: ${req.method()}, path: ${req.path()}")
        val response = routingContext.response().putHeader("Content-Type", "application/json")
        response.setStatusCode(200).end("{\"specialcontent\": \"hello world\"}")
    }

}