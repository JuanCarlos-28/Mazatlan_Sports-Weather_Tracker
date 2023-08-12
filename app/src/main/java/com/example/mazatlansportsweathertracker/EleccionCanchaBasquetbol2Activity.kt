package com.example.mazatlansportsweathertracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

class EleccionCanchaBasquetbol2Activity : AppCompatActivity() {

  private var isRayosUVSelected: Boolean = false
  private var isTemperaturaSelected: Boolean = false
  private var isHumedadSelected: Boolean = false
  private var isLluviaSelected: Boolean = false

  private lateinit var btnRayosUV: CardView
  private lateinit var btnTemperatura: CardView
  private lateinit var btnHumedad: CardView
  private lateinit var btnLluvia: CardView
  private lateinit var btnRegresar: FloatingActionButton

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_eleccion_cancha_basquetbol2)
    iniciarComponentes()
    iniciarListeners()
  }

  private fun iniciarComponentes() {
    btnRayosUV = findViewById(R.id.btnRayosUV)
    btnTemperatura = findViewById(R.id.btnTemperatura)
    btnHumedad = findViewById(R.id.btnHumedad)
    btnLluvia = findViewById(R.id.btnLluvia)
    btnRegresar = findViewById(R.id.btnRegresar)
  }

  private fun iniciarListeners() {
    btnRayosUV.setOnClickListener {
      changeButtonRayosUV()
      setButtonColor()
      navigateToRayosUV()
    }
    btnTemperatura.setOnClickListener {
      changeButtonTemperatura()
      setButtonColor()
      navigateToTemperatura()
    }
    btnHumedad.setOnClickListener {
      changeButtonHumedad()
      setButtonColor()
      navigateToHumedad()
    }
    btnLluvia.setOnClickListener {
      changeButtonLluvia()
      setButtonColor()
      navigateToLluvia()
    }
    btnRegresar.setOnClickListener {
      onBackPressed()
    }
  }

  private fun changeButtonRayosUV() {
    isRayosUVSelected = !isRayosUVSelected
  }

  private fun changeButtonTemperatura() {
    isTemperaturaSelected = !isTemperaturaSelected
  }

  private fun changeButtonHumedad() {
    isHumedadSelected = !isHumedadSelected
  }

  private fun changeButtonLluvia() {
    isLluviaSelected = !isLluviaSelected
  }

  private fun setButtonColor() {
    btnRayosUV.setCardBackgroundColor(getBackgroundRayosUV(isRayosUVSelected))
    btnTemperatura.setCardBackgroundColor(getBackgroundTemperatura(isTemperaturaSelected))
    btnHumedad.setCardBackgroundColor(getBackgroundHumedad(isHumedadSelected))
    btnLluvia.setCardBackgroundColor(getBackgroundLluvia(isLluviaSelected))
  }

  private fun getBackgroundRayosUV(isSelectedComponent: Boolean): Int {
    val colorReference = if (isSelectedComponent) {
      R.color.purpleSecondary
    } else {
      R.color.purplePrimary
    }

    return ContextCompat.getColor(this, colorReference)
  }

  private fun getBackgroundTemperatura(isSelectedComponent: Boolean): Int {
    val colorReference = if (isSelectedComponent) {
      R.color.yellowSecondary
    } else {
      R.color.yellowPrimary
    }

    return ContextCompat.getColor(this, colorReference)
  }

  private fun getBackgroundHumedad(isSelectedComponent: Boolean): Int {
    val colorReference = if (isSelectedComponent) {
      R.color.greenSecondary
    } else {
      R.color.greenPrimary
    }

    return ContextCompat.getColor(this, colorReference)
  }

  private fun getBackgroundLluvia(isSelectedComponent: Boolean): Int {
    val colorReference = if (isSelectedComponent) {
      R.color.blueSecondary
    } else {
      R.color.bluePrimary
    }

    return ContextCompat.getColor(this, colorReference)
  }

  private fun navigateToRayosUV() {
    val intent = Intent(this, NivelRayosUvActivity::class.java)
    startActivity(intent)
  }

  private fun navigateToTemperatura() {
    val intent = Intent(this, NivelTemperaturaActivity::class.java)
    startActivity(intent)
  }

  private fun navigateToHumedad() {
    val intent = Intent(this, NivelHumedadActivity::class.java)
    startActivity(intent)
  }

  private fun navigateToLluvia() {
    val intent = Intent(this, NivelLluviaActivity::class.java)
    startActivity(intent)
  }

}
