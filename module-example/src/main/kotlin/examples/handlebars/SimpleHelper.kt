package examples.handlebars

import com.github.jknack.handlebars.Options
import io.knotx.te.handlebars.CustomHandlebarsHelper

import java.io.IOException

class SimpleHelper : CustomHandlebarsHelper<String> {
    override fun getName(): String {
        return "simpleHelper"
    }

    @Throws(IOException::class)
    override fun apply(context: String, options: Options): Any {
        return "$context simple helper"
    }
}
