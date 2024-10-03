package com.example.mod2assign

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.min
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay

// SplashActivity is the first activity that appears when the app is launched
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SplashScreen {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent) // Start the MainActivity
                finish() // Finish the current activity
            }
        }
    }
}

@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    LaunchedEffect(Unit) { // Wait for 2 seconds before calling onTimeout
        delay(2000L)
        onTimeout() // Call the onTimeout lambda
    }

    // Use BoxWithConstraints to adjust the layout
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center // Center the content
    ) {
        val minDimension = min(maxWidth, maxHeight) // Get the smaller of the width and height

        // Adjust the box size and text size based on the available space
        Box(
            modifier = Modifier
                .size(minDimension * 0.5f) // Set the size to 50% of the smaller dimension
                .background(MaterialTheme.colorScheme.primary),
            contentAlignment = Alignment.Center
        ) {
            Text( // Display the app name
                text = "Venture",
                color = Color.White,
                fontSize = (minDimension * 0.1f).value.sp, // Dynamic font size
                fontWeight = FontWeight.Bold
            )
        }
    }
}
// Preview the splash screen.
@Preview(showBackground = true)
@Composable
fun PreviewSplashScreen() {
    SplashScreen(onTimeout = {})
}
