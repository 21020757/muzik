package com.example.muzik.data_model.retrofit_model.request

data class RegisterRequest(
    var name: String,
    var email: String,
    var phoneNumber: Int,
    var username: String,
    var password: String
)
