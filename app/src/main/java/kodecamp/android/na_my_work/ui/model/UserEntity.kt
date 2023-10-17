package kodecamp.android.na_my_work.ui.model

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userInfo")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val userId: Int = 0,
    var fullName: String = "",
    var email: String = "",
    var password: String = "",
    var phoneNumber: String = "",
    var dateOfBirth: String = "",
    var profilePicture: Uri? = null,
    var countryOfResidence: String = "",
    var state: String = "",
    var cityOfResidence: String = "",
    var title: String = "",
    var bio: String = "",
    var twoStepVerification: Boolean = false,
    val rating: Double = 0.0,
    val ratingCount: Int = 0,
    var link: String = "",
    var appNotification: Notification? = null,
    var emailNotification: Notification? = null,
    var profileSetupStatus: String? = "Incomplete",
    val category: String? = "",
    var experience: MutableList<WorkExperience> = mutableListOf(),
    var softSkills: MutableList<String> = mutableListOf(),
    var photos: MutableList<Uri> = mutableListOf(),
)
