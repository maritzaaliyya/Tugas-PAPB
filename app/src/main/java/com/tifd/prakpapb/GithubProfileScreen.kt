package com.tifd.prakpapb

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import androidx.navigation.NavController

// Data class to represent the user fetched from GitHub API
data class GithubUser(
    val avatar_url: String,
    val login: String,
    val name: String?,
    val followers: Int,
    val following: Int
)

// Retrofit interface to define the API endpoints
interface GithubApiService {
    @GET("users/{username}")
    suspend fun getUser(@Path("username") username: String): GithubUser

    companion object {
        fun create(): GithubApiService {
            return Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GithubApiService::class.java)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GithubProfileScreen(navController: NavController, username: String = "maritzaaliyya") {
    var githubUser by remember { mutableStateOf<GithubUser?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val scope = rememberCoroutineScope()
    val githubApi = remember { GithubApiService.create() }

    // Fetch the GitHub user when the screen is first displayed
    LaunchedEffect(Unit) {
        scope.launch {
            try {
                val user = githubApi.getUser(username)
                githubUser = user
            } catch (e: Exception) {
                errorMessage = "Failed to load profile: ${e.message}"
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Github Profile") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            )
        },
        content = { paddingValues ->
            // UI to display the user profile
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .padding(paddingValues),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (githubUser != null) {
                    ProfileContent(githubUser!!)
                } else if (errorMessage != null) {
                    Text(
                        text = errorMessage ?: "Unknown error",
                        color = Color.Red,
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center
                    )
                } else {
                    CircularProgressIndicator()
                }
            }
        }
    )
}

@Composable
fun ProfileContent(user: GithubUser) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberImagePainter(user.avatar_url),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(128.dp)
                .padding(16.dp),
            contentScale = ContentScale.Crop
        )
        Text(text = "@${user.login}", style = MaterialTheme.typography.bodyLarge)
        user.name?.let {
            Text(text = it, style = MaterialTheme.typography.headlineMedium)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Followers: ${user.followers}", style = MaterialTheme.typography.bodyLarge)
        Text(text = "Following: ${user.following}", style = MaterialTheme.typography.bodyLarge)
    }
}