package kodecamp.android.na_my_work.ui.data

import android.net.Uri
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

object StringJsonAdapter {
    @FromJson
    fun fromJson(string: String) = string.toString()

    @ToJson
    fun toJson(value: String) = value
}

object UriAdapter{
   /*@FromJson
    fun fromJson(string: String): URI? {
        return URI(string.ifEmpty { return null } )
    }*/

    @FromJson
    fun fromJson(string: String): Uri? {
        return Uri.parse(string)
    }

    @ToJson
    fun toJson(uri : Uri?): String {
        return uri?.toString() ?: ""
    }
}