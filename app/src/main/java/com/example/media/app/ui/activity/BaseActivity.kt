package com.example.media.app.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.media.app.R
import com.example.media.app.ui.fragment.BaseFragment

abstract class BaseActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }

    fun setUpNavGraph(graphId: Int) {
        val newNavGraph = NavHostFragment.create(graphId)
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_container, newNavGraph)
            .setPrimaryNavigationFragment(newNavGraph)
            .commit()
    }

    override fun onBackPressed() {
        val navHostFragment =
            supportFragmentManager.primaryNavigationFragment as NavHostFragment
        val fragment =
            navHostFragment.childFragmentManager.primaryNavigationFragment as BaseFragment<*>
        fragment.popBackStack()
    }
}