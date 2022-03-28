package com.ip.intralotapp.presentation.repos_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ip.intralotapp.common.Resource
import com.ip.intralotapp.domain.use_case.get_projects.GetReposUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

val LOG_TAG = GithubReposViewModel::class.simpleName


@HiltViewModel
class GithubReposViewModel @Inject constructor(
    private val getReposUseCase: GetReposUseCase
) : ViewModel() {


    private val _reposValue = MutableStateFlow(GithubReposState())
    // we provide the state of our response in the Ui through this var
    var reposValue: StateFlow<GithubReposState> = _reposValue


    fun getRepos() = viewModelScope.launch(Dispatchers.IO) {
        getReposUseCase().collect {
            when (it) {
                // handling the states
                is Resource.Success -> {
                    _reposValue.value = GithubReposState(repos = it.data ?: emptyList())
                    Log.d(LOG_TAG, "Api request success, getting results")
                }
                is Resource.Loading -> {
                    _reposValue.value = GithubReposState(isLoading = true)
                    Log.d(LOG_TAG, "Api request loading...")
                }
                is Resource.Error -> {
                    _reposValue.value = GithubReposState(error = it.message ?: "Unexpected Error")
                    Log.d(LOG_TAG, "Unexpected Error ${it.message}")
                }
            }
        }
    }

}