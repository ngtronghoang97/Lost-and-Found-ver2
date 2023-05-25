package com.example.lostandfound.dbhelper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.example.lostandfound.scheme.LostFoundContract

class DbHelper(context: Context)
    : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    /**
     * Create a database using an SQL helper.
     * Implement methods that create and maintain the database and tables.
     */

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        /**
         * This database is only a cache for online data, so its upgrade policy is to simply to
         * discard the data and start over.
         */
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    companion object {
        /**
         * If you change the database schema, you must increment the database version.
         */
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "LostAndFoundDb.db"

        private const val SQL_CREATE_ENTRIES =
            "CREATE TABLE ${LostFoundContract.LostFoundEntry.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                    "${LostFoundContract.LostFoundEntry.COLUMN_NAME_NAME} TEXT," +
                    "${LostFoundContract.LostFoundEntry.COLUMN_NAME_PHONE} TEXT," +
                    "${LostFoundContract.LostFoundEntry.COLUMN_NAME_DESCRIPTION} TEXT," +
                    "${LostFoundContract.LostFoundEntry.COLUMN_NAME_DATE} TEXT," +
                    "${LostFoundContract.LostFoundEntry.COLUMN_NAME_LOCATION} TEXT," +
                    "${LostFoundContract.LostFoundEntry.COLUMN_NAME_COORDINATES} TEXT)"

        private const val SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS ${LostFoundContract.LostFoundEntry.TABLE_NAME}"
    }
}