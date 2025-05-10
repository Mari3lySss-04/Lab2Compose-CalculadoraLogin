package com.example.myapplicationcompose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myapplicationcompose.ui.theme.MyApplicationComposeTheme

class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val username = intent.getStringExtra("username") ?: "Usuario"
        setContent {
            MyApplicationComposeTheme {
                CalculatorScreen(username)
            }
        }
    }
}

@Composable
fun CalculatorButton(label: String, modifier: Modifier, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(label)
    }
}

fun calculate(n1: String, n2: String, op: Char): String {
    val a = n1.toDoubleOrNull()
    val b = n2.toDoubleOrNull()
    if (a == null || b == null) return "Error"

    return when (op) {
        '+' -> (a + b).toString()
        '-' -> (a - b).toString()
        '*' -> (a * b).toString()
        '/' -> if (b != 0.0) (a / b).toString() else "División por 0"
        else -> "Operación inválida"
    }
}

@Composable
fun CalculatorScreen(username: String) {
    val context = LocalContext.current
    var num1 by remember { mutableStateOf("") }
    var num2 by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.fondo),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Calculadora", style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .background(Color.White, shape = RoundedCornerShape(12.dp))
                    .border(width = 2.dp, color = Color.LightGray, shape = RoundedCornerShape(12.dp))
                    .padding(horizontal = 16.dp, vertical = 8.dp))
            Spacer(modifier = Modifier.height(8.dp))
            Text("Usuario: $username", modifier = Modifier
                .background(Color.White, shape = RoundedCornerShape(12.dp))
                .border(width = 2.dp, color = Color.LightGray, shape = RoundedCornerShape(12.dp))
                .padding(horizontal = 16.dp, vertical = 8.dp))
            Spacer(modifier = Modifier.height(20.dp))

            // Entrada 1
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("#1", modifier = Modifier.width(30.dp))
                Spacer(modifier = Modifier.width(8.dp))
                OutlinedTextField(
                    value = num1,
                    onValueChange = { num1 = it },
                    label = { Text("Número 1") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black),
                    modifier = Modifier
                        .weight(1f)
                        .background(Color.White)

                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Entrada 2
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("#2", modifier = Modifier.width(30.dp))
                Spacer(modifier = Modifier.width(8.dp))
                OutlinedTextField(
                    value = num2,
                    onValueChange = { num2 = it },
                    label = { Text("Número 2") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black),
                    modifier = Modifier.weight(1f)
                        .background(Color.White)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))



            val buttonModifier = Modifier
                .weight(1f)
                .height(56.dp)
                .padding(4.dp)

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                CalculatorButton("Sumar", buttonModifier) {
                    result = calculate(num1, num2, '+')
                }
                CalculatorButton("Restar", buttonModifier) {
                    result = calculate(num1, num2, '-')
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                CalculatorButton("Multiplicar", buttonModifier) {
                    result = calculate(num1, num2, '*')
                }
                CalculatorButton("Dividir", buttonModifier) {
                    result = calculate(num1, num2, '/')
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            // Resultado
            Text("Resultado", style = MaterialTheme.typography.titleMedium, modifier = Modifier
                .background(Color.White, shape = RoundedCornerShape(12.dp))
                .border(width = 2.dp, color = Color.LightGray, shape = RoundedCornerShape(12.dp))
                .padding(horizontal = 16.dp, vertical = 8.dp))
            Spacer(modifier = Modifier.height(4.dp)
            )
            OutlinedTextField(
                value = result,
                onValueChange = {},
                readOnly = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black),
                modifier = Modifier.fillMaxWidth()
                    .background(Color.White))


            Spacer(modifier = Modifier.height(30.dp))

            Button(onClick = {
                val intent = Intent(context, MainActivity::class.java)
                context.startActivity(intent)
            }) {
                Text("Salir")
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Imagen de salida
                Image(
                    painter = painterResource(id = R.drawable.salida),
                    contentDescription = "Imagen Login",
                    modifier = Modifier.size(220.dp)
                )




            }
        }
    }
}



