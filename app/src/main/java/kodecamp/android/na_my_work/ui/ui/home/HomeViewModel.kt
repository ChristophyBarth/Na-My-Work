package kodecamp.android.na_my_work.ui.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kodecamp.android.na_my_work.ui.model.UserEntity
import kodecamp.android.na_my_work.ui.repo.UserRepository
import kodecamp.android.na_my_work.ui.utils.Event
import kodecamp.android.na_my_work.ui.utils.Object
import kodecamp.android.na_my_work.ui.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _updateState = MutableLiveData<Event<Resource<String>>>()
    val updateState: LiveData<Event<Resource<String>>> get() = _updateState

    private val _deleteState = MutableLiveData<Event<Resource<String>>>()
    val deleteState: LiveData<Event<Resource<String>>> get() = _deleteState

    companion object {
        private const val TAG = "Home VM"
    }

    fun updateUserInformation(user: UserEntity) {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                withContext(Dispatchers.Main) {
                    _updateState.value = Event(Resource.Loading())
                }

                val updatedRowCount = userRepository.updateUser(user)

                withContext(Dispatchers.Main) {
                    if (updatedRowCount > 0) {
                        _updateState.value = Event(Resource.Success("Account successfully updated."))
                    } else {
                        _updateState.value = Event(Resource.Error("Account update failed."))
                    }
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "updateUserInformation: ${e.message}")
        }
    }

    fun deleteUser(user: UserEntity) {
        try {
            if (user.email != Object.user!!.email) {
                _deleteState.value = Event(Resource.Error("Email doesn't match logged in user."))
            } else if (user.password != Object.user!!.password) {
                _deleteState.value = Event(Resource.Error("Password incorrect"))
            } else {
                viewModelScope.launch(Dispatchers.IO) {
                    withContext(Dispatchers.Main) {
                        _deleteState.value = Event(Resource.Loading())
                    }

                    val deletedRowCount = userRepository.deleteUser(Object.user!!)

                    withContext(Dispatchers.Main) {
                        if (deletedRowCount > 0) {
                            _deleteState.value = Event(Resource.Success("Account successfully deleted."))
                        } else {
                            _deleteState.value =
                                Event(Resource.Error("Delete failed, Check your email and password."))
                        }
                    }
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "deleteUser: ${e.message}")
        }
    }
}