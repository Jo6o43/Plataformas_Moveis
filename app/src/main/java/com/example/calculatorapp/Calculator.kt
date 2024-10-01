package com.example.calculatorapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CalculatorApp(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = "0")
        Row {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "C")
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "()")
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "√")
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "/")
            }
        }
        Row {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "7")
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "8")
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "9")
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "x")
            }
        }
        Row {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "4")
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "5")
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "6")
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "-")
            }
        }
        Row {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "1")
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "2")
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "3")
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "+")
            }
        }
        Row {
            Button(onClick = { /*TODO*/ }) {
                Text(text = ".")
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "0")
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "=")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalcScreenPreview() {
    CalculatorApp()
}
