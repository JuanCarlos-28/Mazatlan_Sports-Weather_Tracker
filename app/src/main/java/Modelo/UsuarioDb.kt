package Modelo

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.mazatlansportsweathertracker.Usuarios

class UsuarioDb : Persistencia, Proyeccion {
  private var context: Context
  private var helper: UsuarioDbHelper
  private var db: SQLiteDatabase? = null

  constructor(context: Context, helper: UsuarioDbHelper) {
    this.context = context
    this.helper = helper
  }

  constructor(context: Context) {
    this.context = context
    helper = UsuarioDbHelper(this.context)
  }

  override fun openDataBase() {
    db = helper.getWritableDatabase()
  }

  override fun closeDataBase() {
    helper.close()
  }


  override fun insertUsuario(usuario: Usuarios?): Long {
    val values = ContentValues()
    values.put(DefineTabla.Usuarios.COLUMN_NAME_NOMBREUSUARIO, usuario?.getNombre())
    values.put(DefineTabla.Usuarios.COLUMN_NAME_CORREO, usuario?.getCorreo())
    values.put(DefineTabla.Usuarios.COLUMN_NAME_PASSWORD, usuario?.getContrasenia())
    openDataBase()
    val num = db!!.insert(DefineTabla.Usuarios.TABLE_NAME, null, values)
    Log.d("Agregar", "Se inserto el usuario $num")
    return num
  }

  override fun obtenerUser(correo: String?): Boolean {
    db = helper.getWritableDatabase()
    val cursor = db!!.query(
      DefineTabla.Usuarios.TABLE_NAME,
      DefineTabla.REGISTROS,
      DefineTabla.Usuarios.COLUMN_NAME_CORREO + " = ? ", arrayOf(correo),
      null, null, null
    )
    return cursor.moveToFirst()

    //Regresarr true si ya existe un usuario con ese correo
  }

  override fun getUsuario(nombre: String?, password: String?): Boolean {
    db = helper.getWritableDatabase()
    val cursor = db!!.query(
      DefineTabla.Usuarios.TABLE_NAME,
      DefineTabla.REGISTROS,
      DefineTabla.Usuarios.COLUMN_NAME_CORREO + " = ? AND " + DefineTabla.Usuarios.COLUMN_NAME_PASSWORD + " = ?",
      arrayOf(nombre, password),
      null,
      null,
      null
    )
    return cursor.moveToFirst()

    //Regresara true si encuentra algun usuario con la contraseña y el correo ingresado.
  }

  override fun allUsuarios(): ArrayList<Usuarios?>? {
    db = helper.getWritableDatabase()
    val cursor = db!!.query(
      DefineTabla.Usuarios.TABLE_NAME,
      DefineTabla.REGISTROS,
      null, null, null, null, null
    )
    val usuarios: ArrayList<Usuarios?> = ArrayList<Usuarios?>()
    cursor.moveToFirst()
    while (!cursor.isAfterLast) {
      val usuario: Usuarios? = readUsuario(cursor)
      usuarios.add(usuario)
      cursor.moveToNext()
    }
    cursor.close()
    return usuarios
  }

  override fun readUsuario(cursor: Cursor?): Usuarios? {
    val usuario = Usuarios()
    usuario.setIdUsuario(cursor!!.getInt(0))
    usuario.setNombre(cursor.getString(1))
    usuario.setCorreo(cursor.getString(2))
    usuario.setContrasenia(cursor.getString(3))
    return usuario
  }
}
