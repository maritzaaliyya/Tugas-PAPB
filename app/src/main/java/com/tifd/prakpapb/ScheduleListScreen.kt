package com.tifd.prakpapb

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.tifd.prakpapb.ui.theme.NeonBlue
import com.tifd.prakpapb.ui.theme.PrakPAPBTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleListScreen(navController: NavHostController) {
    PrakPAPBTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background) // Dark theme background
        ) {
            LazyColumn(
                contentPadding = PaddingValues(vertical = 8.dp, horizontal = 8.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(dummySchedule) { schedule ->
                    ScheduleCard(schedule)
                    Divider( // Thin line separator
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f), // Slightly visible divider
                        thickness = 1.dp
                    )
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
            .clickable {},
        shape = RoundedCornerShape(0.dp), // No rounded corners, flat edges
        elevation = CardDefaults.cardElevation(0.dp), // No elevation to make it flat
        colors = CardDefaults.cardColors(containerColor = Color.Transparent) // Transparent background
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = schedule.namaMK, style = MaterialTheme.typography.titleMedium, color = NeonBlue)
            Text(text = "Kode: ${schedule.kodeMK}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Kelas: ${schedule.kelas}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Waktu: ${schedule.waktu}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Dosen: ${schedule.dosen}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}