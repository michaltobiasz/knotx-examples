package examples.knot

import io.knotx.proxy.KnotProxy
import io.vertx.core.Context
import io.vertx.core.Vertx
import io.vertx.core.eventbus.MessageConsumer
import io.vertx.core.json.JsonObject
import io.vertx.core.logging.Logger
import io.vertx.core.logging.LoggerFactory
import io.vertx.reactivex.core.AbstractVerticle
import io.vertx.serviceproxy.ServiceBinder

class SimpleKnot : AbstractVerticle() {

    private companion object {
        val LOGGER: Logger = LoggerFactory.getLogger(SimpleKnot::class.java)
    }

    private lateinit var consumer: MessageConsumer<JsonObject>

    private lateinit var serviceBinder: ServiceBinder

    private lateinit var options: SimpleKnotOptions

    override fun init(vertx: Vertx, context: Context) {
        super.init(vertx, context)
        this.options = SimpleKnotOptions(config())
    }

    override fun start() {
        LOGGER.info("Starting <{}>", this.javaClass.simpleName)

        //register the service proxy on event bus
        serviceBinder = ServiceBinder(getVertx())
        consumer = serviceBinder
            .setAddress(options.address)
            .register(KnotProxy::class.java, SimpleKnotProxy())
    }

    override fun stop() {
        serviceBinder.unregister(consumer)
    }
}