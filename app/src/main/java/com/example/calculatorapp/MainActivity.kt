package com.example.calculatorapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.calculatorapp.ui.theme.CalculatorAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CalculatorApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CalculatorView(modifier: Modifier = Modifier) {
    var displayText by remember { mutableStateOf("0") }
    val calculatorBrain = remember { CalculatorBrain() }

    fun setDisplay(value: Double) {
        var intResult = value.toInt()
        var resultTxt = ""
        if (value == intResult.toDouble()) {
            resultTxt = intResult.toString()
        } else {
            resultTxt = value.toString()

        }
        displayText = resultTxt
    }

    fun getDisplay(): Double {
        return displayText.toDouble()

    }

    var userIsInTheMiddleOfIntroducing = true
}

