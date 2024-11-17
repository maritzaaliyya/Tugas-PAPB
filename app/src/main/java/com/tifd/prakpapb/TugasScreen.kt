package com.tifd.prakpapb

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.database.*
import com.tifd.prakpapb.ui.theme.AlmostWhite
import com.tifd.prakpapb.ui.theme.DarkBlue
import com.tifd.prakpapb.ui.theme.GrayBlue
import com.tifd.prakpapb.ui.theme.NeonBlue
import com.tifd.prakpapb.ui.theme.PrakPAPBTheme

@Composable
fun TugasScreen(navController: NavHostController, viewModel: TaskViewModel) {
    val tasks by viewModel.tasks.collectAsState()
    var inputMk by remember { mutableStateOf("") }
    var inputDetail by remember { mutableStateOf("") }

    Scaffold(
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                // Input Fields
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    TextField(
                        value = inputMk,
                        onValueChange = { inputMk = it },
                        label = { Text("Mata Kuliah") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    TextField(
                        value = inputDetail,
                        onValueChange = { inputDetail = it },
                        label = { Text("Detail Tugas") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            if (inputMk.isNotEmpty() && inputDetail.isNotEmpty()) {
                                viewModel.addTask(
                                    TaskEntity(
                                        namaMk = inputMk,
                                        detail = inputDetail,
                                        timestamp = System.currentTimeMillis()
                                    )
                                )
                                inputMk = ""
                                inputDetail = ""
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Submit")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Task List
                LazyColumn(
                    modifier = Modifier.fillMaxSize().padding(8.dp)
                ) {
                    items(tasks) { task ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = task.isDone,
                                onCheckedChange = { checked ->
                                    viewModel.updateTask(task.copy(isDone = checked))
                                }
                            )
                            Column(
                                modifier = Modifier.padding(start = 8.dp)
                            ) {
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = task.namaMk,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = if (task.isDone) GrayBlue else NeonBlue,
                                )
                                Text(
                                    text = task.detail,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = if (task.isDone) GrayBlue else AlmostWhite
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = formatTimestamp(task.timestamp as Long),
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = GrayBlue
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                            }
                        }
                        Divider(
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f),
                            thickness = 1.dp
                        )
                    }
                }
            }
        }
    )
}

fun formatTimestamp(timestamp: Long): String {
    val sdf = java.text.SimpleDateFormat("dd MMM yyyy", java.util.Locale.getDefault())
    val date = java.util.Date(timestamp)
    return sdf.format(date)
}