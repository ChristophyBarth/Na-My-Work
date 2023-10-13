package kodecamp.android.na_my_work.ui.ui.onboarding

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kodecamp.android.na_my_work.ui.model.UserEntity
import kodecamp.android.na_my_work.ui.repo.UserRepository
import kodecamp.android.na_my_work.ui.utils.Event
import kodecamp.android.na_my_work.ui.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _retrieveState = MutableLiveData<Resource<UserEntity>>()
    val retrieveState : LiveData<Resource<UserEntity>> get() = _retrieveState

    private val _forgotPasswordState = MutableLiveData<Event<Resource<UserEntity>>>()
    val forgotPasswordState : LiveData<Event<Resource<UserEntity>>> get() = _forgotPasswordState

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

            val user = userRepository.getUser(email, password)

            if (user != null) {
                _loginState.value = Resource.Success(user)
            } else {
                _loginState.value = Resource.Error("Login failed. Check your email and password.")
            }
        }
    }

    fun retrieveUserById(id: Int){
        viewModelScope.launch {
            _retrieveState.value = Resource.Loading()

            val user = userRepository.getUserByID(id)

            if (user != null) {
                _retrieveState.value = Resource.Success(user)
            } else {
                _retrieveState.value = Resource.Error("User not found.")
            }
        }
    }

    fun retrieveUserByEmail(email: String){
        viewModelScope.launch {
            _forgotPasswordState.value = Event(Resource.Loading())

            val user = userRepository.getUserByEmail(email)

            if (user != null){
                _forgotPasswordState.value = Event(Resource.Success(user))
            } else{
                _forgotPasswordState.value = Event(Resource.Error("No user found with that email."))
            }
        }
    }

}