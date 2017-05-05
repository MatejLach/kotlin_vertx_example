package de.swirtz.vertx.standalone.webserver.reqhandler

import de.swirtz.vertx.standalone.webserver.JSON_CONT_TYPE
import de.swirtz.vertx.standalone.webserver.verticles.WebVerticleRequestCounter
import io.vertx.core.Handler
import io.vertx.ext.web.RoutingContext
import org.slf4j.LoggerFactory

/**
 * Created on 28.04.2017.
 * @author: simon-wirtz
 */

class SpecialHandler : Handler<RoutingContext> {
    private companion object {
        val LOG = LoggerFactory.getLogger(SpecialHandler::class.java)
    }

    override fun handle(routingContext: RoutingContext) {
        val req = routingContext.request()
        val reqNum = WebVerticleRequestCounter.incrementAndGetCounter()
        LOG.debug("$reqNum. Got request from ${req.remoteAddress()}: method: ${req.method()}, path: ${req.path()}")
        val response = routingContext.response().putHeader("Content-Type", JSON_CONT_TYPE)
        response.setStatusCode(200).end("{\"specialcontent\": \"hello world\"}")
    }

}