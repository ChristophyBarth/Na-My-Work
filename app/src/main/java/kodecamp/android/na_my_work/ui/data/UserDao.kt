package kodecamp.android.na_my_work.ui.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kodecamp.android.na_my_work.ui.model.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUser(userEntity: UserEntity) : Long
    @Delete
    suspend fun deleteUser(userEntity: UserEntity) : Int
    @Update
    suspend fun updateUser(userEntity: UserEntity) : Int

    @Query("SELECT * FROM userInfo WHERE userId = :userId")
    suspend fun getUserById(userId: Int): UserEntity?
    @Query("SELECT * FROM userInfo WHERE email = :email")
    suspend fun getUserByEmail(email: String): UserEntity?
}
