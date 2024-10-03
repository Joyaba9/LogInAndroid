package com.example.mod2assign

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mod2assign.ui.theme.Mod2AssignTheme

class DashboardActivity : ComponentActivity() { // Changed to "DashboardActivity" for naming consistency
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Mod2AssignTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DashboardScreen(modifier = Modifier.padding(innerPadding))// Display the dashboard screen
                }
            }
        }
    }
}

@Composable
fun DashboardScreen(modifier: Modifier = Modifier) {
    var destination by remember { mutableStateOf("") } // Store the destination in a state
    //does nothing

    BoxWithConstraints( // Use BoxWithConstraints to adjust the layout based on the screen size
        modifier = modifier.fillMaxSize()
    ) {
        if (maxWidth > 600.dp) {
            // Large screen or landscape mode will use Row
            RowLayout(destination = destination, onDestinationChange = { destination = it })
        } else {
            // Small screen or portrait mode will use Column
            ColumnLayout(destination = destination, onDestinationChange = { destination = it })
        }
    }
}

@Composable
fun ColumnLayout(destination: String, onDestinationChange: (String) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        // Use Column to arrange the sections vertically
        verticalArrangement = Arrangement.SpaceBetween,
        // Align the content in the center horizontally
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // the following is easier to call and debug
        // Display the top image
        TopImageSection()
        ContentSection(destination = destination, onDestinationChange = onDestinationChange) // Display the content section
        BottomBar() // Display the bottom bar
    }
}

@Composable
fun RowLayout(destination: String, onDestinationChange: (String) -> Unit) {
    Row( // Use Row to arrange the sections horizontally
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TopImageSection(Modifier.weight(1f)) // Display the top image
        ContentSection(
            modifier = Modifier.weight(1f), // Adjust the width of the content section
            destination = destination,
            onDestinationChange = onDestinationChange
        )
    }
}

// Display the top image section
// easier to use if i want to randomize the image or in case i want to use a
// different source for the image
@Composable
fun TopImageSection(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.venture),
        contentDescription = "Top Image",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp)
    )
}

// Display the content section
@Composable
fun ContentSection(
    modifier: Modifier = Modifier,
    destination: String,
    onDestinationChange: (String) -> Unit // Callback to handle destination change
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text( // Display the title
            text = "Search flights",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 32.dp)
        )
        OutlinedTextField( // Display the destination input field which again does nothign
            value = destination,
            onValueChange = onDestinationChange,
            label = { Text("Enter Destination") },
            modifier = Modifier.fillMaxWidth(0.8f)
        )
    }
}

@Composable
fun BottomBar(modifier: Modifier = Modifier) { // Display the bottom bar
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Welcome to the Dashboard!",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

// Preview the dashboard screen
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Mod2AssignTheme {
        DashboardScreen() // Show a preview of the dashboard screen
    }
}