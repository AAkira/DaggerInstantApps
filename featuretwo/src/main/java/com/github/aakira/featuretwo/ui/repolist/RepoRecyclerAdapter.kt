package com.github.aakira.featuretwo.ui.repolist

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.github.aakira.daggerinstantapps.data.model.GithubRepo
import com.github.aakira.featuretwo.R

class RepoRecyclerAdapter(private val data: List<GithubRepo>, val clickListener: (GithubRepo) -> Unit) :
        RecyclerView.Adapter<RepoRecyclerAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_repo_list, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]

        holder.titleText.text = item.name
        holder.itemView.setOnClickListener { clickListener.invoke(item) }
    }

    override fun getItemCount() = data.size

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var titleText: TextView = v.findViewById<View>(R.id.titleText) as TextView
    }
}