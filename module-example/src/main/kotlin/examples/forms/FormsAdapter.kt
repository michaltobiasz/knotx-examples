package examples.forms

import io.knotx.forms.api.FormsAdapterProxy
import io.vertx.core.Context
import io.vertx.core.Vertx
import io.vertx.core.eventbus.MessageConsumer
import io.vertx.core.json.JsonObject
import io.vertx.core.logging.Logger
import io.vertx.core.logging.LoggerFactory
import io.vertx.reactivex.core.AbstractVerticle
import io.vertx.serviceproxy.ServiceBinder

class FormsAdapter : AbstractVerticle() {

    private companion object {
        val LOGGER: Logger = LoggerFactory.getLogger(FormsAdapter::class.java)
    }

    private lateinit var configuration: FormsAdapterOptions

    private lateinit var consumer: MessageConsumer<JsonObject>

    private lateinit var serviceBinder: ServiceBinder


    override fun init(vertx: Vertx, context: Context) {
        super.init(vertx, context)
        this.configuration = FormsAdapterOptions(config())
    }

    override fun start() {
        LOGGER.info("Starting <{}>", this.javaClass.simpleName)

        //register the service proxy on event bus
        serviceBinder = ServiceBinder(getVertx())
        consumer = serviceBinder
            .setAddress(configuration.address)
            .register(FormsAdapterProxy::class.java, FormsAdapterProxy(vertx, configuration))
    }

    override fun stop() {
        serviceBinder.unregister(consumer)
    }
}