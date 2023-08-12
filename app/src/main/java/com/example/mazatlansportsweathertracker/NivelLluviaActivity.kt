package com.example.mazatlansportsweathertracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.Formatter
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.Format
import java.text.NumberFormat
import java.util.Locale

class NivelLluviaActivity : AppCompatActivity() {

  private lateinit var btnRegresar: FloatingActionButton
  private lateinit var lblIndiceLluvia: TextView

  // CAMBIOS NUEVOS
  private lateinit var lblLluvia: TextView
  private lateinit var lblDescripcionLluvia: TextView
  private lateinit var lblDescripcionLluvia2: TextView

  private var nivelLluvia: Double = 0.0  // Inicializa con un valor predeterminado
  private var nivelLluviaMax: Double = 750.00

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_nivel_lluvia)
    obtenerNivelLluviaFromIntent()
    iniciarComponentes()
    iniciarUI()
    iniciarListeners()
  }

  private fun iniciarComponentes() {
    lblIndiceLluvia = findViewById(R.id.lblIndiceLluvia)
    btnRegresar = findViewById(R.id.btnRegresar)

    // CAMBIOS NUEVOS
    lblLluvia = findViewById(R.id.lblLluvia)
    lblDescripcionLluvia = findViewById(R.id.lblDescripcionLluvia)
    lblDescripcionLluvia2 = findViewById(R.id.lblDescripcionLluvia2)

  }

  private fun obtenerNivelLluviaFromIntent() {
    nivelLluvia = intent.getDoubleExtra("Nivel_Lluvia", 0.0)
  }

  private fun iniciarUI() {
    val numberFormat = NumberFormat.getNumberInstance(Locale.US)
    numberFormat.maximumFractionDigits = 1

    nivelLluvia = (nivelLluvia*100) / nivelLluviaMax
    val nivelLuviaFormateado = numberFormat.format(nivelLluvia)
    nivelLluvia = nivelLuviaFormateado.toString().toDouble()

    lblIndiceLluvia.text = "$nivelLluvia %"
    actualizarMensajes(nivelLluvia.toString().toDouble())
  }

  private fun actualizarMensajes(nivelLluvia: Double) {

    // Dar margen
    val params = lblDescripcionLluvia2.layoutParams as ViewGroup.MarginLayoutParams

    if (nivelLluvia <= 15.0) {
      lblLluvia.text = getString(R.string.lluvia_sin_amenaza)
      lblDescripcionLluvia.text = getString(R.string.desc_lluvia_sin_amenaza)
      lblDescripcionLluvia2.text = getString(R.string.desc_lluvia_sin_amenaza2)

      lblLluvia.textSize = 23f
      lblDescripcionLluvia.textSize = 20f
      lblDescripcionLluvia2.textSize = 19f

      params.bottomMargin = 120 // Puedes ajustar el valor del margen según tus necesidades
      lblDescripcionLluvia2.layoutParams = params

      lblLluvia.setTextColor(ContextCompat.getColor(this, R.color.estable))

    } else if (nivelLluvia > 15.0 && nivelLluvia <= 60.0) {
      lblLluvia.text = getString(R.string.lluvia_intervalos)
      lblDescripcionLluvia.text = getString(R.string.desc_lluvia_intervalos)
      lblDescripcionLluvia2.text = getString(R.string.desc_lluvia_intervalos2)

      lblLluvia.textSize = 28f
      lblDescripcionLluvia.textSize = 18.5f
      lblDescripcionLluvia2.textSize = 16.5f

      params.bottomMargin = 100 // Puedes ajustar el valor del margen según tus necesidades
      lblDescripcionLluvia2.layoutParams = params

      lblLluvia.setTextColor(ContextCompat.getColor(this, R.color.advertencia))

    } else if (nivelLluvia > 60.0) {
      lblDescripcionLluvia.text = getString(R.string.desc_lluvia_peligro)

      lblLluvia.textSize = 30f
      lblDescripcionLluvia.textSize = 17.5f
      lblDescripcionLluvia2.textSize = 17f

        if (nivelLluvia >= 100.0) {
          lblIndiceLluvia.text = "100 %"
        }

      params.bottomMargin = 170 // Puedes ajustar el valor del margen según tus necesidades
      lblDescripcionLluvia2.layoutParams = params

      lblLluvia.setTextColor(ContextCompat.getColor(this, R.color.peligro))
    } else {
      lblLluvia.text = ""
      lblDescripcionLluvia.text = ""
      lblDescripcionLluvia2.text = ""
    }

  }

  private fun iniciarListeners() {
    btnRegresar.setOnClickListener { onBackPressed() }
  }
}
