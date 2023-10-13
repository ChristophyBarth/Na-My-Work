package kodecamp.android.na_my_work.ui.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.net.URI

@Entity(tableName = "userInfo")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val userId: Int = 0,
    val fullName: String = "",
    val email: String = "",
    val password: String = "",
    val phoneNumber: String = "",
    val dateOfBirth: String = "",
    val profilePicture: URI? = null,
    val countryOfResidence: String = "",
    val state: String = "",
    val cityOfResidence: String = "",
    val bio: String = "",
    val twoStepVerification: Boolean = false,
    val rating: Double = 0.0,
    val link: String = "",
    val appNotification: Notification? = null,
    val emailNotification: Notification? = null,
    val experience: List<WorkExperience> = emptyList(),
    val softSkills: List<String> = emptyList(),
    val photos: List<URI> = emptyList(),
)
