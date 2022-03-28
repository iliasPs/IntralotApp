package com.ip.intralotapp.presentation.repos_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import com.ip.intralotapp.R
import com.ip.intralotapp.databinding.RepoItemBinding
import com.ip.intralotapp.domain.models.GithubRepo

class ReposListAdapter(itemClickListener: ItemClickListener) :
    RecyclerView.Adapter<ReposListAdapter.ReposViewHolder>() {

    private val itemsList = ArrayList<GithubRepo>()
    private val itemClickListener = itemClickListener


    inner class ReposViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = RepoItemBinding.bind(view)
        val repoNameTv = binding.repoName
        val ownerTv = binding.owner
        val forksTv = binding.forks
        val watchers = binding.watchers

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.repo_item, parent, false)
        return ReposViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReposViewHolder, position: Int) {

        val repo = itemsList[position]
        holder.repoNameTv.text = repo.name
        holder.ownerTv.text = repo.owner?.login
        holder.forksTv.text = repo.forksCount.toString()
        holder.watchers.text = repo.watchersCount.toString()

        // passing the repo object with the click
        // to be provided to the details fragment
        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("repo", GsonBuilder().create().toJson(repo))
            itemClickListener.onItemClicked(bundle)
        }

    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    fun setData(characterList: ArrayList<GithubRepo>) {
        this.itemsList.addAll(characterList)
        notifyDataSetChanged()
    }
}