package com.tifd.projectcomposed

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tifd.projectcomposed.ui.theme.ProjectComposeDTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjectComposeDTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScreen() {
    var name by remember { mutableStateOf("") }
    var inputName by remember { mutableStateOf("") }
    var thoughts by remember { mutableStateOf("") }
    var inputThoughts by remember { mutableStateOf("") }
    var isSubmitted by remember { mutableStateOf(false) } // State variable to track submission

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Thoughts")
                }
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
                Text(text = "What are your thoughts today?", fontSize = 24.sp)

                if (isSubmitted) {
                    Text(text = "$name is thinking about \"$thoughts\"")
                }

                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = inputName,
                    onValueChange = { inputName = it },
                    label = { Text("Your name") }
                )

                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = inputThoughts,
                    onValueChange = { inputThoughts = it },
                    label = { Text("Your thoughts") }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = {
                    thoughts = inputThoughts
                    name = inputName
                    isSubmitted = true
                }) {
                    Text("Submit")
                }
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ProjectComposeDTheme {
        MyScreen()
    }
}