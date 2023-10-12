package kodecamp.android.na_my_work.ui.ui.onboarding

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kodecamp.android.na_my_work.ui.model.UserEntity
import kodecamp.android.na_my_work.ui.repo.UserRepository
import kodecamp.android.na_my_work.ui.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _loginState = MutableLiveData<Resource<UserEntity>>()
    val loginState: LiveData<Resource<UserEntity>> get() = _loginState
    fun saveUserInformation(user: UserEntity) {
        try {
            viewModelScope.launch(Dispatchers.IO){
                userRepository.saveUser(user)
                Log.d("MYTAG", "$user successfully saved")
            }
        } catch (e: Exception) {
            Log.e("MYTAG", "$user failed to save")
        }

    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginState.value = Resource.Loading()

            val user = userRepository.getUserByEmail(email, password)

            if (user != null) {
                _loginState.value = Resource.Success(user)
            } else {
                _loginState.value = Resource.Error("Login failed. Check your email and password.")
            }
        }
    }

}