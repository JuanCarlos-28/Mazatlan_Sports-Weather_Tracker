package com.example.mazatlansportsweathertracker

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class SplashActivity : AppCompatActivity() {
  private lateinit var lblNombreTeamNegro: TextView
  private lateinit var imgLogoBlanco: ImageView
  private lateinit var imgLogoAzul: ImageView
  private lateinit var imgLogoNegro: ImageView
  private lateinit var imgLogoNegro2: ImageView
  private var pasoActual = 0
  private val ordenImagen = intArrayOf(2, 3, 4)

  @RequiresApi(Build.VERSION_CODES.M)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_splash)
    iniciarComponentes()

    // Iniciar la secuencia de cambio de imágenes
    mostrarImagenSig()


  }

  private fun iniciarComponentes() {

    lblNombreTeamNegro = findViewById(R.id.lblNombreTeamNegro)


    imgLogoBlanco = findViewById(R.id.imgLogoBlanco)
    imgLogoAzul = findViewById(R.id.imgLogoAzul)
    imgLogoNegro = findViewById(R.id.imgLogoNegro)
    imgLogoNegro2 = findViewById(R.id.imgLogoNegro2)
  }

  private fun mostrarImagenSig() {
    val handler = Handler()
    handler.postDelayed({

      // Establecer todas las imágenes invisibles
      imgLogoBlanco.setVisibility(View.INVISIBLE)
      imgLogoAzul.setVisibility(View.INVISIBLE)
      imgLogoNegro.setVisibility(View.INVISIBLE)
      imgLogoNegro2.setVisibility(View.INVISIBLE)

      lblNombreTeamNegro.setVisibility(View.INVISIBLE)



      when (ordenImagen.get(pasoActual)) {
        2 -> imgLogoAzul.setVisibility(View.VISIBLE)
        3 -> imgLogoBlanco.setVisibility(View.VISIBLE)
        4 -> {
          imgLogoNegro2.setVisibility(View.VISIBLE)
          lblNombreTeamNegro.setVisibility(View.VISIBLE)
        }

      }
      pasoActual++
      if (pasoActual < ordenImagen.size) {
        mostrarImagenSig()
      } else {
        pasoActual = 0 // Reiniciar el índice al final de la secuencia
        val nextActivityHandler = Handler()
        nextActivityHandler.postDelayed({
          val intent = Intent(this@SplashActivity, MainActivity::class.java)
          startActivity(intent)
          finish()
        }, 500)
      }

    }, 800) // 0.8 segundos de retraso para cada cambio de imagen
  }


}
