package com.example.myfriendshouse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import com.example.myfriendshouse.adapters.FriendsListAdapter
import com.example.myfriendshouse.dto.Friend
import com.example.myfriendshouse.helpers.DatabaseHelper

import kotlinx.android.synthetic.main.activity_friends_list.*

class FriendsListActivity : AppCompatActivity() {
    private var friendsList: ArrayList<Friend>? = null
    private var dbHelper: DatabaseHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends_list)
    }

    override fun onStart() {
        super.onStart()
        this.dbHelper = DatabaseHelper(this.applicationContext)

        this.friendsList = this.dbHelper!!.listFriends()
        friendListView.adapter = FriendsListAdapter(this.applicationContext, this.friendsList!!)
    }

    fun onClickAdd (v: View) {
        this.startActivity(Intent(this@FriendsListActivity, FriendsAddActivity::class.java))
    }
}