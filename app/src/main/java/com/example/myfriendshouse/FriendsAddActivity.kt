package com.example.myfriendshouse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

import com.example.myfriendshouse.dto.Friend
import com.example.myfriendshouse.helpers.DatabaseHelper
import com.example.myfriendshouse.helpers.GeoLocationHelper

import kotlinx.android.synthetic.main.activity_friends_add.*

import java.security.InvalidParameterException
import kotlin.concurrent.thread

class FriendsAddActivity : AppCompatActivity() {
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var geoLocationHelper: GeoLocationHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends_add)
    }

    override fun onStart() {
        super.onStart()
        this.dbHelper = DatabaseHelper(this.applicationContext)
        this.geoLocationHelper = GeoLocationHelper(this.applicationContext)
    }

    fun onAddFriend (v: View) {
        val name = nameEditText.text.toString()
        val surname = surnameEditText.text.toString()
        val street = streetEditText.text.toString()
        val city = cityEditText.text.toString()
        val country = countryEditText.text.toString()

        thread(start = true) {
            val geoInformation: Array<Double>
            try {
                geoInformation = this.geoLocationHelper.getFriendGeolocationInformation(street, city, country)
            } catch (e: InvalidParameterException) {
                this.runOnUiThread { Toast.makeText(this@FriendsAddActivity, "Error while retrieving geolocation info", Toast.LENGTH_LONG).show() }
                return@thread
            }

            this.runOnUiThread {
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
                if (!this.dbHelper.createFriend(newFriend)) {
                    Toast.makeText(this@FriendsAddActivity, "Error while inserting the friend in the DB", Toast.LENGTH_LONG).show()
                }
                this.finish()
            }
        }
    }
}