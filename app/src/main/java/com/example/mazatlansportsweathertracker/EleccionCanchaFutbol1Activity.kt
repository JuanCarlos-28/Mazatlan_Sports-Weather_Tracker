package com.example.mazatlansportsweathertracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

// Importa FirebaseUtils en la parte superior de tu actividad
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class EleccionCanchaFutbol1Activity : AppCompatActivity() {

  private var isRayosUVSelected: Boolean = false
  private var isTemperaturaSelected: Boolean = false
  private var isHumedadSelected: Boolean = false
  private var isLluviaSelected: Boolean = false

  private lateinit var btnRayosUV: CardView
  private lateinit var btnTemperatura: CardView
  private lateinit var btnHumedad: CardView
  private lateinit var btnLluvia: CardView
  private lateinit var btnRegresar: FloatingActionButton

  val database: FirebaseDatabase = FirebaseDatabase.getInstance()
  val rayosUvReference: DatabaseReference = database.reference.child("indiceuv")
  val temperaturaReference: DatabaseReference = database.reference.child("temperatura")
  val humedadReference: DatabaseReference = database.reference.child("humedad")
  val nivelaguaReference: DatabaseReference = database.reference.child("nivelagua")


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_eleccion_cancha_futbol1)
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
    obtenerIndiceUV()
  }

  private fun navigateToTemperatura() {
    obtenerTemperatura()
  }

  private fun navigateToHumedad() {
    obtenerHumedad()
  }

  private fun navigateToLluvia() {
    obtenerLluvia()
  }

  private fun obtenerIndiceUV() {
    rayosUvReference.addListenerForSingleValueEvent(object : ValueEventListener {
      override fun onDataChange(snapshot: DataSnapshot) {
        val rayosUV = snapshot.getValue(String::class.java)
        // Haz algo con los datos obtenidos
        Log.d("Rayos UV", "Rayos UV: $rayosUV")
        val intent = Intent(this@EleccionCanchaFutbol1Activity, NivelRayosUvActivity::class.java)
        intent.putExtra("INDICE_UV", rayosUV.toString().toDouble())
        startActivity(intent)
      }

      override fun onCancelled(error: DatabaseError) {
        Log.d("Rayos UV", "Error Rayos UV: $error")
      }
    })
  }

  private fun obtenerTemperatura() {
    temperaturaReference.addListenerForSingleValueEvent(object : ValueEventListener {
      override fun onDataChange(snapshot: DataSnapshot) {
        val temperatura = snapshot.getValue(String::class.java)
        // Haz algo con los datos obtenidos
        Log.d("Temperatura", "Temperatura: $temperatura")
        val intent = Intent(this@EleccionCanchaFutbol1Activity, NivelTemperaturaActivity::class.java)
        intent.putExtra("Nivel_Temperatura", temperatura.toString().toDouble())
        startActivity(intent)
      }

      override fun onCancelled(error: DatabaseError) {
        Log.d("Temperatura", "Error Temperatura: $error")
      }
    })
  }

  private fun obtenerHumedad() {
    humedadReference.addListenerForSingleValueEvent(object : ValueEventListener {
      override fun onDataChange(snapshot: DataSnapshot) {
        val humedad = snapshot.getValue(String::class.java)
        // Haz algo con los datos obtenidos
        Log.d("Humedad", "Humedad: $humedad")
        val intent = Intent(this@EleccionCanchaFutbol1Activity, NivelHumedadActivity::class.java)
        intent.putExtra("Nivel_Humedad", humedad.toString().toDouble())
        startActivity(intent)
      }

      override fun onCancelled(error: DatabaseError) {
        Log.d("Humedad", "Error Humedad: $error")
      }
    })
  }

  private fun obtenerLluvia() {
    nivelaguaReference.addListenerForSingleValueEvent(object : ValueEventListener {
      override fun onDataChange(snapshot: DataSnapshot) {
        val lluvia = snapshot.getValue(String::class.java)
        // Haz algo con los datos obtenidos
        Log.d("Lluvia", "Lluvia: $lluvia")
        val intent = Intent(this@EleccionCanchaFutbol1Activity, NivelLluviaActivity::class.java)
        intent.putExtra("Nivel_Lluvia", lluvia.toString().toDouble())
        startActivity(intent)
      }

      override fun onCancelled(error: DatabaseError) {
        Log.d("Lluvia", "Error Lluvia: $error")
      }
    })
  }

}
