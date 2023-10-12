package kodecamp.android.na_my_work.ui.data

import androidx.room.Database
import androidx.room.RoomDatabase
import kodecamp.android.na_my_work.ui.model.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false )
abstract class UserDataBase: RoomDatabase() {
    abstract fun userDao(): UserDao
}