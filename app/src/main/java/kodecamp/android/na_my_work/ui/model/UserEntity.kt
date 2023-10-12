package kodecamp.android.na_my_work.ui.model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "userInfo")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val userId: Int = 0,
    val fullName: String = "",
    val email: String = "",
    val password: String = "",
    val phoneNumber: String = "",
    val dateOfBirth: String = "",
    val countryOfResidence: String = "",
    val state: String = "",
    val cityOfResidence: String = "",
    val profilePicture: Int = 0,
    val bio: String = "",
    val twoStepVerification: Boolean = false,
    val rating: Double = 0.0,
    val link: String = "",
    val notifications: String = ""
)


//@Entity(tableName = "userInfo")
//data class UserEntity(
//    @PrimaryKey(autoGenerate = true)
//    val userId: Int,
//    val fullName: String,
//    val email: String,
//    val password: String,
//    val phoneNumber: String,
//    val dateOfBirth: String,
//    val countryOfResidence: String,
//    val state: String,
//    val cityOfResidence: String,
//    val profilePicture: Int,
//    val bio: String,
//    val twoStepVerification: Boolean = false,
//    val rating: Double,
//    val link: String,
//    val notifications: String,
//)


//@Entity(tableName = "userInfo")
//data class UserEntity(
//    @PrimaryKey(autoGenerate = true)
//    val userId: Int,
//    val login: Login,
//    val fullName: String,
//    val email: String,
//    val phoneNumber: Int,
//    val dateOfBirth: String,
//    val countryOfResidence: String,
//    val state: String,
//    val cityOfResidence: String,
//    val profilePicture: URI,
//    val bio: String,
//    val password: String,
//    val twoStepVerification: Boolean,
//    val rating: Double,
//    val link: String,
//    val notifications: Notification,
//    val experience: List<WorkExperience>,
//    val softSkills: List<String>,
//    val photos: List<URI>,
//    val updateUserPassword: UpdateUserPassword
//)
