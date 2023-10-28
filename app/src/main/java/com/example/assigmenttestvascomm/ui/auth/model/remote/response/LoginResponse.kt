package com.example.assigmenttestvascomm.ui.auth.model.remote.response

class LoginResponse {

    data class Response(
        val token: String,
        val error: String,
    )

}