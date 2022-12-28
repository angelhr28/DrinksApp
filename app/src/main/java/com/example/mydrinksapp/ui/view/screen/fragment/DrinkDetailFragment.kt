package com.example.mydrinksapp.ui.view.screen.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.mydrinksapp.databinding.FragmentDetailDrinkBinding
import com.example.mydrinksapp.domain.model.Address
import com.example.mydrinksapp.ui.viewmodel.DrinkViewModel
import com.example.mydrinksapp.utils.BaseFragment
import com.example.mydrinksapp.utils.safeNavigateFromNavController
import com.squareup.picasso.Picasso

class DrinkDetailFragment : BaseFragment<FragmentDetailDrinkBinding>() {

    private val drinkViewModel: DrinkViewModel by activityViewModels()
    private lateinit var navController: NavController
    private val args: DrinkDetailFragmentArgs by navArgs()
    private val drinkId: Int by lazy { args.drinkId }
    private val address = Address()
    private var name = ""

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailDrinkBinding {
        return FragmentDetailDrinkBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        initEvent()
        setUpBtn()
        bindingObservers()

    }

    private fun setUpBtn() = with(binding) {
        btnMap.setOnClickListener {
            val action =
                DrinkDetailFragmentDirections.actionDetailDrinkFragmentToMapsFragment(address, name)
            safeNavigateFromNavController(action)
        }
    }

    private fun bindingObservers() = with(binding) {
        drinkViewModel.detail.observe(viewLifecycleOwner) {
            txtTitle.text = it.drinkName
            textDesc.text = it.instruction
            textIngredients.text = it.ingredients
            Picasso.get()
                .load(it.drinkImg)
                .into(imgDrink)
            address.long = it.drinkLon
            address.lat = it.drinkLat
            name = it.drinkName
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun initEvent() {
        drinkViewModel.getDrinkDetail(drinkId)
    }
}