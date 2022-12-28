package com.example.mydrinksapp.ui.view.screen.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.mydrinksapp.R
import com.example.mydrinksapp.databinding.FragmentDrinksBinding
import com.example.mydrinksapp.ui.view.adapter.DrinkAdapter
import com.example.mydrinksapp.ui.viewmodel.DrinkViewModel
import com.example.mydrinksapp.utils.BaseFragment
import com.example.mydrinksapp.utils.hideKeyboard
import com.example.mydrinksapp.utils.isConnected
import com.example.mydrinksapp.utils.safeNavigateFromNavController
import com.example.mydrinksapp.utils.toast

class DrinksFragment : BaseFragment<FragmentDrinksBinding>(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var drinkAdapter: DrinkAdapter
    private val drinkViewModel: DrinkViewModel by activityViewModels()
    private lateinit var navController: NavController

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDrinksBinding {
        return FragmentDrinksBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        setUpRecyclerView()
        setUpSwipeRefresh()
        initEvent()
        bindingObservers()
    }

    private fun setUpSwipeRefresh() = with(binding) {
        swipeRefresh.apply {
            setOnRefreshListener(this@DrinksFragment)
        }
    }

    override fun onRefresh() {
        this.context?.let { isConnected(it) }?.let { drinkViewModel.refreshDrink(it) }
    }

    override fun initEvent() {
        drinkViewModel.getDrinks()
    }

    private fun bindingObservers() = with(binding) {
        drinkViewModel.drinkModel.observe(viewLifecycleOwner, drinkAdapter::setItems)
        drinkViewModel.isRefresh.observe(viewLifecycleOwner) { swipeRefresh.isRefreshing = it }
        drinkViewModel.snackbar.observe(viewLifecycleOwner) {
            toast(it, this@DrinksFragment.context!!)
        }
        drinkViewModel.isProgress.observe(viewLifecycleOwner) { progress.isVisible = it }
        drinkViewModel.isEmpty.observe(viewLifecycleOwner) {
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
            layoutManager = LinearLayoutManager(this.context)
        }
    }

    private fun toDetail(drinkId: Int) {
        val action = DrinksFragmentDirections.actionDrinksFragmentToDetailDrinkFragment(drinkId)
        safeNavigateFromNavController(action)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)

        val searchView =
            menu.findItem(R.id.action_search)?.actionView as androidx.appcompat.widget.SearchView

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
        super.onCreateOptionsMenu(menu, inflater)
    }
}