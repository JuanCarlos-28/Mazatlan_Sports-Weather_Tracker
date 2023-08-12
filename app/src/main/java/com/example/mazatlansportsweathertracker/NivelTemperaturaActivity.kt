package com.example.mazatlansportsweathertracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NivelTemperaturaActivity : AppCompatActivity() {

  private lateinit var btnRegresar: FloatingActionButton
  private lateinit var lblIndiceTemperatura: TextView
  // CAMBIOS NUEVOS
  private lateinit var lblTemperatura: TextView
  private lateinit var lblDescripcionTemperatura: TextView
  private lateinit var lblDescripcionTemperatura2: TextView

  private var nivelTemperatura: Double = 0.0  // Inicializa con un valor predeterminado

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_nivel_temperatura)
    obtenerNivelTemperaturaFromIntent()
    iniciarComponentes()
    iniciarUI()
    iniciarListeners()
  }

  private fun iniciarComponentes() {
    lblIndiceTemperatura = findViewById(R.id.lblIndiceTemperatura)
    btnRegresar = findViewById(R.id.btnRegresar)

    // CAMBIOS NUEVOS
    lblTemperatura = findViewById(R.id.lblTemperatura)
    lblDescripcionTemperatura = findViewById(R.id.lblDescripcionTemperatura)
    lblDescripcionTemperatura2 = findViewById(R.id.lblDescripcionTemperatura2)
  }

  private fun obtenerNivelTemperaturaFromIntent() {
    nivelTemperatura = intent.getDoubleExtra("Nivel_Temperatura", 0.0)
  }

  private fun iniciarUI() {
    lblIndiceTemperatura.text = "$nivelTemperatura° C"
    actualizarMensajes(nivelTemperatura.toString().toDouble())
  }

  private fun actualizarMensajes(nivelTemperatura: Double) {

    // Dar margen
    // Aplicar un margen de 16 píxeles a la izquierda del TextView
    val params = lblDescripcionTemperatura2.layoutParams as ViewGroup.MarginLayoutParams

    if (nivelTemperatura <= 17.0) {
      lblTemperatura.text = getString(R.string.temp_advertencia_baja)
      lblDescripcionTemperatura.text = getString(R.string.desc_temp_advertencia_baja)
      lblDescripcionTemperatura2.text = getString(R.string.desc_temp_advertencia_baja2)

      lblTemperatura.textSize = 35f
      lblDescripcionTemperatura.textSize = 22f
      lblDescripcionTemperatura2.textSize = 22.5f

      params.bottomMargin = 100 // Puedes ajustar el valor del margen según tus necesidades
      lblDescripcionTemperatura2.layoutParams = params

      lblTemperatura.setTextColor(ContextCompat.getColor(this, R.color.advertencia))

    } else if (nivelTemperatura > 17.0 && nivelTemperatura <= 30.0) {
      lblTemperatura.text = getString(R.string.temp_aceptable)
      lblDescripcionTemperatura.text = getString(R.string.desc_temp_aceptable)
      lblDescripcionTemperatura2.text = getString(R.string.desc_temp_aceptable2)

      lblTemperatura.textSize = 25f
      lblDescripcionTemperatura.textSize = 22f
      lblDescripcionTemperatura2.textSize = 22.5f

      params.bottomMargin = 140 // Puedes ajustar el valor del margen según tus necesidades
      lblDescripcionTemperatura2.layoutParams = params

      lblTemperatura.setTextColor(ContextCompat.getColor(this, R.color.estable))


    } else if (nivelTemperatura > 30.0 && nivelTemperatura <= 37.5) {

      lblTemperatura.setTextColor(ContextCompat.getColor(this, R.color.advertencia))

    } else if (nivelTemperatura > 37.5) {

      lblTemperatura.text = getString(R.string.temp_peligro)
      lblDescripcionTemperatura.text = getString(R.string.desc_temp_peligro)
      lblDescripcionTemperatura2.text = getString(R.string.desc_temp_peligro2)

      lblTemperatura.textSize = 38f
      lblDescripcionTemperatura.textSize = 19f
      lblDescripcionTemperatura2.textSize = 19.5f

      params.bottomMargin = 80 // Puedes ajustar el valor del margen según tus necesidades
      lblDescripcionTemperatura2.layoutParams = params

      lblTemperatura.setTextColor(ContextCompat.getColor(this, R.color.peligro))

    } else {
      lblTemperatura.text = ""
      lblDescripcionTemperatura.text = ""
      lblDescripcionTemperatura2.text = ""
    }

  }

  private fun iniciarListeners() {
    btnRegresar.setOnClickListener { onBackPressed() }
  }
}
