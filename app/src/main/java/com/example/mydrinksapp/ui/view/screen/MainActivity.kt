package com.example.mydrinksapp.ui.view.screen

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.mydrinksapp.R
import com.example.mydrinksapp.databinding.ActivityMainBinding
import com.example.mydrinksapp.ui.view.adapter.DrinkAdapter
import com.example.mydrinksapp.ui.viewmodel.DrinkViewModel
import com.example.mydrinksapp.utils.hideKeyboard
import com.example.mydrinksapp.utils.isConnected
import com.example.mydrinksapp.utils.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var drinkAdapter: DrinkAdapter
    private val drinkViewModel: DrinkViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpRecyclerView()
        setUpSwipeRefresh()
        initEvent()
        bindingObservers()
        setUpToolbar()
    }

    private fun setUpToolbar() = with(binding) {
        setSupportActionBar(tbMain)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setUpSwipeRefresh() = with(binding) {
        swipeRefresh.apply {
            setOnRefreshListener(this@MainActivity)
        }
    }

    private fun initEvent() {
        drinkViewModel.getDrinks()
    }

    private fun bindingObservers() = with(binding) {
        drinkViewModel.drinkModel.observe(this@MainActivity, drinkAdapter::setItems)
        drinkViewModel.isRefresh.observe(this@MainActivity) { swipeRefresh.isRefreshing = it }
        drinkViewModel.snackbar.observe(this@MainActivity) { toast(it, this@MainActivity) }
        drinkViewModel.isProgress.observe(this@MainActivity) { progress.isVisible = it }
        drinkViewModel.isEmpty.observe(this@MainActivity) {
            txtEmpty.visibility = if (it) View.VISIBLE else View.GONE
            swipeRefresh.visibility = if (it) View.GONE else View.VISIBLE
        }
    }

    private fun setUpRecyclerView() = with(binding) {
        drinkAdapter = DrinkAdapter {
            toDetail(it)
        }
        rvItems.apply {
            adapter = drinkAdapter
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun toDetail(url: String) {
        val intent = Intent(this, DetailDrinkActivity::class.java)
        intent.apply {
            putExtra("url", url)
            startActivity(this)
        }
    }

    override fun onRefresh() {
        drinkViewModel.refreshDrink(isConnected(this))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        val searchView =
            menu?.findItem(R.id.action_search)?.actionView as androidx.appcompat.widget.SearchView

        searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                drinkViewModel.searchDrink(query?.trim() ?: "")
                binding.root.hideKeyboard()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                drinkViewModel.searchDrink(newText?.trim() ?: "")
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }
}