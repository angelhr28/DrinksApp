package com.example.mydrinksapp.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

fun toast(message: String, context: Context) {
    Toast.makeText(
        context,
        message,
        Toast.LENGTH_SHORT
    ).show()
}

fun isConnected(context: Context): Boolean {
    val cm: ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
    return activeNetwork != null && activeNetwork.isConnected
}


fun Any.toJson(): String = Gson().toJson(this)

inline fun <reified T : Any> parseJson(json: String): T {
    val gson = Gson()
    return gson.fromJson(json, T::class.java)
}

@RequiresApi(Build.VERSION_CODES.N)
@SuppressLint("SimpleDateFormat", "NewApi")
fun String.parseDate(): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val formatToDate = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss.SSSX")
        val stringToDate = formatToDate.parse(this)
        val formatToString = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        formatToString.format(stringToDate)

    } else {
        val formatToDate = SimpleDateFormat("uuuu-MM-dd'T'HH:mm:ss.SSSX")
        val formatToString = SimpleDateFormat("yyyy-MM-dd")
        val stringToDate = formatToDate.parse(this)
        formatToString.format(stringToDate)
    }
}

@SuppressLint("NewApi", "SimpleDateFormat")
fun String.toDate(): Date? {
    val date = SimpleDateFormat("yyyy-MM-dd")
    return date.parse(this)
}

@RequiresApi(Build.VERSION_CODES.N)
fun String.getDate(): String {
    val currentDate = Date(System.currentTimeMillis())

    val diff: Long = currentDate.time - this.parseDate()
        .toDate()?.time!!

    val seconds = diff / 1000
    val minutes = seconds / 60
    val hours = minutes / 60
    val day = hours / 24

    return when {
        day > 0 -> "$day D"
        hours > 0 -> "$hours H"
        minutes > 0 -> "$minutes M"
        seconds > 0 -> "$seconds S"
        else -> "HOY"
    }
}

fun View.hideKeyboard() {
    val inputMethodManager =
        context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(this.windowToken, 0)
}

fun Fragment.safeNavigateFromNavController(directions: NavDirections) = with(findNavController()) {
    currentDestination?.getAction(directions.actionId)?.let {
        navigate(directions)
    }
}