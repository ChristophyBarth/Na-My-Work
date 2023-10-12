package kodecamp.android.na_my_work.ui.repo

import kodecamp.android.na_my_work.ui.model.UserEntity
interface UserRepository {
    suspend fun getUser(email: String, password: String): UserEntity?

    suspend fun saveUser(userEntity: UserEntity)

    suspend fun getUserByEmail(email: String, password: String): UserEntity?

    suspend fun updateUser(user: UserEntity)
}