package com.ip.intralotapp.presentation

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import com.ip.intralotapp.R
import com.ip.intralotapp.presentation.repos_list.ReposListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        supportFragmentManager.beginTransaction().replace(android.R.id.content, ReposListFragment())
            .commit()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBar != null) {
            if (item.itemId == R.id.home) {
                onBackPressed()
                return true
            }
        }
        return false
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, ReposListFragment())
                .commit()
        }
    }

    fun showUpButton() {
        actionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    fun hideUpButton() {
        actionBar!!.setDisplayHomeAsUpEnabled(false)
    }
}