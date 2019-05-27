package examples.knot

import io.knotx.dataobjects.KnotContext
import io.knotx.knot.AbstractKnotProxy
import io.reactivex.Single
import io.vertx.core.logging.Logger
import io.vertx.core.logging.LoggerFactory

class SimpleKnotProxy : AbstractKnotProxy() {

    private companion object {
        val LOGGER: Logger = LoggerFactory.getLogger(SimpleKnotProxy::class.java)
        const val SERVICE_NAME: String = "simpleknot"
    }

    override fun shouldProcess(knots: MutableSet<String>): Boolean {
        return knots.contains(SERVICE_NAME)
    }

    override fun processRequest(knotContext: KnotContext): io.reactivex.Single<KnotContext> {
        LOGGER.info("Processing request...")
        val headers = knotContext.clientResponse.headers
        headers.add("X-Simple-Knot", "test")
        knotContext.clientResponse.headers = headers
        return Single.just(knotContext)
    }

    override fun processError(knotContext: KnotContext, error: Throwable): KnotContext {
        return knotContext
    }
}