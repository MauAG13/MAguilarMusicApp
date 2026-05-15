package com.example.maguilarmusicapp.network

import com.example.maguilarmusicapp.model.Album
import com.example.maguilarmusicapp.model.AlbumDetail
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface MusicApiService {
    @GET("api/albums")
    suspend fun getAlbums(): List<Album>

    @GET("api/albums/{id}")
    suspend fun getAlbumDetail(@Path("id") id: String): AlbumDetail
}

object RetrofitInstance {
    private const val BASE_URL = "https://musicapi.pjasoft.com/"

    val api: MusicApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MusicApiService::class.java)
    }
}
