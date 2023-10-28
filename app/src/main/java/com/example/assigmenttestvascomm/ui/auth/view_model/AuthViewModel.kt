package com.example.assigmenttestvascomm.ui.auth.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.assigmenttestvascomm.ui.auth.model.remote.request.LoginRequestBody
import com.example.assigmenttestvascomm.ui.auth.model.remote.response.LoginResponse
import com.example.assigmenttestvascomm.ui.auth.repository.remote.AuthRepository
import com.example.assigmenttestvascomm.utils.ResponseState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class AuthViewModel(private var repository: AuthRepository) : ViewModel() {

    // Source data login
    private var _loginResponse = MutableLiveData<ResponseState<LoginResponse.Response>>()
    val loginResponse: LiveData<ResponseState<LoginResponse.Response>> get() = _loginResponse

    fun processLogin(requestBody: LoginRequestBody) {

        viewModelScope.launch {

            _loginResponse.postValue(ResponseState.Loading(null))

            try {
                withContext(Dispatchers.IO) {
                    val response: Response<LoginResponse.Response> =
                        repository.login(requestBody)

                    if (response.isSuccessful) {
                        if (response.body()?.token.toString().isNotEmpty()) {
                            _loginResponse.postValue(ResponseState.Success(response.body()!!))
                            Log.d("AuthViewModel", "processLogin : ${response.body()}")

                        } else {
                            _loginResponse.postValue(ResponseState.Error(response.body()!!.error))
                            Log.d("AuthViewModel", "processLogin : ${response.body()}")

                        }

                    } else {
                        _loginResponse.postValue(ResponseState.Error("Server error"))
                        Log.d("AuthViewModel", "processLogin : ${response.body()}")
                    }

                }

            } catch (ex: Exception) {
                _loginResponse.postValue(ResponseState.Error("Server error"))
                Log.d("AuthViewModel", "processLogin : ${ex.localizedMessage}")
            }

        }
    }

}

class AuthViewModelFactory(private val repository: AuthRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
