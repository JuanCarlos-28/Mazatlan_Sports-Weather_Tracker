package Modelo

object DefineTabla {
  var REGISTROS = arrayOf(
    Usuarios.COLUMN_NAME_ID,
    Usuarios.COLUMN_NAME_NOMBREUSUARIO,
    Usuarios.COLUMN_NAME_CORREO,
    Usuarios.COLUMN_NAME_PASSWORD
  )

  object Usuarios {
    const val TABLE_NAME = "usuarios"
    const val COLUMN_NAME_ID = "id"
    const val COLUMN_NAME_NOMBREUSUARIO = "nombre"
    const val COLUMN_NAME_CORREO = "correo"
    const val COLUMN_NAME_PASSWORD = "contrasenia"
  }
}
