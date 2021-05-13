package com.example.myfriendshouse.helpers

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity


abstract class AndroidInternetActivity: AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    protected fun checkInternetConnectivity (): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}