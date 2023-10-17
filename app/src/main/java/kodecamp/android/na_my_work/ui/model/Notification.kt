package kodecamp.android.na_my_work.ui.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Notification(
    @Json(name = "messages") var messages: Boolean = false,
    @Json(name = "viewedProfile") var viewedProfile: Boolean = false,
    @Json(name = "nothing") var nothing: Boolean = false
)
