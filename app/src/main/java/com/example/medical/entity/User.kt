package com.example.medical.entity

data class User(
    val userID: String?= null,
    val username: String? = null,
    val password: String? = null,
    val email:String? = null,
    val name: String? = null,
    val gender: String? = null,
    val roleID:Int? = null
)