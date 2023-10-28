package com.example.assigmenttestvascomm.ui.auth.model.remote.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LoginRequestBody {

    @SerializedName("email")
    @Expose
    lateinit var email:String

    @SerializedName("password")
    @Expose
    lateinit var password:String

}