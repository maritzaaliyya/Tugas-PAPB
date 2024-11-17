package com.tifd.prakpapb

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.tifd.prakpapb.ui.theme.AlmostWhite
import com.tifd.prakpapb.ui.theme.DarkBlue
import com.tifd.prakpapb.ui.theme.NeonBlue
import com.tifd.prakpapb.ui.theme.Poppins
import com.tifd.prakpapb.ui.theme.PrakPAPBTheme

@Composable
fun LoginScreen(navController: NavHostController, onLoginSuccess: () -> Unit) {
    PrakPAPBTheme {
        val auth = Firebase.auth
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var errorMessage by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start // Align children to the left
        ) {
            // Align the "Welcome Back." text to the left
            Text(
                text = "Welcome",
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold,
                fontSize = 48.sp,
                color = NeonBlue,
            )

            Text(
                text = "Back.",
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold,
                fontSize = 48.sp,
                color = NeonBlue,
            )

            // Rest of the UI
            Text(
                text = "Sudah siap menjalani semester ini?",
                fontFamily = Poppins,
                fontWeight = FontWeight.Normal,
                fontSize = 22.sp,
                color = AlmostWhite
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Button(onClick = {
                    if (email.isNotBlank() && password.isNotBlank()) {
                        auth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    Log.d("LoginScreen", "signInWithEmail:success")
                                    onLoginSuccess() // Call the success callback
                                } else {
                                    Log.w("LoginScreen", "signInWithEmail:failure", task.exception)
                                    val exception = task.exception
                                    errorMessage = when {
                                        exception?.message?.contains("no user record") == true -> {
                                            "User not found. Please check your email."
                                        }
                                        exception?.message?.contains("wrong password") == true -> {
                                            "Incorrect password. Please try again."
                                        }
                                        else -> {
                                            "Authentication failed: ${exception?.message}"
                                        }
                                    }
                                }
                            }
                    } else {
                        errorMessage = "Email and password cannot be empty"
                    }
                },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = DarkBlue
                    ),
                    shape = MaterialTheme.shapes.medium) {
                    Text("Log In", fontWeight = FontWeight.Bold)
                }
            }

            if (errorMessage.isNotEmpty()) {
                Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
            }
        }
    }
}