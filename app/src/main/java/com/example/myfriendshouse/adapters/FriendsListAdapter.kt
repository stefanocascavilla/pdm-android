package com.example.myfriendshouse.adapters

import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

import com.example.myfriendshouse.R

import com.example.myfriendshouse.dto.Friend
import com.example.myfriendshouse.helpers.DeleteClickListener

class FriendsListAdapter(private val context: Context, private val data: ArrayList<Friend>): BaseAdapter() {
    override fun getCount(): Int {
        return this.data.size
    }

    override fun getItem(position: Int): Friend {
        return this.data[position]
    }

    override fun getItemId(position: Int): Long {
        return this.data[position].id.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var newView = convertView
        if (newView == null) {
            newView = LayoutInflater.from(context).inflate(R.layout.friends_list_item, parent, false)
        }

        val currentData = this.getItem(position)
        newView?.findViewById<TextView>(R.id.userNameSurname)?.text = "${currentData.name} ${currentData.surname}"
        newView?.findViewById<TextView>(R.id.userAddress)?.text = "${currentData.street} - ${currentData.city} - ${currentData.country}"

        newView?.findViewById<ImageView>(R.id.userIcon)?.setImageResource(R.mipmap.user_icon)
        newView?.findViewById<ImageView>(R.id.deleteIcon)?.setImageResource(R.mipmap.delete_icon_foreground)

        newView?.findViewById<ImageView>(R.id.deleteIcon)?.setOnClickListener(DeleteClickListener(this.context, currentData.id, this, this.data))

        return newView
    }
}