package com.example.assigmenttestvascomm.source.remote.auth

import com.example.assigmenttestvascomm.ui.auth.model.remote.request.LoginRequestBody
import com.example.assigmenttestvascomm.ui.auth.model.remote.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {


    @POST("login")
    suspend fun login(
        @Body request: LoginRequestBody,
    ): Response<LoginResponse.Response>

}