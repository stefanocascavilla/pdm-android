package com.example.myfriendshouse.helpers

import android.content.Context
import android.view.View
import android.widget.Toast

import com.example.myfriendshouse.adapters.FriendsListAdapter
import com.example.myfriendshouse.dto.Friend

class DeleteClickListener(private val context: Context, private val id: Int, private val adapter: FriendsListAdapter, private val data: ArrayList<Friend>): View.OnClickListener {
    private val dbHelper: DatabaseHelper = DatabaseHelper(context)

    override fun onClick(v: View?) {
        if (this.data.remove(this.dbHelper.getFriend(this.id)) && this.dbHelper.deleteFriend(this.id)) {
            this.adapter.notifyDataSetChanged()
            Toast.makeText(this.context, "Correctly removed the friend", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this.context, "Error while removing the friend", Toast.LENGTH_LONG).show()
        }
    }
}