package com.example.mazatlansportsweathertracker

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class EleccionCanchasActivity : AppCompatActivity() {

  private lateinit var btnCanchaFutbol1: CardView
  private lateinit var btnCanchaFutbol2: CardView
  private lateinit var btnCanchaBeisbol1: CardView
  private lateinit var btnCanchaBeisbol2: CardView
  private lateinit var btnCanchaBasquetbol1: CardView
  private lateinit var btnCanchaBasquetbol2: CardView
  private lateinit var btnCanchaVoleibol1: CardView
  private lateinit var btnCanchaVoleibol2: CardView
  private lateinit var btnCanchaTenis1: CardView
  private lateinit var btnCanchaTenis2: CardView

  private lateinit var btnRegresar: FloatingActionButton

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_eleccion_canchas)
    iniciarComponentes()
    iniciarListeners()
  }

  private fun iniciarComponentes() {
    btnCanchaFutbol1 = findViewById(R.id.btnCanchaFutbol1)
    btnCanchaFutbol2 = findViewById(R.id.btnCanchaFutbol2)
    btnCanchaBeisbol1 = findViewById(R.id.btnCanchaBeisbol1)
    btnCanchaBeisbol2 = findViewById(R.id.btnCanchaBeisbol2)
    btnCanchaBasquetbol1 = findViewById(R.id.btnCanchaBasquetbol1)
    btnCanchaBasquetbol2 = findViewById(R.id.btnCanchaBasquetbol2)
    btnCanchaVoleibol1 = findViewById(R.id.btnCanchaVoleibol1)
    btnCanchaVoleibol2 = findViewById(R.id.btnCanchaVoleibol2)
    btnCanchaTenis1 = findViewById(R.id.btnCanchaTenis1)
    btnCanchaTenis2 = findViewById(R.id.btnCanchaTenis2)
    btnRegresar = findViewById(R.id.btnRegresar)
  }

  private fun iniciarListeners() {
    btnCanchaFutbol1.setOnClickListener {
      navigateToEleccionActividadCanchaFutbol1()
    }
    btnCanchaFutbol2.setOnClickListener {
      navigateToEleccionActividadCanchaFutbol2()
    }
    btnCanchaBeisbol1.setOnClickListener {
      navigateToEleccionActividadCanchaBeisbol1()
    }
    btnCanchaBeisbol2.setOnClickListener {
      navigateToEleccionActividadCanchaBeisbol2()
    }
    btnCanchaBasquetbol1.setOnClickListener {
      navigateToEleccionActividadCanchaBasquetbol1()
    }
    btnCanchaBasquetbol2.setOnClickListener {
      navigateToEleccionActividadCanchaBasquetbol2()
    }
    btnCanchaVoleibol1.setOnClickListener {
      navigateToEleccionActividadCanchaVoleibol1()
    }
    btnCanchaVoleibol2.setOnClickListener {
      navigateToEleccionActividadCanchaVoleibol2()
    }
    btnCanchaTenis1.setOnClickListener {
      navigateToEleccionActividadCanchaTenis1()
    }
    btnCanchaTenis2.setOnClickListener {
      navigateToEleccionActividadCanchaTenis2()
    }
    btnRegresar.setOnClickListener {
      onBackPressed()
    }
  }

  private fun navigateToEleccionActividadCanchaFutbol1() {
    intent = Intent(this, EleccionCanchaFutbol1Activity::class.java)
    startActivity(intent)
  }

  private fun navigateToEleccionActividadCanchaFutbol2() {
    intent = Intent(this, EleccionCanchaFutbol2Activity::class.java)
    startActivity(intent)
  }

  private fun navigateToEleccionActividadCanchaBeisbol1() {
    intent = Intent(this, EleccionCanchaBeisbol1Activity::class.java)
    startActivity(intent)
  }

  private fun navigateToEleccionActividadCanchaBeisbol2() {
    intent = Intent(this, EleccionCanchaBeisbol2Activity::class.java)
    startActivity(intent)
  }

  private fun navigateToEleccionActividadCanchaBasquetbol1() {
    intent = Intent(this, EleccionCanchaBasquetbol1Activity::class.java)
    startActivity(intent)
  }

  private fun navigateToEleccionActividadCanchaBasquetbol2() {
    intent = Intent(this, EleccionCanchaBasquetbol2Activity::class.java)
    startActivity(intent)
  }

  private fun navigateToEleccionActividadCanchaVoleibol1() {
    intent = Intent(this, EleccionCanchaVoleibol1Activity::class.java)
    startActivity(intent)
  }

  private fun navigateToEleccionActividadCanchaVoleibol2() {
    intent = Intent(this, EleccionCanchaVoleibol2Activity::class.java)
    startActivity(intent)
  }

  private fun navigateToEleccionActividadCanchaTenis1() {
    intent = Intent(this, EleccionCanchaTenis1Activity::class.java)
    startActivity(intent)
  }

  private fun navigateToEleccionActividadCanchaTenis2() {
    intent = Intent(this, EleccionCanchaTenis2Activity::class.java)
    startActivity(intent)
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
}
