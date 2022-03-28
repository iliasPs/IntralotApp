package com.ip.intralotapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


/**
 * This is needed by Hilt.
 * Provides Hilt with access to application Context
 */
@HiltAndroidApp
class GithubReposApplication : Application()