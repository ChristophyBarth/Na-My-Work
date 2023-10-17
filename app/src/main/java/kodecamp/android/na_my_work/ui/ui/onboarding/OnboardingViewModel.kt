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
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    companion object {
        private const val TAG = "Onboarding VM"
    }

    private val _saveState = MutableLiveData<Resource<String>>()
    val saveState: LiveData<Resource<String>> get() = _saveState

    private val _updateState = MutableLiveData<Resource<String>>()
    val updateState: LiveData<Resource<String>> get() = _updateState

    private val _retrieveState = MutableLiveData<Resource<UserEntity>>()
    val retrieveState: LiveData<Resource<UserEntity>> get() = _retrieveState

    private val _forgotPasswordState = MutableLiveData<Event<Resource<UserEntity>>>()
    val forgotPasswordState: LiveData<Event<Resource<UserEntity>>> get() = _forgotPasswordState

    private val _loginState = MutableLiveData<Resource<UserEntity>>()
    val loginState: LiveData<Resource<UserEntity>> get() = _loginState

    fun saveUserInformation(user: UserEntity) {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                withContext(Dispatchers.Main) {
                    _saveState.value = Resource.Loading()
                }

                val updatedRowCount = userRepository.saveUser(user)

                withContext(Dispatchers.Main) {
                    if (updatedRowCount > -1) {
                        _saveState.value = Resource.Success("User registration successful.")
                    } else {
                        _saveState.value = Resource.Error("User registration failed.")
                    }
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "saveUserInformation: ${e.message}")
        }
    }


    fun updateUserInformation(user: UserEntity) {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                withContext(Dispatchers.Main) {
                    _updateState.value = Resource.Loading()
                }

                val updatedRowCount = userRepository.updateUser(user)

                withContext(Dispatchers.Main) {
                    if (updatedRowCount > 0) {
                        _updateState.value = Resource.Success("Account successfully updated.")
                    } else {
                        _updateState.value = Resource.Error("Account update failed.")
                    }
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "updateUserInformation: ${e.message}")
        }
    }

    fun login(email: String, password: String) {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                withContext(Dispatchers.Main) {
                    _loginState.value = Resource.Loading()
                }

                val user = userRepository.getUser(email, password)

                withContext(Dispatchers.Main) {
                    if (user != null) {
                        _loginState.value = Resource.Success(user)
                    } else {
                        _loginState.value =
                            Resource.Error("Login failed. Check your email and password.")
                    }
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "login: ${e.message}")
        }
    }

    fun retrieveUserById(id: Int) {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                withContext(Dispatchers.Main) {
                    _retrieveState.value = Resource.Loading()
                }

                val user = userRepository.getUserByID(id)

                withContext(Dispatchers.Main) {
                    if (user != null) {
                        _retrieveState.value = Resource.Success(user)
                    } else {
                        _retrieveState.value = Resource.Error("User not found.")
                    }
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "retrieveUserById: ${e.message}")
        }
    }

    fun retrieveUserByEmail(email: String) {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                withContext(Dispatchers.Main) {
                    _forgotPasswordState.value = Event(Resource.Loading())
                }

                val user = userRepository.getUserByEmail(email)

                withContext(Dispatchers.Main) {
                    if (user != null) {
                        _forgotPasswordState.value = Event(Resource.Success(user))
                    } else {
                        _forgotPasswordState.value =
                            Event(Resource.Error("No user found with that email."))
                    }
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "retrieveUserByEmail: ${e.message}")
        }
    }
}