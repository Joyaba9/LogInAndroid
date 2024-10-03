package com.example.mod2assign

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.example.mod2assign.ui.theme.Mod2AssignTheme


// MainActivity is the entry point of the app after splash.kt runs
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Mod2AssignTheme{
            Scaffold(
                modifier = Modifier.fillMaxSize()
            ) { innerPadding ->
                // Pass innerPadding to the content modifier
                // once opened, you can navigate to the login or register page
                LandingPageApp(
                    modifier = Modifier.padding(innerPadding), // Apply the padding here
                    onLoginClick = { navigateToLogin() },
                    onRegisterClick = { navigateToRegister() }
                )
            }
        }
    }
}
//
private fun navigateToLogin() {
    // intent to navigate to the login page
    val intent = Intent(this, LoginActivity::class.java)
    startActivity(intent) // Start the LoginActivity
}

private fun navigateToRegister() {
    // intent to navigate to the register page
    val intent = Intent(this, RegisterActivity::class.java)
    startActivity(intent) // Start the RegisterActivity
}
}

// preview the landing page
@Preview(showBackground = true)
@Composable
fun PreviewLandingPageApp() {
    Mod2AssignTheme {
        LandingPageApp(
            modifier = Modifier, // Default modifier
            onLoginClick = {},
            onRegisterClick = {}
        )
    }
}

// Landing page composable function
@Composable
fun LandingPageApp(
    modifier: Modifier = Modifier, // Default modifier value
    onLoginClick: () -> Unit, // Callback for login button click
    onRegisterClick: () -> Unit // Callback for register button click
) {
    Column(
        modifier = modifier
            .fillMaxSize(), // Fill the entire screen
        verticalArrangement = Arrangement.SpaceBetween // Ensures content is spaced with top image and bottom bar
    ) {
        //Image from R.drawable.venture
                Box(modifier = Modifier.fillMaxWidth()) {
                    Image(
                        painter = painterResource(id = R.drawable.venture), // Replace with your image file name
                        contentDescription = "Top Image",
                        contentScale = ContentScale.Crop, // Ensures the image is cropped, not stretched, to fit the area
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp) // Set height for the image
                    )
                }

        // Center Column with Text and Buttons
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Welcome Text
            Text(
                text = "Welcome to Venture!",
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold
            )
            // Login Button
            Button(
                onClick = onLoginClick,
                modifier = Modifier.padding(top = 16.dp).width(200.dp)
            ) {
                Text(text = "Login")
            }
            // Register Button
            Button(
                onClick = onRegisterClick,
                modifier = Modifier.padding(top = 16.dp).width(200.dp)
            ) {
                Text(text = "Register")
            }
        }

        // Bottom Color Bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp) // Height of the bottom bar
                .background(Color.DarkGray), // Dark gray color
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Venture",
                color = Color.White, // White text on the purple bar
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


