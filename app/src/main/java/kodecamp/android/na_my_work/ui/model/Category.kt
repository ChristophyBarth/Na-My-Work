package kodecamp.android.na_my_work.ui.model

import android.graphics.drawable.Drawable

data class Category(
    val image: Int,
    val name: String
)

data class User(
    val name: String,
    val occupation: String,
    val location: String,
    val description: String,
    val image: Int
)
