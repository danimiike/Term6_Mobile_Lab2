package com.example.dmiike.lab2

import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream

class DBAdapter(private val context: Context) {

    companion object {
        private const val DATABASE_COLUMN = "plant_name"
        private const val TAG = "DBAdapter"
        private const val DATABASE_NAME = "plantDB.db"
        private const val DATABASE_TABLE = "plant"
        private const val DATABASE_VERSION = 1
        private const val DATABASE_CREATE =
            "create table $DATABASE_TABLE ($DATABASE_COLUMN text primary key);"
        private val context: Context? = null

    }

    var DBHelper: DatabaseHelper? = null
    var db: SQLiteDatabase? = null

    init {
        DBHelper = DatabaseHelper(context)
    }

    class DatabaseHelper internal constructor(context: Context?) :
        SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
        override fun onCreate(db: SQLiteDatabase) {
            try {
                db.execSQL(DATABASE_CREATE)
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }

        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            Log.w(
                TAG, "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data"
            )
            db.execSQL("DROP TABLE IF EXISTS $DATABASE_TABLE")
            onCreate(db)
        }
    }

    //---opens the database---
    @Throws(SQLException::class, IOException::class)
    fun open(): DBAdapter {
        val myInput = context!!.assets.open(DATABASE_NAME)
        val path: File? = context?.getDatabasePath(DATABASE_NAME)
        val myOutput: OutputStream = FileOutputStream(path)

        val buffer = ByteArray(1024)
        var length: Int
        while (myInput.read(buffer).also { length = it } > 0) {
            myOutput.write(buffer, 0, length)
        }

        myOutput.flush()
        myOutput.close()
        myInput.close()
        db = DBHelper!!.writableDatabase
        return this
    }

    //---closes the database---
    fun close() {
        DBHelper!!.close()
    }

    //---retrieves all the contacts---
    fun getAllPlants(): Cursor? {
        return db!!.query(DATABASE_TABLE, arrayOf(DATABASE_COLUMN), null, null, null, null, null)
    }

}