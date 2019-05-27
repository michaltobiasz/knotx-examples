package examples.datasource

import io.vertx.codegen.annotations.DataObject
import io.vertx.core.json.JsonObject

@DataObject(generateConverter = true, publicConverter = false)
class SimpleDataSourceOptions {

    lateinit var address : String

    /**
     * Create an settings from JSON
     *
     * @param json the JSON
     */
    constructor(json: JsonObject) {
        SimpleDataSourceOptionsConverter.fromJson(json, this)
    }

    /**
     * Convert to JSON
     *
     * @return the JSON
     */
    fun toJson(): JsonObject {
        val json = JsonObject()
        SimpleDataSourceOptionsConverter.toJson(this, json)
        return json
    }

}