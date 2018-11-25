package com.ridvan.apping

import android.app.Application
import com.ridvan.apping.view.constant.AppINGConstants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by ridvanozguvenir on 25.11.2018.
 */

class AppINGApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val client = OkHttpClient().newBuilder()
            .writeTimeout(AppINGConstants.TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(AppINGConstants.TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(AppINGConstants.TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            })
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(AppINGConstants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        ServiceContext.initialize(retrofit)
    }
}