package com.example.myfriendshouse

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi

import com.example.myfriendshouse.adapters.FriendsListAdapter
import com.example.myfriendshouse.dto.Friend
import com.example.myfriendshouse.helpers.AndroidInternetActivity
import com.example.myfriendshouse.helpers.DatabaseHelper

import kotlinx.android.synthetic.main.activity_friends_list.*

class FriendsListActivity : AndroidInternetActivity() {
    private lateinit var friendsList: ArrayList<Friend>
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends_list)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onStart() {
        super.onStart()
        this.dbHelper = DatabaseHelper(this.applicationContext)

        if (!this.checkInternetConnectivity()) {
            Toast.makeText(this@FriendsListActivity, "You need to switch internet connectivity on", Toast.LENGTH_LONG).show()
            this.finish()
        }
        this.friendsList = this.dbHelper.listFriends()
        friendListView.adapter = FriendsListAdapter(this.applicationContext, this.friendsList)
    }

    fun onClickAdd (v: View) {
        this.startActivity(Intent(this@FriendsListActivity, FriendsAddActivity::class.java))
    }
}