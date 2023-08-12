package com.example.mazatlansportsweathertracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NivelRayosUvActivity : AppCompatActivity() {
  private lateinit var btnRegresar: FloatingActionButton

  // CAMBIOS NUEVOS
  private lateinit var lblIndiceRayosUV: TextView
  private lateinit var lblRayosUV: TextView
  private lateinit var lblDescripcionRayosUV: TextView
  private lateinit var lblDescripcionRayosUV2: TextView

  private var indiceUV: Double = 0.0  // Inicializa con un valor predeterminado


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_nivel_rayos_uv)
    iniciarComponentes()
    obtenerIndiceUVFromIntent()
    iniciarUI()
    iniciarListeners()
  }

  private fun iniciarComponentes() {
    // CAMBIOS NUEVOS
    lblIndiceRayosUV = findViewById(R.id.lblIndiceRayosUV)
    lblRayosUV = findViewById(R.id.lblRayosUV)
    lblDescripcionRayosUV = findViewById(R.id.lblDescripcionRayosUV)
    lblDescripcionRayosUV2 = findViewById(R.id.lblDescripcionRayosUV2)

    btnRegresar = findViewById(R.id.btnRegresar)
  }

  private fun obtenerIndiceUVFromIntent() {
    indiceUV = intent.getDoubleExtra("INDICE_UV", 0.0)
    Log.d("indiceFromIntent", "indiceUV: $indiceUV")
  }

  private fun iniciarUI() {
    lblIndiceRayosUV.text = indiceUV.toString()
    actualizarMensajes(indiceUV.toString().toDouble())
  }

  private fun actualizarMensajes(indiceUV: Double) {

    if (indiceUV in 0.0..2.0) {
      lblRayosUV.text = getString(R.string.rayos_uv_bajos)
      lblDescripcionRayosUV.text = getString(R.string.desc_rayos_uv_bajos)
      lblDescripcionRayosUV2.text = getString(R.string.desc_rayos_uv_bajos2)

      lblRayosUV.setTextColor(ContextCompat.getColor(this, R.color.estable))

    } else if (indiceUV > 2.0 && indiceUV <= 8.0) {
      lblRayosUV.text = getString(R.string.rayos_uv_moderado)
      lblDescripcionRayosUV.text = getString(R.string.desc_rayos_uv_moderado)
      lblDescripcionRayosUV2.text = getString(R.string.desc_rayos_uv_moderado2)

      lblRayosUV.textSize = 28f
      lblDescripcionRayosUV.textSize = 17f
      lblDescripcionRayosUV2.textSize = 18f

      lblRayosUV.setTextColor(ContextCompat.getColor(this, R.color.advertencia))

    } else if (indiceUV > 8.0 && indiceUV <= 12.0) {
      lblRayosUV.text = getString(R.string.rayos_uv_altos)
      lblDescripcionRayosUV.text = getString(R.string.desc_rayos_uv_altos)
      lblDescripcionRayosUV2.text = getString(R.string.desc_rayos_uv_altos2)

      lblRayosUV.textSize = 28f
      lblDescripcionRayosUV.textSize = 18f
      lblDescripcionRayosUV2.textSize = 18f

      lblRayosUV.setTextColor(ContextCompat.getColor(this, R.color.peligro))

    } else {
      lblRayosUV.text = ""
      lblDescripcionRayosUV.text = ""
      lblDescripcionRayosUV2.text = ""
    }

  }

  private fun iniciarListeners() {
    btnRegresar.setOnClickListener { onBackPressed() }
  }

}
