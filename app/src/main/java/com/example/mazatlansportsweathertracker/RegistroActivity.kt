package com.example.mazatlansportsweathertracker

import Modelo.UsuarioDb
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class RegistroActivity : AppCompatActivity() {
  private lateinit var txtNombre: TextInputEditText
  private lateinit var txtCorreo: TextInputEditText
  private lateinit var txtPassword: TextInputEditText
  private lateinit var txtPassword2: TextInputEditText
  private lateinit var lblEmpresas: TextView
  private lateinit var lblRegistro: TextView
  private lateinit var btnRegistrar: Button
  private lateinit var btnRegresar: Button
  private lateinit var usuario: Usuarios
  private lateinit var usuarioDb: UsuarioDb

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_registro)
    txtNombre = findViewById(R.id.txtNombre)
    txtCorreo = findViewById(R.id.txtCorreo)
    txtPassword = findViewById(R.id.txtContrasenia)
    txtPassword2 = findViewById(R.id.txtReContrasenia)
    lblEmpresas = findViewById(R.id.lblEmpresas)
    lblRegistro = findViewById(R.id.lblRegistro)
    btnRegistrar = findViewById(R.id.btnRegistrar)
    btnRegresar = findViewById(R.id.btnRegresar)
    btnRegresar.setOnClickListener(View.OnClickListener { onBackPressed() })
    btnRegistrar.setOnClickListener(View.OnClickListener { registro() })
  }

  private fun registro() {
    usuario = Usuarios()
    usuario.setNombre(txtNombre.text.toString())
    usuario.setCorreo(txtCorreo.text.toString())
    usuario.setContrasenia(txtPassword.text.toString())
    val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager

    if (correosExistentes()) {
      Toast.makeText(this, "Correo ya existente", Toast.LENGTH_SHORT).show()
    } else {
      if (txtNombre.text!!.isEmpty()) {
        Toast.makeText(this, "Capture su Nombre", Toast.LENGTH_SHORT).show()
        txtNombre.requestFocus()
        // Mostrar el teclado virtual
        imm.showSoftInput(txtNombre, InputMethodManager.SHOW_IMPLICIT)
      } else if (txtCorreo.text!!.isEmpty()){
        Toast.makeText(this, "Capture su Correo", Toast.LENGTH_SHORT).show()
        txtCorreo.requestFocus()
        // Mostrar el teclado virtual
        imm.showSoftInput(txtCorreo, InputMethodManager.SHOW_IMPLICIT)
      } else if (txtPassword.text!!.isEmpty()){
        Toast.makeText(this, "Capture su Contraseña", Toast.LENGTH_SHORT).show()
        txtPassword.requestFocus()
        // Mostrar el teclado virtual
        imm.showSoftInput(txtPassword, InputMethodManager.SHOW_IMPLICIT)
      } else if (txtPassword2.text!!.isEmpty()){
        Toast.makeText(this, "Reescriba su Contraseña", Toast.LENGTH_SHORT).show()
        txtPassword2.requestFocus()
        // Mostrar el teclado virtual
        imm.showSoftInput(txtPassword2, InputMethodManager.SHOW_IMPLICIT)
      } else if (!txtPassword2.text.toString().equals(txtPassword.text.toString())) {
        Toast.makeText(this, "Contraseña Incorrecta", Toast.LENGTH_SHORT).show()
        txtPassword2.requestFocus()
        // Mostrar el teclado virtual
        imm.showSoftInput(txtPassword2, InputMethodManager.SHOW_IMPLICIT)
      } else {
        if (!isEmailValid(txtCorreo.text.toString())) {
          Toast.makeText(this, "Ingrese un Correo válido", Toast.LENGTH_SHORT).show()
          txtCorreo.requestFocus()
          // Mostrar el teclado virtual
          imm.showSoftInput(txtCorreo, InputMethodManager.SHOW_IMPLICIT)
        } else {
          usuarioDb = UsuarioDb(applicationContext)
          usuarioDb.insertUsuario(usuario)
          Toast.makeText(applicationContext, "Se ha creado con exito ", Toast.LENGTH_SHORT).show()

//          // Aquí guardamos el estado del registro en SharedPreferences
//          val sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
//          val editor = sharedPreferences.edit()
//          editor.putBoolean("isRegistered", true)
//          editor.apply()

          setResult(RESULT_OK)
          finish()
        }
      }
    }
  }

  override fun onBackPressed() {
    val dialogo = AlertDialog.Builder(this)
    dialogo.setTitle("Mazatlán Sports Weather Tracker")
    dialogo.setMessage("¿Desea Salir?")
    dialogo.setPositiveButton("Confirmar") { _: DialogInterface, _: Int ->
      finish()
    }
    dialogo.setNegativeButton("Cancelar") { dialog: DialogInterface, _: Int ->
      dialog.dismiss()
    }
    dialogo.show()
  }

//  private fun validar(): Boolean {
//    if (txtNombre!!.text.toString() == "") return false
//    if (txtCorreo!!.text.toString() == "") return false
//    if (txtPassword!!.text.toString() == "") return false
//    return if (txtPassword2!!.text.toString() == txtPassword!!.text.toString()) true else false
//  }

  private fun isEmailValid(email: String): Boolean {
    val regex = Regex("^([a-zA-Z0-9_\\.-]+)@([\\da-zA-Z\\.-]+)\\.([a-zA-Z\\.]{2,6})$")
    return regex.matches(email)
  }

  private fun correosExistentes(): Boolean {
    usuarioDb = UsuarioDb(applicationContext)
    val correoExistente = usuarioDb.obtenerUser(txtCorreo.text.toString())
    usuarioDb.openDataBase()
    return correoExistente
  }
}
