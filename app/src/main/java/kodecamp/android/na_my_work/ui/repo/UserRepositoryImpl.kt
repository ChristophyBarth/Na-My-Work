package kodecamp.android.na_my_work.ui.repo

import kodecamp.android.na_my_work.ui.data.UserDao
import kodecamp.android.na_my_work.ui.model.UserEntity
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {
    override suspend fun getUser(email: String, password: String): UserEntity? {
        val user = userDao.getUserByEmail(email)
        return if (user != null && user.password == password) user else null
    }

    override suspend fun getUserByEmail(email: String): UserEntity? {
        return userDao.getUserByEmail(email)
    }

    override suspend fun getUserByID(id: Int): UserEntity? {
        return userDao.getUserById(id)
    }

    override suspend fun saveUser(userEntity: UserEntity): Long {
        return userDao.saveUser(userEntity)
    }

    override suspend fun updateUser(user: UserEntity): Int {
        return userDao.updateUser(user)
    }

    override suspend fun deleteUser(user: UserEntity): Int {
        return userDao.deleteUser(user)
    }

}
