package com.ip.intralotapp.domain.use_case.get_projects

import com.ip.intralotapp.common.Resource
import com.ip.intralotapp.data.remote.dto.toGitHubRepo
import com.ip.intralotapp.domain.models.GithubRepo
import com.ip.intralotapp.domain.repository.GithubReposRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * We create a use case just for the cause we want to use it.
 * No need to include here other cases that the one described in the file name.
 */
class GetReposUseCase @Inject constructor(
    //by implementing the GithubReposRepository interface we allow modularity to the app and it is easily replaceable
    private val repository: GithubReposRepository
) {

    // operator fun invoke() allows us to use the class as it was a function
    // flow is asynchronous data stream that sequentially emits values and completes normally or with an exception
    operator fun invoke(): Flow<Resource<List<GithubRepo>>> = flow {

        try {
            // still loading - we use this in ViewModel
            emit(Resource.Loading<List<GithubRepo>>())
            // if the below line didn't crash
            val repos = repository.getRepos().map { it.toGitHubRepo() }
            // we know that we can now emit the data from the response
            emit(Resource.Success(repos))
        } catch (e: HttpException) {
            // catching exceptions
            emit(Resource.Error<List<GithubRepo>>(e.localizedMessage ?: "HTTP Unexpected Error"))
        } catch (e: IOException) {
            emit(
                Resource.Error<List<GithubRepo>>(
                    e.localizedMessage ?: "Check your internet connection"
                )
            )
        }
    }
}