package com.tifd.prakpapb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tifd.prakpapb.ui.screens.LoginScreen
import com.tifd.prakpapb.ui.theme.PrakPAPBTheme
import androidx.core.text.isDigitsOnly
import com.tifd.prakpapb.R
import com.tifd.prakpapb.ui.screens.ScheduleListScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PrakPAPBTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "main") {
                    composable("main") { MyScreen(navController) }
                    composable("login") {
                        LoginScreen(navController = navController, onLoginSuccess = {
                            navController.navigate("schedule_list")
                        })
                    }
                    composable("schedule_list") { ScheduleListScreen(navController) }
                }
            }
        }
    }
}

@Composable
fun NavigationGraph() {
    PrakPAPBTheme{
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = "main") {
            composable("main") { MyScreen(navController) }
            composable("login") {
                LoginScreen(navController = navController, onLoginSuccess = {
                    navController.navigate("schedule_list")
                })
            }

            composable("schedule_list") { ScheduleListScreen(navController) }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScreen(navController: NavHostController) {
    var name by remember { mutableStateOf("") }
    var inputName by remember { mutableStateOf("") }
    var nim by remember { mutableStateOf("") }
    var inputNim by remember { mutableStateOf("") }
    var submitStatus by remember { mutableStateOf("null") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Konfirmasi Identitas") },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .padding(paddingValues),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    text = "Ini adalah Dr. Ratio, konfirmasi identitasmu supaya Dr. Ratio bisa mengenalimu lebih baik.",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                GreetingImage()
                if (submitStatus == "true") {
                    Text(
                        text = "\"NIM kamu $nim? Baik, salam kenal, $name\"",
                        textAlign = TextAlign.Center
                    )
                } else if (submitStatus == "false") {
                    Text(
                        text = "Tolong masukkan nama dan NIM yang valid.",
                        color = Color.Red
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Filled.AccountBox,
                        contentDescription = "Icon Profile",
                        tint = Color.Black,
                        modifier = Modifier.size(25.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    OutlinedTextField(
                        value = inputName,
                        onValueChange = { inputName = it },
                        label = { Text("Masukkan nama") },
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp)
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Filled.Info,
                        contentDescription = "NIM Info",
                        tint = Color.Black,
                        modifier = Modifier.size(25.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    OutlinedTextField(
                        value = inputNim,
                        onValueChange = { inputNim = it },
                        label = { Text("Masukkan NIM") },
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Button(
                        onClick = {
                            if (inputName.isNotBlank() && inputNim.isDigitsOnly()) {
                                name = inputName
                                nim = inputNim
                                submitStatus = "true"
                            } else {
                                submitStatus = "false"
                            }
                        },
                        enabled = inputName.isNotBlank() && inputNim.isNotBlank()
                    ) {
                        Text("Submit")
                    }
                    Button(
                        onClick = {
                            navController.navigate("login") // Navigate to LoginScreen
                        }
                    ) {
                        Text("Log In")
                    }
                }
            }
        }
    )
}

@Composable
fun GreetingImage() {
    val image = painterResource(R.drawable.ratio)
    Image(
        painter = image,
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth(0.75f)
            .aspectRatio(1f)
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PrakPAPBTheme {
        MyScreen(navController = rememberNavController())
    }
}
