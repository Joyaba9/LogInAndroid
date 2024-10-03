package com.example.mod2assign

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mod2assign.ui.theme.Mod2AssignTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Mod2AssignTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginScreen(modifier = Modifier.padding(innerPadding)) { enteredEmail, enteredPassword ->
                        // Check if entered credentials match the registered ones
                        if (enteredEmail == RegisterActivity.registeredEmail &&
                            enteredPassword == RegisterActivity.registeredPassword) {
                            // Successful login
                            val intent = Intent(this, DashboardActivity::class.java)
                            startActivity(intent)
                        } else {
                            // Show a Toast message for incorrect login credentials
                            Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}

// Preview the login screen.
@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    Mod2AssignTheme {
        LoginScreen { _, _ -> }
    }
}

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onLoginClick: (String, String) -> Unit // Callback to handle login logic
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    BoxWithConstraints(modifier = modifier.fillMaxSize()) {
        val isLargeScreen = maxWidth > 600.dp


        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween // Similar to the structure of MainActivity
        ) {
            // Top Image Section
            item {
                // Top Image Section
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.venture), // Replace with your image file name
                        contentDescription = "Top Image",
                        contentScale = ContentScale.Crop, // Ensures the image is cropped, not stretched, to fit the area
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp) // Set height for the image covering the top part of the screen
                    )
                }

                // Middle Section: Email, Password, and Login Button
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 46.dp)
                        .padding(top = 26.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Login to Venture",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 32.dp)
                    )

                    // Email TextField
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                    )

                    // Password TextField
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Password") },
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 32.dp)
                    )

                    // Login Button
                    Button(
                        onClick = {
                            onLoginClick(
                                email,
                                password
                            )
                        }, // Pass email and password to the callback
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .fillMaxWidth() // Adjust button width based on screen size
                    ) {
                        Text(text = "Login")
                    }
                }
            }
        }

        }
    }
