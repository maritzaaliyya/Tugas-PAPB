package com.tifd.prakpapb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.tifd.prakpapb.ui.theme.PrakPAPBTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Database and Repository Initialization
        val database = AppDatabase.getDatabase(applicationContext)
        val repository = TaskRepository(database.taskDao())

        setContent {
            PrakPAPBTheme {
                val navController = rememberNavController()

                // Initialize the ViewModel here using the appropriate scope
                val taskViewModel: TaskViewModel = viewModel(factory = TaskViewModelFactory(repository))

                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(navController = navController)
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "login",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("login") {
                            LoginScreen(navController) {
                                navController.navigate("schedule_list") {
                                    popUpTo("login") { inclusive = true }
                                }
                            }
                        }
                        composable("schedule_list") { ScheduleListScreen(navController) }
                        composable("tugas") {
                            TugasScreen(navController = navController, viewModel = taskViewModel)
                        }
                        composable("github_profile") { GithubProfileScreen(navController) }
                    }
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem("Matkul", "schedule_list", Icons.Default.Info),
        BottomNavItem("Tugas", "tugas", Icons.Default.Info),
        BottomNavItem("Profil", "github_profile", Icons.Default.AccountCircle)
    )

    val currentDestination by navController.currentBackStackEntryAsState()
    val showBottomBar = currentDestination?.destination?.route !in listOf("login")

    if (showBottomBar) {
        NavigationBar {
            items.forEach { item ->
                NavigationBarItem(
                    icon = { Icon(item.icon, contentDescription = item.name) },
                    label = { Text(item.name) },
                    selected = currentDestination?.destination?.route == item.route,
                    onClick = {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}

data class BottomNavItem(val name: String, val route: String, val icon: androidx.compose.ui.graphics.vector.ImageVector)
