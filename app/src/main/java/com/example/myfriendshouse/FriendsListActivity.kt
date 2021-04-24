package com.example.myfriendshouse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class FriendsListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends_list)
    }

    fun onClickAdd (v: View) {
        this.startActivity(Intent(this@FriendsListActivity, FriendsAddActivity::class.java))
    }
}