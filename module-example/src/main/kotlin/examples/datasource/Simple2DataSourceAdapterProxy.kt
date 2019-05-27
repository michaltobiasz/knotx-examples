package examples.datasource

import io.knotx.databridge.api.DataSourceAdapterRequest
import io.knotx.databridge.api.DataSourceAdapterResponse
import io.knotx.databridge.api.reactivex.AbstractDataSourceAdapterProxy
import io.knotx.dataobjects.ClientResponse
import io.netty.handler.codec.http.HttpResponseStatus
import io.reactivex.Single
import io.vertx.core.json.JsonObject
import io.vertx.core.logging.Logger
import io.vertx.core.logging.LoggerFactory

class Simple2DataSourceAdapterProxy : AbstractDataSourceAdapterProxy() {

    private companion object {
        val LOGGER: Logger = LoggerFactory.getLogger(Simple2DataSourceAdapterProxy::class.java)
    }

    override fun processRequest(message: DataSourceAdapterRequest): Single<DataSourceAdapterResponse> {
        LOGGER.info("Processing request...")
        val clientResponse = ClientResponse()
            .setStatusCode(HttpResponseStatus.OK.code())
            .setBody(JsonObject().put("key2", "testKey2").put("value2", "testValue2").toBuffer())

        val dataSource = DataSourceAdapterResponse()
            .setResponse(clientResponse)

        return Single.just(dataSource)
    }
}