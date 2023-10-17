package kodecamp.android.na_my_work.ui.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder

class GsonUtil {
    private var gson : Gson? = null
    fun getGsonParser(): Gson {
        if (gson == null) {
            val builder = GsonBuilder()
            gson = builder.create()
        }

        return gson!!
    }

}