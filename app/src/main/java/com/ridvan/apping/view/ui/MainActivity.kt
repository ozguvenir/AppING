package com.ridvan.apping.view.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.ridvan.apping.R
import com.ridvan.apping.databinding.ActivityMainBinding
import com.ridvan.apping.view.adapter.AppINGAdapter
import com.ridvan.apping.viewmodel.AppINGViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: AppINGViewModel
    private var adapter: AppINGAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(AppINGViewModel::class.java)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)

        adapter = AppINGAdapter()
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        layoutManager.scrollToPosition(0)
        binding.myRecyclerView.layoutManager = layoutManager
        binding.myRecyclerView.setHasFixedSize(true)
        binding.myRecyclerView.adapter = adapter

        viewModel.getProjects().observe(this, Observer { projects -> adapter!!.submitList(projects) })

        if (savedInstanceState == null) {
            viewModel.getProjectList("ozguvenir")
        }
    }
}
