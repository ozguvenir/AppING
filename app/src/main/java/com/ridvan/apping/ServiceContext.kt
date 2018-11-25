package com.ridvan.apping

import retrofit2.Retrofit

/**
 * Created by ridvanozguvenir on 25.11.2018.
 */
class ServiceContext private constructor(val retrofit: Retrofit) {
    companion object {
        var instance: ServiceContext? = null

        fun initialize(retrofit: Retrofit) {
            if (instance != null) throw IllegalStateException("ServiceContext already initialized")
            ServiceContext.instance = ServiceContext(retrofit)
        }
    }
}
