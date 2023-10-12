package kodecamp.android.na_my_work.ui.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kodecamp.android.na_my_work.ui.data.UserDao
import kodecamp.android.na_my_work.ui.data.UserDataBase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun getDao(db: UserDataBase): UserDao = db.userDao()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context) =
        Room.databaseBuilder(
            appContext,
            UserDataBase::class.java,
            "USER_DATABASE"
        ).build()

}