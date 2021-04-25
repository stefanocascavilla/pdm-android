package com.example.myfriendshouse.helpers

import android.content.ContentValues
import android.content.Context

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

import com.example.myfriendshouse.constants.DatabaseConstants

import com.example.myfriendshouse.dto.Friend

class DatabaseHelper (var context: Context): SQLiteOpenHelper(context, DatabaseConstants.DATABASE_NAME, null, DatabaseConstants.DATABASE_VERSION) {
    private val databaseReader: SQLiteDatabase = this.readableDatabase
    private val databaseWriter: SQLiteDatabase = this.writableDatabase

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(DatabaseConstants.TABLE_CREATION_STATEMENT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(DatabaseConstants.TABLE_DROP_STATEMENT)
        this.onCreate(db)
    }


    fun createFriend (friend: Friend): Boolean {
        val contentValues = ContentValues()
        contentValues.put(DatabaseConstants.COL_NAME, friend.name)
        contentValues.put(DatabaseConstants.COL_SURNAME, friend.surname)
        contentValues.put(DatabaseConstants.COL_STREET, friend.street)
        contentValues.put(DatabaseConstants.COL_CITY, friend.city)
        contentValues.put(DatabaseConstants.COL_COUNTRY, friend.country)
        contentValues.put(DatabaseConstants.COL_LONGITUDE, friend.longitude)
        contentValues.put(DatabaseConstants.COL_LATITUDE, friend.latitude)

        val writeResult = this.databaseWriter.insert(DatabaseConstants.TABLE_NAME, null, contentValues)
        return writeResult != (0).toLong()
    }

    fun deleteFriend (friendId: Int): Boolean {
        val deleteResult = this.databaseWriter.delete(DatabaseConstants.TABLE_NAME, "id = $friendId", null)
        return deleteResult == 1
    }

    fun getFriend (friendId: Int): Friend {
        var friend: Friend? = null

        val queryResult = this.databaseReader.rawQuery("${DatabaseConstants.QUERY_SINGLE_FRIEND} id = $friendId", null)
        if (queryResult.moveToFirst()) {
            friend = Friend(
                id = queryResult.getInt(queryResult.getColumnIndex(DatabaseConstants.COL_ID)),
                name = queryResult.getString(queryResult.getColumnIndex(DatabaseConstants.COL_NAME)),
                surname = queryResult.getString(queryResult.getColumnIndex(DatabaseConstants.COL_SURNAME)),
                street = queryResult.getString(queryResult.getColumnIndex(DatabaseConstants.COL_STREET)),
                city = queryResult.getString(queryResult.getColumnIndex(DatabaseConstants.COL_CITY)),
                country = queryResult.getString(queryResult.getColumnIndex(DatabaseConstants.COL_COUNTRY)),
                longitude = queryResult.getString(queryResult.getColumnIndex(DatabaseConstants.COL_LONGITUDE)).toDouble(),
                latitude = queryResult.getString(queryResult.getColumnIndex(DatabaseConstants.COL_LATITUDE)).toDouble()
            )
        }
        queryResult.close()

        return friend!!
    }

    fun listFriends (): ArrayList<Friend> {
        val returnArray: ArrayList<Friend> = ArrayList()

        val queryResult = this.databaseReader.rawQuery(DatabaseConstants.QUERY_LIST_FRIENDS, null)
        if (queryResult.moveToFirst()) {
            do {
                returnArray.add(Friend(
                    id = queryResult.getInt(queryResult.getColumnIndex(DatabaseConstants.COL_ID)),
                    name = queryResult.getString(queryResult.getColumnIndex(DatabaseConstants.COL_NAME)),
                    surname = queryResult.getString(queryResult.getColumnIndex(DatabaseConstants.COL_SURNAME)),
                    street = queryResult.getString(queryResult.getColumnIndex(DatabaseConstants.COL_STREET)),
                    city = queryResult.getString(queryResult.getColumnIndex(DatabaseConstants.COL_CITY)),
                    country = queryResult.getString(queryResult.getColumnIndex(DatabaseConstants.COL_COUNTRY)),
                    longitude = queryResult.getString(queryResult.getColumnIndex(DatabaseConstants.COL_LONGITUDE)).toDouble(),
                    latitude = queryResult.getString(queryResult.getColumnIndex(DatabaseConstants.COL_LATITUDE)).toDouble()
                ))
            } while (queryResult.moveToNext())
        }
        queryResult.close()

        return returnArray
    }
}