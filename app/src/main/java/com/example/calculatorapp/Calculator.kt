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
    var displayText by remember {mutableStateOf("0")}
    val onNumPressed : (String) -> Unit = { num ->
        if (displayText == "0") {
            displayText = num
        }else{
            displayText += num
        }
    }
    Column(modifier = modifier) {
        Text(text =displayText,
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
            CalcButton(modifier = Modifier.weight(1f), label = "C", true, onClick = {displayText = "0" })
            CalcButton(modifier = Modifier.weight(1f), label = "()", true, onClick = { /*TODO*/ })
            CalcButton(modifier = Modifier.weight(1f), label = "√", true, onClick = { /*TODO*/ })
            CalcButton(modifier = Modifier.weight(1f), label = "/", true, onClick = { /*TODO*/ })
        }
        Row(
            modifier = modifier
                .weight(1f)
        )
        {
            CalcButton(modifier = Modifier.weight(1f), label = "7", onClick = onNumPressed)
            CalcButton(modifier = Modifier.weight(1f), label = "8", onClick = onNumPressed)
            CalcButton(modifier = Modifier.weight(1f), label = "9", onClick = onNumPressed)
            CalcButton(modifier = Modifier.weight(1f), label = "x", true, onClick = { /*TODO*/ })
        }
        Row(
            modifier = modifier
                .weight(1f)
        )
        {
            CalcButton(modifier = Modifier.weight(1f), label = "4",  onClick = onNumPressed)
            CalcButton(modifier = Modifier.weight(1f), label = "5",  onClick = onNumPressed)
            CalcButton(modifier = Modifier.weight(1f), label = "6",  onClick = onNumPressed)
            CalcButton(modifier = Modifier.weight(1f), label = "-", true, onClick = { /*TODO*/ })
        }
        Row(
            modifier = modifier
                .weight(1f)
        )
        {
            CalcButton(modifier = Modifier.weight(1f), label = "1",  onClick = onNumPressed)
            CalcButton(modifier = Modifier.weight(1f), label = "2",  onClick = onNumPressed)
            CalcButton(modifier = Modifier.weight(1f), label = "3",  onClick = onNumPressed)
            CalcButton(modifier = Modifier.weight(1f), label = "+", true, onClick = { /*TODO*/ })
        }
        Row(
            modifier = modifier
                .weight(1f)
        )
        {
            CalcButton(modifier = Modifier.weight(1f), label = "+/-", true, onClick = { /*TODO*/ })
            CalcButton(modifier = Modifier.weight(1f), label = "0",  onClick = { /*TODO*/ })
            CalcButton(modifier = Modifier.weight(1f), label = ".", true, onClick = { /*TODO*/ })
            CalcButton(modifier = Modifier.weight(1f), label = "=", true, onClick = { /*TODO*/ })
        }

    }
}

@Preview(showBackground = true)
@Composable
fun CalcScreenPreview() {
    CalculatorApp()
}
