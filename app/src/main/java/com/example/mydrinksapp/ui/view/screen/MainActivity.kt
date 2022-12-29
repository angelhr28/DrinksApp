package com.example.mydrinksapp.ui.view.screen

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.mydrinksapp.R
import com.example.mydrinksapp.databinding.ActivityMainBinding
import com.example.mydrinksapp.utils.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val navController by lazy { findNavController(R.id.navHostFragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setToolbarWithButtonBack(binding.tbMain, R.string.title_activity_main)
        setUpNavGraph()
    }

    private fun setUpNavGraph() {
        navController.setGraph(R.navigation.nav_drink_graph)
        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

    override fun getActivityBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }
}