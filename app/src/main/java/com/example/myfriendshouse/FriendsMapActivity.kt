package com.example.myfriendshouse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.myfriendshouse.dto.Friend
import com.example.myfriendshouse.helpers.DatabaseHelper

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class FriendsMapActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    private lateinit var dbHelper: DatabaseHelper

    private lateinit var friendsList: ArrayList<Friend>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_friends_map)
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onStart() {
        super.onStart()

        this.dbHelper = DatabaseHelper(this.applicationContext)
        this.friendsList = this.dbHelper.listFriends()
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        for (friendItem in this.friendsList) {
            mMap.addMarker(MarkerOptions().position(LatLng(friendItem.latitude, friendItem.longitude)).title("${friendItem.name} ${friendItem.surname}"))
        }

        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}