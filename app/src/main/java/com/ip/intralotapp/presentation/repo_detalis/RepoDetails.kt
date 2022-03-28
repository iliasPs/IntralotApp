package com.ip.intralotapp.presentation.repo_detalis

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.gson.GsonBuilder
import com.ip.intralotapp.databinding.FragmentRepoDetailsBinding
import com.ip.intralotapp.domain.models.GithubRepo
import com.ip.intralotapp.presentation.MainActivity


class RepoDetails : Fragment() {

    private lateinit var binding: FragmentRepoDetailsBinding
    private lateinit var title: TextView
    private lateinit var description: TextView
    private lateinit var owner: TextView
    private lateinit var license: TextView
    private lateinit var permissionPull: TextView
    private lateinit var permissionPush: TextView
    private lateinit var permissisonMaintain: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainActivity?)!!.showUpButton()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRepoDetailsBinding.inflate(inflater, container, false)

        title = binding.title
        description = binding.description
        owner = binding.owner
        license = binding.license
        permissionPull = binding.permissionPull
        permissionPush = binding.permissionPush
        permissisonMaintain = binding.permissionMaintain

        if (arguments != null) {
            val gson = GsonBuilder().create()
            val repoString = requireArguments().getString("repo")

            val githubRepo = gson.fromJson(repoString, GithubRepo::class.java)
            inflateUi(githubRepo)
        }

        return binding.root
    }

    private fun inflateUi(githubRepo: GithubRepo?) {

        title.text = githubRepo?.name
        description.text = githubRepo?.description
        owner.text = githubRepo?.owner?.login
        license.text = githubRepo?.license?.name
        permissionPull.text = githubRepo?.permissions?.pull.toString()
        permissionPush.text = githubRepo?.permissions?.push.toString()
        permissisonMaintain.text = githubRepo?.permissions?.maintain.toString()

    }

}