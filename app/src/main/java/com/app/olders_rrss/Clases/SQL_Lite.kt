package com.app.olders_rrss.Clases

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class clsSQL(context: Context): SQLiteOpenHelper(
    context, "bd_local.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {

        db!!.execSQL("CREATE TABLE tblUsuarios (ID INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, email TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Verifica la diferencia de versiones y ejecuta

        db!!.execSQL("DROP TABLE IF EXISTS tblUsuarios")
        this.onCreate(db)
    }

    fun DeleteDatos(){
        val db = this.writableDatabase
        db.execSQL("DELETE FROM tblUsuarios")
        db.close()
    }

    fun InsertDatos(nombre: String, email: String) {
        val datos = ContentValues()

        datos.put("nombre", nombre)
        datos.put("email", email)

        val db = this.writableDatabase
        db.insert("tblUsuarios", null, datos)
        db.close()
    }

}

