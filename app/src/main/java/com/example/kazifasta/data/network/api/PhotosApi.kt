package com.example.kazifasta.data.network.api

import com.example.kazifasta.data.model.Photo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface PhotosApi {
    @GET("photos")
    suspend fun getPhotos(): List<Photo>
}

val photosRetrofit: Retrofit = Retrofit.Builder()
    .baseUrl("https://jsonplaceholder.typicode.com")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val photosApi: PhotosApi = photosRetrofit.create(PhotosApi::class.java)