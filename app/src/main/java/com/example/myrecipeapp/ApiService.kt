package com.example.myrecipeapp

import retrofit2.http.GET

interface ApiService{
    @GET("categories.php")
    suspend fun getCategories():CategoriesResponse
}