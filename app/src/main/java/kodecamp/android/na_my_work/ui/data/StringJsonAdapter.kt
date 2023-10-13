package kodecamp.android.na_my_work.ui.data

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.net.URI

object StringJsonAdapter {
    @FromJson
    fun fromJson(string: String) = string.toString()

    @ToJson
    fun toJson(value: String) = value
}

object URIAdapter{
    @FromJson
    fun fromJson(string: String): URI? {
        return URI(string.ifEmpty { return null } )
    }

    @ToJson
    fun toJson(uri : URI?): String {
        return uri?.toString() ?: ""
    }
}