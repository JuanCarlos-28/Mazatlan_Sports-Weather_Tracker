package com.example.mazatlansportsweathertracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NivelHumedadActivity : AppCompatActivity() {

  private lateinit var btnRegresar: FloatingActionButton
  private lateinit var lblIndiceHumedad: TextView

  // CAMBIOS NUEVOS
  private lateinit var lblHumedad: TextView
  private lateinit var lblDescripcionHumedad: TextView
  private lateinit var lblDescripcionHumedad2: TextView

  private var nivelHumedad: Double = 0.0  // Inicializa con un valor predeterminado

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_nivel_humedad)
    obtenerNivelHumedadFromIntent()
    iniciarComponentes()
    iniciarUI()
    iniciarListeners()
  }

  private fun iniciarComponentes() {
    lblIndiceHumedad = findViewById(R.id.lblIndiceHumedad)
    btnRegresar = findViewById(R.id.btnRegresar)

    // CAMBIOS NUEVOS
    lblHumedad = findViewById(R.id.lblHumedad)
    lblDescripcionHumedad = findViewById(R.id.lblDescripcionHumedad)
    lblDescripcionHumedad2 = findViewById(R.id.lblDescripcionHumedad2)

  }

  private fun obtenerNivelHumedadFromIntent() {
    nivelHumedad = intent.getDoubleExtra("Nivel_Humedad", 0.0)
  }

  private fun iniciarUI() {
    lblIndiceHumedad.text = "$nivelHumedad %"
    actualizarMensajes(nivelHumedad.toString().toDouble())
  }

  private fun actualizarMensajes(nivelHumedad: Double) {

    // Dar margen
    val params = lblDescripcionHumedad2.layoutParams as ViewGroup.MarginLayoutParams

    if (nivelHumedad <= 30.0) {
      lblHumedad.text = getString(R.string.humedad_advertencia)
      lblDescripcionHumedad.text = getString(R.string.desc_humedad_advertencia)
      lblDescripcionHumedad2.text = getString(R.string.desc_humedad_advertencia2)

      lblHumedad.textSize = 35f
      lblDescripcionHumedad.textSize = 19f
      lblDescripcionHumedad2.textSize = 21f

      params.bottomMargin = 165 // Puedes ajustar el valor del margen según tus necesidades
      lblDescripcionHumedad2.layoutParams = params

      lblHumedad.setTextColor(ContextCompat.getColor(this, R.color.advertencia))

    } else if (nivelHumedad > 30.0 && nivelHumedad <= 60.0) {
      lblHumedad.text = getString(R.string.humedad_aceptable)
      lblDescripcionHumedad.text = getString(R.string.desc_humedad_aceptable)
      lblDescripcionHumedad2.text = getString(R.string.desc_humedad_aceptable2)

      lblHumedad.textSize = 26f
      lblDescripcionHumedad.textSize = 18.8f
      lblDescripcionHumedad2.textSize = 19.3f

      params.bottomMargin = 75 // Puedes ajustar el valor del margen según tus necesidades
      lblDescripcionHumedad2.layoutParams = params

      lblHumedad.setTextColor(ContextCompat.getColor(this, R.color.estable))

    } else if (nivelHumedad > 60.0) {

      lblHumedad.setTextColor(ContextCompat.getColor(this, R.color.peligro))

    } else {
      lblHumedad.text = ""
      lblDescripcionHumedad.text = ""
      lblDescripcionHumedad2.text = ""
    }

  }

  private fun iniciarListeners() {
    btnRegresar.setOnClickListener { onBackPressed() }
  }
}
