package com.ridvan.apping.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.ridvan.apping.ServiceContext
import com.ridvan.apping.model.Project
import com.ridvan.apping.network.AppINGService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by ridvanozguvenir on 25.11.2018.
 */

class AppINGViewModel : ViewModel() {

    private val projects = MutableLiveData<List<Project>>()
    fun getProjectList(userName: String) {
        val appINGService = ServiceContext.instance!!.retrofit.create(AppINGService::class.java)
        val call = appINGService.getProjectDetails(userName)
        call.enqueue(object : Callback<List<Project>> {
            override fun onFailure(call: Call<List<Project>>, t: Throwable) {
                projects.value = null
            }

            override fun onResponse(call: Call<List<Project>>, response: Response<List<Project>>) {
                projects.value = response.body()
            }
        })
    }

    fun getProjects(): LiveData<List<Project>> {
        return projects
    }
}