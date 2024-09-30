package com.tifd.prakpapb.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.tifd.prakpapb.dummySchedule
import com.tifd.prakpapb.Schedule
import com.tifd.prakpapb.ui.theme.PrakPAPBTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleListScreen(navController: NavHostController) {
    PrakPAPBTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            // Top app bar with back button
            TopAppBar(
                title = { Text("Schedule List") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Using LazyColumn to display schedules
            LazyColumn(
                contentPadding = PaddingValues(vertical = 8.dp, horizontal = 8.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(dummySchedule) { schedule -> // Use 'items' for lazy column
                    ScheduleCard(schedule)
                    Spacer(modifier = Modifier.height(8.dp)) // Space between cards
                }
            }
        }
    }
}

@Composable
fun ScheduleCard(schedule: Schedule) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Handle card click here if needed */ },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp) // Correct way to specify elevation
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = schedule.namaMK, style = MaterialTheme.typography.titleMedium)
            Text(text = "Kode: ${schedule.kodeMK}", style = MaterialTheme.typography.bodySmall)
            Text(text = "Kelas: ${schedule.kelas}", style = MaterialTheme.typography.bodySmall)
            Text(text = "Waktu: ${schedule.waktu}", style = MaterialTheme.typography.bodySmall)
            Text(text = "Dosen: ${schedule.dosen}", style = MaterialTheme.typography.bodySmall)
        }
    }
}
