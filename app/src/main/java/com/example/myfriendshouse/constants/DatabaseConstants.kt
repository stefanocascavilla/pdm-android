package com.example.myfriendshouse.constants

object DatabaseConstants {
    const val DATABASE_NAME: String = "myfriendshouse.db"
    const val DATABASE_VERSION: Int = 1

    const val TABLE_NAME = "friends"
    const val COL_NAME = "name"
    const val COL_SURNAME = "surname"
    const val COL_STREET = "street"
    const val COL_CITY = "city"
    const val COL_COUNTRY = "country"
    const val COL_LONGITUDE = "longitude"
    const val COL_LATITUDE = "latitude"

    const val TABLE_CREATION_STATEMENT = "CREATE TABLE $TABLE_NAME ($COL_NAME TEXT, $COL_SURNAME TEXT, $COL_STREET TEXT, $COL_CITY TEXT, $COL_COUNTRY TEXT, $COL_LONGITUDE TEXT, $COL_LATITUDE TEXT)"
    const val TABLE_DROP_STATEMENT = "DROP TABLE IF EXISTS $TABLE_NAME"
}