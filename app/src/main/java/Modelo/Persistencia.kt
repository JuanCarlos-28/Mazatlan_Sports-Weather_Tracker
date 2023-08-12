package Modelo

import com.example.mazatlansportsweathertracker.Usuarios

interface Persistencia {
  fun openDataBase()
  fun closeDataBase()
  fun insertUsuario(usuario: Usuarios?): Long
}
