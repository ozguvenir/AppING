package com.ridvan.apping.network

import com.ridvan.apping.model.Project
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by ridvanozguvenir on 25.11.2018.
 */
interface AppINGService {
    @GET("users/{user}/repos")
    fun getProjectDetails(@Path("user") user: String): Call<List<Project>>
}