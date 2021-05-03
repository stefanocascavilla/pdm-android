package com.example.myfriendshouse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickListFriends (v: View) {
        this.startActivity(Intent(this@MainActivity, FriendsListActivity::class.java))
    }

    fun onClickMapFriends (v: View) {
        this.startActivity(Intent(this@MainActivity, FriendsMapActivity::class.java))
    }
}