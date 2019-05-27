package examples.forms

import io.knotx.dataobjects.ClientResponse
import io.knotx.forms.api.FormsAdapterRequest
import io.knotx.forms.api.FormsAdapterResponse
import io.knotx.forms.api.reactivex.AbstractFormsAdapterProxy
import io.reactivex.Single
import io.vertx.core.buffer.Buffer
import io.vertx.core.json.JsonObject
import io.vertx.reactivex.core.Vertx

class FormsAdapterProxy(vertx: Vertx, options: FormsAdapterOptions) : AbstractFormsAdapterProxy() {


    override fun processRequest(message: FormsAdapterRequest): Single<FormsAdapterResponse> {
        val formsAdapterResponse = FormsAdapterResponse()
        val email = message.request.formAttributes.get("email")
        val clientResponse = ClientResponse()
        clientResponse.statusCode = 200
        if(email.startsWith("john.doe")) {
            formsAdapterResponse.signal = "success"
        } else {
            formsAdapterResponse.signal = "error"
            clientResponse.body = JsonObject().put("validationErrors", "Error message").toBuffer()
        }
        formsAdapterResponse.response = clientResponse
        return Single.just(formsAdapterResponse)
    }
}