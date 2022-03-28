package com.ip.intralotapp.presentation.repos_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ip.intralotapp.databinding.FragmentReposListBinding
import com.ip.intralotapp.domain.models.GithubRepo
import com.ip.intralotapp.presentation.MainActivity
import com.ip.intralotapp.presentation.repo_detalis.RepoDetails
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


val TAG = ReposListFragment::class.java.simpleName

@AndroidEntryPoint
class ReposListFragment : Fragment(), ItemClickListener,
    FragmentManager.OnBackStackChangedListener {

    // viewbinding allows us to reduce boilerplate code and lateinit views
    private lateinit var binding: FragmentReposListBinding
    private lateinit var viewModel: GithubReposViewModel
    private lateinit var repoRv: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: ReposListAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(GithubReposViewModel::class.java)
        adapter = ReposListAdapter(this)
        layoutManager = LinearLayoutManager(context)
        inflateUi()

    }

    override fun onStart() {
        super.onStart()
        viewModel.getRepos()

    }

    @InternalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReposListBinding.inflate(inflater, container, false)

        repoRv = binding.reposRv
        swipeRefreshLayout = binding.swipe
        repoRv.layoutManager = layoutManager
        repoRv.adapter = adapter

        progressBar = binding.progressCircular

        // added onSwipeRefresh
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.getRepos()
            swipeRefreshLayout.isRefreshing = false
        }

        return binding.root
    }

    private fun inflateUi() {

        CoroutineScope(Dispatchers.Main).launch {

            viewModel.reposValue.collect { value ->
                when {
                    value.isLoading -> {
                        progressBar.visibility = View.VISIBLE
                    }
                    value.error.isNotEmpty() -> {
                        progressBar.visibility = View.GONE
                        Log.d(TAG, "inflateUi: ${value.error}")

                    }
                    value.repos.isNotEmpty() -> {
                        progressBar.visibility = View.GONE
                        adapter.setData(value.repos as ArrayList<GithubRepo>)

                    }
                }
            }
        }
    }

    //we handle the item click here instead of the adapter with a simple listener
    //because I didn't want the adapter to start fragment transactions
    override fun onItemClicked(bundle: Bundle) {
        val detailsFrag = RepoDetails()
        detailsFrag.arguments = bundle
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(android.R.id.content, detailsFrag).commit()
    }

    override fun onBackStackChanged() {
        if (activity != null) {
            if (requireActivity().supportFragmentManager.backStackEntryCount < 1) {
                (activity as MainActivity?)!!.hideUpButton()
            }
        }
    }

}