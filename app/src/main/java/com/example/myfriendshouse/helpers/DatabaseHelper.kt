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
}