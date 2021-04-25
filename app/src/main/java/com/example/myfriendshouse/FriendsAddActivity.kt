package com.example.myfriendshouse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

import android.location.Geocoder
import android.location.Address

import com.example.myfriendshouse.dto.Friend
import com.example.myfriendshouse.helpers.DatabaseHelper

import kotlinx.android.synthetic.main.activity_friends_add.*
import java.lang.Exception

import java.security.InvalidParameterException

class FriendsAddActivity : AppCompatActivity() {
    private var dbHelper: DatabaseHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends_add)
    }

    override fun onStart() {
        super.onStart()
        this.dbHelper = DatabaseHelper(this.applicationContext)
    }

    private fun getFriendGeolocationInformation (street: String, city: String, country: String): Array<Double> {
        val geocoderResult: MutableList<Address>
        try {
            val geocoder = Geocoder(this.applicationContext)
            geocoderResult = geocoder.getFromLocationName("$street, $city, $country", 1)
        } catch (e: Exception) {
            throw InvalidParameterException("$e")
        }

        if (geocoderResult.size == 0) {
            throw InvalidParameterException("No address returned")
        }

        return arrayOf(geocoderResult[0].longitude, geocoderResult[0].latitude)
    }

    fun onAddFriend (v: View) {
        val name = nameEditText.text.toString()
        val surname = surnameEditText.text.toString()
        val street = streetEditText.text.toString()
        val city = cityEditText.text.toString()
        val country = countryEditText.text.toString()

        val geoInformation: Array<Double>
        try {
            geoInformation = this.getFriendGeolocationInformation(street, city, country)
        } catch (e: InvalidParameterException) {
            println("The error in geolocation is $e")
            return
        }

        val newFriend = Friend(
            id = -1,
            name = name,
            surname = surname,
            street = street,
            city = city,
            country = country,
            longitude = geoInformation[0],
            latitude = geoInformation[1]
        )
        if (!this.dbHelper!!.createFriend(newFriend)) {
            Toast.makeText(this@FriendsAddActivity, "Error while inserting the friend in the DB", Toast.LENGTH_LONG).show()
        }
        this.finish()
    }
}