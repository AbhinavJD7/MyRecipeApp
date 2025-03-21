package com.example.myrecipeapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET


private val retrofit = Retrofit.Builder().baseUrl("www.themealdb.com/api/json/v1/1/") // we are building our retrofit URL
    .addConverterFactory(GsonConverterFactory.create())
    .build()  // These lines are building connection to this base URL

val recipeServie = retrofit.create(ApiService::class.java)

interface ApiService{
    @GET("categories.php")
    suspend fun getCategories():CategoriesResponse
}