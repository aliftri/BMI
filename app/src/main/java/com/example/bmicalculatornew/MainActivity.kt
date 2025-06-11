package com.example.bmicalculatornew

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BMICalculatorScreen()
        }
    }
}

@Composable
fun BMICalculatorScreen() {
    var berat by remember { mutableStateOf("") }
    var tinggi by remember { mutableStateOf("") }
    var hasilBmi by remember { mutableStateOf("") }
    var kategoriBmi by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Kalkulator BMI",
            fontSize = 24.sp,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
            color = Color(0xFF212121)
        )

        OutlinedTextField(
            value = berat,
            onValueChange = { berat = it },
            label = { Text("Berat (kg)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF1976D2),
                unfocusedBorderColor = Color(0xFF757575)
            )
        )

        OutlinedTextField(
            value = tinggi,
            onValueChange = { tinggi = it },
            label = { Text("Tinggi (cm)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF1976D2),
                unfocusedBorderColor = Color(0xFF757575)
            )
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = {
                    if (berat.isEmpty() || tinggi.isEmpty()) {
                        hasilBmi = "Isi semua kolom!"
                        kategoriBmi = ""
                        return@Button
                    }

                    val beratValue = berat.toDoubleOrNull()
                    val tinggiValue = tinggi.toDoubleOrNull()

                    if (beratValue == null || tinggiValue == null || beratValue <= 0 || tinggiValue <= 0) {
                        hasilBmi = "Input tidak valid!"
                        kategoriBmi = ""
                        return@Button
                    }

                    val tinggiMeter = tinggiValue / 100.0
                    val bmi = beratValue / (tinggiMeter * tinggiMeter)

                    hasilBmi = "BMI: %.1f".format(bmi)
                    kategoriBmi = when {
                        bmi < 18.5 -> "Kurus"
                        bmi < 25.0 -> "Normal"
                        bmi < 30.0 -> "Gemuk"
                        else -> "Obesitas"
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2)),
                modifier = Modifier.weight(1f)
            ) {
                Text("Hitung", color = Color.White)
            }

            Button(
                onClick = {
                    berat = ""
                    tinggi = ""
                    hasilBmi = ""
                    kategoriBmi = ""
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF757575)),
                modifier = Modifier.weight(1f)
            ) {
                Text("Reset", color = Color.White)
            }
        }

        Text(
            text = hasilBmi,
            fontSize = 18.sp,
            color = Color(0xFF212121),
            textAlign = TextAlign.Center
        )

        Text(
            text = kategoriBmi,
            fontSize = 18.sp,
            color = Color(0xFF212121),
            textAlign = TextAlign.Center
        )
    }
}