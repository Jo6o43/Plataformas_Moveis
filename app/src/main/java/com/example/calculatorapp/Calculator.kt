package com.example.calculatorapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calculatorapp.ui.theme.CalcButton.CalcButton
import com.example.calculatorapp.ui.theme.CalculatorAppTheme

@Composable
fun CalculatorApp(modifier: Modifier = Modifier) {
    var displayText by remember { mutableStateOf("0") }
    var operand by remember { mutableStateOf(0.0) }
    var operator by remember { mutableStateOf("") }
    var userIsInTheMiddleOfIntroduction = true

    fun getDisplayText(): Double {
        return displayText.toDouble()
    }
    fun setDisplay(value: Double) {

        if (value % 1 == 0.0) {
            displayText = value.toInt().toString()
        }

        displayText = value.toString()
    }

    val onNumPressed: (String) -> Unit = { num ->
        if (userIsInTheMiddleOfIntroduction) {
            if (displayText == "0") {
                if (num == ".") { //Se pressionar o ponto primeiro coloca um 0 à frente
                    displayText = "0."
                } else {
                    displayText = num
                }
            } else {
                if (num == ".") { //Verifica se a tecla pressionada é a do ponto
                    if (!displayText.contains('.')) { //Verifica se já existe um ponte
                        displayText += num
                    }
                } else {
                    displayText += num
                }
            }

        } else {
            displayText = num
        }
        userIsInTheMiddleOfIntroduction = true
    }

    val clearCalculator: (String) -> Unit = {
        operand = 0.0
        operator = ""
        displayText = "0"
    }

    val onOperatorPressed: (String) -> Unit = { op ->
        if (operator.isNotEmpty()) { //Verifica se o operador está vazio
            when (operator) {
                "+" -> operand += displayText.toDouble()
                "-" -> operand -= displayText.toDouble()
                "x" -> operand *= displayText.toDouble()
                "/" -> operand /= displayText.toDouble()
                "=" -> operator = ""

            }
            setDisplay(operand)
        }
        operand = getDisplayText()
        operator = op
        userIsInTheMiddleOfIntroduction = false
    }

    Column(modifier = modifier) {
        Text(
            text = displayText,
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            style = MaterialTheme.typography.displayLarge,
            textAlign = TextAlign.End,
        )

        Row(
            modifier = modifier
                .weight(1f)
        )
        {
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "C",
                true,
                onClick = clearCalculator
            )
            CalcButton(modifier = Modifier.weight(1f), label = "()", true, onClick = { /*TODO*/ })
            CalcButton(modifier = Modifier.weight(1f), label = "√", true, onClick = { /*TODO*/ })
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "/",
                true,
                onClick = onOperatorPressed
            )
        }
        Row(
            modifier = modifier
                .weight(1f)
        )
        {
            CalcButton(modifier = Modifier.weight(1f), label = "7", onClick = onNumPressed)
            CalcButton(modifier = Modifier.weight(1f), label = "8", onClick = onNumPressed)
            CalcButton(modifier = Modifier.weight(1f), label = "9", onClick = onNumPressed)
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "x",
                true,
                onClick = onOperatorPressed
            )
        }
        Row(
            modifier = modifier
                .weight(1f)
        )
        {
            CalcButton(modifier = Modifier.weight(1f), label = "4", onClick = onNumPressed)
            CalcButton(modifier = Modifier.weight(1f), label = "5", onClick = onNumPressed)
            CalcButton(modifier = Modifier.weight(1f), label = "6", onClick = onNumPressed)
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "-",
                true,
                onClick = onOperatorPressed
            )
        }
        Row(
            modifier = modifier
                .weight(1f)
        )
        {
            CalcButton(modifier = Modifier.weight(1f), label = "1", onClick = onNumPressed)
            CalcButton(modifier = Modifier.weight(1f), label = "2", onClick = onNumPressed)
            CalcButton(modifier = Modifier.weight(1f), label = "3", onClick = onNumPressed)
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "+",
                true,
                onClick = onOperatorPressed
            )
        }
        Row(
            modifier = modifier
                .weight(1f)
        )
        {
            CalcButton(modifier = Modifier.weight(1f), label = "+/-", true, onClick = { /*TODO*/ })
            CalcButton(modifier = Modifier.weight(1f), label = "0", onClick = onNumPressed)
            CalcButton(modifier = Modifier.weight(1f), label = ".", true, onClick = onNumPressed)
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "=",
                true,
                onClick = onOperatorPressed
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun CalcScreenPreview() {
    CalculatorApp()
}
