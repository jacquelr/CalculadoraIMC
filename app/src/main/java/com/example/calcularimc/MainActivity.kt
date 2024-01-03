package com.example.calcularimc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import java.math.BigDecimal
import java.math.RoundingMode

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun calculateBMI(view: View){
        val height = findViewById<EditText>(R.id.activity_main_height)
        val weight = findViewById<EditText>(R.id.activity_main_weight)

        var isHeight = true
        var isWeight = true
        var rangeMessage = ""

        height.error = null
        weight.error = null

        if(height.text.toString().trim().isEmpty()){
            isHeight =false
            height.error = "Campo Requerido"
        }
        if(weight.text.toString().trim().isEmpty()){
            isWeight = false
            weight.error ="Campo Requerido"
        }
        if(isHeight&&isWeight){

            val heightValue = height.text.toString().trim().toBigDecimal()
            val weightValue = weight.text.toString().trim().toBigDecimal()

            //var result : BigDecimal = weightValue / (heightValue.pow(2))
            val result: BigDecimal = weightValue.divide(
                heightValue.pow(2),
                2, // 2 decimales de precisión
                RoundingMode.HALF_UP
            )
            rangeMessage = when {
                result >= BigDecimal.ZERO && result <= BigDecimal("15.0") -> "Delgadez muy severa"
                result > BigDecimal("15.0") && result <= BigDecimal("15.9") -> "Delgadez severa"
                result > BigDecimal("16.0") && result <= BigDecimal("18.4") -> "Delgadez"
                result > BigDecimal("18.5") && result <= BigDecimal("24.9") -> "Peso saludable"
                result > BigDecimal("25.0") && result <= BigDecimal("29.9") -> "Sobrepeso"
                result > BigDecimal("30.0") && result <= BigDecimal("34.9") -> "Obesidad moderada"
                result > BigDecimal("35.0") && result <= BigDecimal("39.9") -> "Obesidad severa"
                result > BigDecimal("40.0") -> "Obesidad muy severa (obesidad mórbida)"
                else -> "Valores Inválidos"
            }

            Toast.makeText(
                this@MainActivity,
                "$result : $rangeMessage",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}