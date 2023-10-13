package kodecamp.android.na_my_work.ui.utils

import kodecamp.android.na_my_work.ui.model.UserEntity

object Object {
    var user : UserEntity? = null
    var primaryKey : Int? = null

    const val USER_PREF_NAME = "userPref"
    const val USER_PRIMARY_KEY = "primaryKey"
    const val COMPLETED_ONBOARDING_KEY = "onboardingCompleted"
}