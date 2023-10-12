package kodecamp.android.na_my_work.ui.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kodecamp.android.na_my_work.ui.repo.UserRepository
import kodecamp.android.na_my_work.ui.repo.UserRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BindingModule {
    @Singleton
    @Binds
    abstract fun provideUserRepoImpl(impl: UserRepositoryImpl): UserRepository
}