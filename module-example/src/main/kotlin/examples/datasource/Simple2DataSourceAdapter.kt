package examples.datasource

import io.knotx.databridge.api.DataSourceAdapterProxy
import io.vertx.core.AbstractVerticle
import io.vertx.core.Context
import io.vertx.core.Vertx
import io.vertx.core.eventbus.MessageConsumer
import io.vertx.core.json.JsonObject
import io.vertx.core.logging.Logger
import io.vertx.core.logging.LoggerFactory
import io.vertx.serviceproxy.ServiceBinder

class Simple2DataSourceAdapter : AbstractVerticle() {

    private companion object {
        val LOGGER: Logger = LoggerFactory.getLogger(Simple2DataSourceAdapter::class.java)
    }

    private lateinit var consumer: MessageConsumer<JsonObject>

    private lateinit var serviceBinder: ServiceBinder

    private lateinit var options: SimpleDataSourceOptions

    override fun init(vertx: Vertx, context: Context) {
        super.init(vertx, context)
        this.options = SimpleDataSourceOptions(config())
    }

    override fun start() {
        LOGGER.info("Starting <{}>", this.javaClass.simpleName)

        //register the service proxy on event bus
        serviceBinder = ServiceBinder(getVertx())
        consumer = serviceBinder
            .setAddress(options.address)
            .register(DataSourceAdapterProxy::class.java, Simple2DataSourceAdapterProxy())
    }

    override fun stop() {
        serviceBinder.unregister(consumer)
    }

}