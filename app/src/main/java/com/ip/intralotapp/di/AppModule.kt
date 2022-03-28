package com.ip.intralotapp.di

import com.ip.intralotapp.common.Constants
import com.ip.intralotapp.data.remote.GithubProjectsApi
import com.ip.intralotapp.data.repository.GithubReposRepositoryImpl
import com.ip.intralotapp.domain.repository.GithubReposRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * DI allows us to make our dependencies replaceable (Db instances, API instances)
 * We avoid hard coding dependencies in our app, which makes it easier to test
 *
 * This is the way we tell Hilt how we provide the different components
 * and how long the will live in our app
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    //we tell Hilt how we provide the API and that it will have only one instance of the API
    @Provides
    @Singleton
    fun provideGithubApi(): GithubProjectsApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GithubProjectsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideGitHubReposRepository(api: GithubProjectsApi): GithubReposRepository {
        return GithubReposRepositoryImpl(api)
    }
}