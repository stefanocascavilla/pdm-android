package com.example.myfriendshouse.dto

data class Friend(
    val id: Int,
    val name: String,
    val surname: String,
    val street: String,
    val city: String,
    val country: String,
    val longitude: Double,
    val latitude: Double
)