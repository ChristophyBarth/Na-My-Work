package kodecamp.android.na_my_work.ui.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Notification(
    @Json(name = "messages") val messages: Boolean = false,
    @Json(name = "viewedProfile") val viewedProfile: Boolean = false,
    @Json(name = "nothing") val nothing: Boolean = false
)
