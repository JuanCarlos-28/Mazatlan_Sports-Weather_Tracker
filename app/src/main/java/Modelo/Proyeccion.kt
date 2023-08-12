package Modelo

import android.database.Cursor
import com.example.mazatlansportsweathertracker.Usuarios

interface Proyeccion {
  fun obtenerUser(correo: String?): Boolean
  fun getUsuario(nombre: String?, contrasenia: String?): Boolean
  fun allUsuarios(): ArrayList<Usuarios?>?
  fun readUsuario(cursor: Cursor?): Usuarios?
}

