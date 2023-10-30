package com.example.gj_bam_1


import android.content.*
import android.database.Cursor
import android.net.Uri
import androidx.room.Room
import com.example.gj_bam_1.database.UserDataDatabase

class Provider : ContentProvider() {
    companion object {
        private var uriMatcher: UriMatcher? = null
        init {
            uriMatcher = UriMatcher(UriMatcher.NO_MATCH)
            uriMatcher!!.addURI(
                "com.example.gj_bam_1.provider",
                "userData",
                1
            )
            uriMatcher!!.addURI(
                "com.example.gj_bam_1.provider",
                "userData/*",
                1
            )
        }
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        val db = Room.databaseBuilder(
            context!!.applicationContext,
            UserDataDatabase::class.java, "userData-database"
        ).allowMainThreadQueries().build()

        val cursor = db.query("SELECT username, number FROM userData", null)

        cursor.setNotificationUri(context!!.contentResolver, uri)

        return cursor
    }

    override fun onCreate(): Boolean {
        return true
    }

    override fun getType(p0: Uri): String? {
        TODO("Not yet implemented")
    }

    override fun insert(p0: Uri, p1: ContentValues?): Uri? {
        TODO("Not yet implemented")
    }

    override fun delete(p0: Uri, p1: String?, p2: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        TODO("Not yet implemented")
    }
}