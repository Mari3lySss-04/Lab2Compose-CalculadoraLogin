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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.myapplicationcompose.ui.theme.MyApplicationComposeTheme
//*USUARIO: SuperUsuario*
//contraseña: Password-99

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationComposeTheme {
                LoginScreen()
            }
        }
    }
}


@Composable
fun LoginScreen() {
    val context = LocalContext.current
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
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
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.image),
                contentDescription = "Imagen Login"
            )

            Spacer(modifier = Modifier.height(20.dp))


            Text("Login", style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .background(Color.White, shape = RoundedCornerShape(12.dp))
                    .border(width = 2.dp, color = Color.LightGray, shape = RoundedCornerShape(12.dp))
                    .padding(horizontal = 16.dp, vertical = 8.dp))

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Usuario") },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(errorMessage, color = Color.Red)

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                if (username == "SuperUsuario" && password == "Password-99" && validatePassword(
                        password
                    )
                ) {
                    val intent = Intent(context, MainActivity2::class.java)
                    intent.putExtra("username", username)
                    context.startActivity(intent)
                } else {
                    errorMessage = "Usuario o contraseña incorrectos"
                }
            }) {
                Text("Enviar")
            }
        }
    }
}

    fun validatePassword(password: String): Boolean {
        val hasUppercase = password.any { it.isUpperCase() }
        val hasSymbol = password.any { !it.isLetterOrDigit() }
        val isLongEnough = password.length >= 8
        return hasUppercase && hasSymbol && isLongEnough
    }

