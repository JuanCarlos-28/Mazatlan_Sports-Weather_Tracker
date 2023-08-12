package com.example.mazatlansportsweathertracker

class Usuarios {
  //Encapsulamiento
  private var idUsuario: Int
  private var nombre: String
  private var correo: String
  private var contrasenia: String

  constructor() {
    idUsuario = 0
    nombre = ""
    correo = ""
    contrasenia = ""
  }

  constructor(idUsuario: Int, nombre: String, correo: String, contrasenia: String) {
    this.idUsuario = idUsuario
    this.nombre = nombre
    this.correo = correo
    this.contrasenia = contrasenia
  }

  //Encapsulamiento
  fun getIdUsuario(): Int {
    return idUsuario
  }

  fun setIdUsuario(id: Int) {
    this.idUsuario = id
  }

  fun getNombre(): String? {
    return nombre
  }

  fun setNombre(nombre: String?) {
    this.nombre = nombre!!
  }

  fun getCorreo(): String? {
    return correo
  }

  fun setCorreo(correo: String?) {
    this.correo = correo!!
  }

  fun getContrasenia(): String? {
    return contrasenia
  }

  fun setContrasenia(contrasenia: String?) {
    this.contrasenia = contrasenia!!
  }

}

