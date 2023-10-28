package com.example.assigmenttestvascomm.ui.auth.repository.remote

import com.example.assigmenttestvascomm.ui.auth.model.remote.request.LoginRequestBody
import com.example.assigmenttestvascomm.ui.auth.model.remote.response.LoginResponse
import com.example.assigmenttestvascomm.utils.RetrofitInstance
import retrofit2.Response

class AuthRepository {

    suspend fun login(request: LoginRequestBody): Response<LoginResponse.Response> {
        return RetrofitInstance.authApi.login(request)
    }

}