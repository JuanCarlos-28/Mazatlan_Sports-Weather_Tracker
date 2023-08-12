package Modelo

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UsuarioDbHelper(context: Context?) :
  SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
  override fun onCreate(db: SQLiteDatabase) {
    db.execSQL(SQL_CREATE_USUARIO)
  }

  override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
    db.execSQL(SQL_DELETE_USUARIO)
    onCreate(db)
  }

  companion object {
    private const val TEXT_TYPE = " TEXT"
    private const val COMA_SEP = " ,"
    private const val SQL_CREATE_USUARIO = "CREATE TABLE " +
      DefineTabla.Usuarios.TABLE_NAME + " (" +
      DefineTabla.Usuarios.COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
      DefineTabla.Usuarios.COLUMN_NAME_NOMBREUSUARIO + TEXT_TYPE + COMA_SEP +
      DefineTabla.Usuarios.COLUMN_NAME_CORREO + TEXT_TYPE + COMA_SEP +
      DefineTabla.Usuarios.COLUMN_NAME_PASSWORD + TEXT_TYPE + ")"
    private const val SQL_DELETE_USUARIO = "DROP TABLE IF EXISTS " +
      DefineTabla.Usuarios.TABLE_NAME
    private const val DATABASE_NAME = "prueba.db"
    private const val DATABASE_VERSION = 1
  }
}
