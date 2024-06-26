package com.example.user.api

import com.example.user.model.Users
import retrofit2.Response
import retrofit2.http.GET


interface UserService {

    @GET("/users")
    suspend fun getUser():Response<Users>
}