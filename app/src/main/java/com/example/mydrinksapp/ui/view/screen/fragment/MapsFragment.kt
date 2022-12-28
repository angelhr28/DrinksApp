package com.example.mydrinksapp.ui.view.screen.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.mydrinksapp.databinding.FragmentMapsBinding
import com.example.mydrinksapp.domain.model.Address
import com.example.mydrinksapp.utils.BaseFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : BaseFragment<FragmentMapsBinding>(), OnMapReadyCallback {

    private val args: MapsFragmentArgs by navArgs()
    private val name: String by lazy { args.name }
    private val address: Address by lazy { args.address }
    private var mMap: GoogleMap? = null

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMapsBinding {
        return FragmentMapsBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initEvent()
    }

    override fun initEvent() {
        val mapFragment =
            childFragmentManager.findFragmentById(binding.map.id) as SupportMapFragment

        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val address = LatLng(address.lat, address.long)

        val uiSettings = mMap?.uiSettings
        uiSettings?.isIndoorLevelPickerEnabled = true
        uiSettings?.isMyLocationButtonEnabled = true
        uiSettings?.isMapToolbarEnabled = true
        uiSettings?.isCompassEnabled = true
        uiSettings?.isZoomControlsEnabled = true
        mMap?.addMarker(MarkerOptions().position(address).title("RECETE DE $name"))
        mMap?.moveCamera(CameraUpdateFactory.newLatLng(address))
        mMap?.setMinZoomPreference(15f)
        mMap?.cameraPosition
    }
}