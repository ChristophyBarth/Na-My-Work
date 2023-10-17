package kodecamp.android.na_my_work.ui.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WorkExperience(
    @Json(name = "companyName") val companyName: String = "",
    @Json(name = "position") val position: String = "",
    @Json(name = "startDate") val startDate: Long = 0,
    @Json(name = "endDate") val endDate: Long = 0,
    @Json(name = "tillDate") val tillDate: Boolean = false,
    @Json(name = "workDescription") val workDescription: String = "",
)