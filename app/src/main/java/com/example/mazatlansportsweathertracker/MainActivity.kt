package com.example.mazatlansportsweathertracker

import Modelo.UsuarioDb
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

  private lateinit var txtUsername:TextInputEditText
  private lateinit var txtPassword:TextInputEditText
  private lateinit var btnLogin:Button
  private lateinit var btnRegistrar:Button
  private lateinit var usuarioDb: UsuarioDb

//  private var isRegistered = false

  companion object {
    lateinit var usuarioDb: UsuarioDb
  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        iniciarComponentes()
        usuarioDb = UsuarioDb(applicationContext)
        usuarioDb.openDataBase()

//        // Verificamos si el usuario ya se ha registrado y deshabilitamos el botón de registro si es así.
//        val sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
//        isRegistered = sharedPreferences.getBoolean("isRegistered", false)
//
//        if (isRegistered) {
//          btnRegistrar.isEnabled = false
//        }

        btnLogin.setOnClickListener { iniciarSesion() }
        btnRegistrar.setOnClickListener { registrar() }


    }

    fun iniciarComponentes() {
      btnLogin = findViewById(R.id.btnLogin)
      btnRegistrar = findViewById(R.id.btnRegistrar)
      txtUsername = findViewById(R.id.txtUsername)
      txtPassword = findViewById(R.id.txtPassword)
    }

    fun registrar() {
      val intent = Intent(this@MainActivity, RegistroActivity::class.java)
      startActivity(intent)
    }

    fun iniciarSesion() {
      val correo: String = txtUsername.text.toString()
      val password = txtPassword.text.toString()
      val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager

      MainActivity.usuarioDb = UsuarioDb(applicationContext)

      val siRegsitrado = MainActivity.usuarioDb.getUsuario(correo, password)
      MainActivity.usuarioDb.openDataBase()

      if (txtUsername.text!!.isEmpty()) {
        Toast.makeText(this, "Ingrese su Correo", Toast.LENGTH_SHORT).show()
        txtUsername.requestFocus()
        // Mostrar el teclado virtual
        imm.showSoftInput(txtUsername, InputMethodManager.SHOW_IMPLICIT)
      } else if (txtPassword.text!!.isEmpty()) {
        Toast.makeText(this, "Ingrese su contraseña", Toast.LENGTH_SHORT).show()
        txtPassword.requestFocus()
        // Mostrar el teclado virtual
        imm.showSoftInput(txtPassword, InputMethodManager.SHOW_IMPLICIT)
      } else if (siRegsitrado) {
        val intent = Intent(this@MainActivity, EleccionCanchasActivity::class.java)
        startActivity(intent)
//      txtUsername.setText("")
        txtPassword.setText("")
      } else {
        Toast.makeText(this, "Datos Incorrectos", Toast.LENGTH_SHORT).show()
      }

    }
}
