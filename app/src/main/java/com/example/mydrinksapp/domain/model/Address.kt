package com.example.mydrinksapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Address(
    var lat: Double = 0.0,
    var long: Double = 0.0
) : Parcelable
