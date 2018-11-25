package com.ridvan.apping.view.adapter

import android.databinding.DataBindingUtil
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ridvan.apping.R
import com.ridvan.apping.databinding.ItemListBinding
import com.ridvan.apping.model.Project
import java.util.*

/**
 * Created by ridvanozguvenir on 25.11.2018.
 */
class AppINGAdapter : ListAdapter<Project, AppINGAdapter.ItemViewHolder>(DIFF_CALLBACK) {

    private val projectList: MutableList<Project>

    init {
        projectList = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding: ItemListBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_list,
            parent, false
        )
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val project = getItem(position)

        holder.binding.project = project
        holder.binding.executePendingBindings()
    }

    class ItemViewHolder(val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Project> = object : DiffUtil.ItemCallback<Project>() {
            override fun areItemsTheSame(oldItem: Project, newItem: Project): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Project, newItem: Project): Boolean {
                return oldItem.name.equals(newItem.name)
            }
        }
    }
}
