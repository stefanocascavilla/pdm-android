package com.example.myfriendshouse.helpers

import android.content.Context
import android.location.Address
import android.location.Geocoder
import java.lang.Exception
import java.security.InvalidParameterException

class GeoLocationHelper (private val context: Context) {
    fun getFriendGeolocationInformation (street: String, city: String, country: String): Array<Double> {
        val geocoderResult: MutableList<Address>
        try {
            val geocoder = Geocoder(this.context)
            geocoderResult = geocoder.getFromLocationName("$street, $city, $country", 1)
        } catch (e: Exception) {
            throw InvalidParameterException("$e")
        }

        if (geocoderResult.size == 0) {
            throw InvalidParameterException("No address returned")
        }

        return arrayOf(geocoderResult[0].longitude, geocoderResult[0].latitude)
    }
}