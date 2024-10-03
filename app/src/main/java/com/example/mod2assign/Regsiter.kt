package com.example.mod2assign

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mod2assign.ui.theme.Mod2AssignTheme

// RegisterActivity is the activity for user registration
class RegisterActivity : ComponentActivity() {
    companion object {
        var registeredEmail: String? = null // Store the registered email
        var registeredPassword: String? = null // Store the registered password
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Mod2AssignTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RegisterScreen(modifier = Modifier.padding(innerPadding)) { email, password ->
                        registeredEmail = email // Store the registered email
                        registeredPassword = password // Store the registered password
                        Log.d("RegisterActivity", "Registered Email: $email, Password: $password")
                        // Navigate to the main activity
                        val intent = Intent(this, MainActivity::class.java) // Create an intent for the MainActivity
                        startActivity(intent) // Start the MainActivity
                        finish() // Finish the current activity
                    }
                }
            }
        }
    }
}
// Preview the register screen.
@Preview(showBackground = true)
@Composable
fun PreviewRegisterScreen() {
    Mod2AssignTheme {
        RegisterScreen { _, _ -> } //lambda expression that ignores its parameters
    // This is a placeholder since callback is not being used in preview
    }
}

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    onRegisterClick: (String, String) -> Unit
) {
    // State variables for form fields and error message
    var fname by remember { mutableStateOf("") }
    var lname by remember { mutableStateOf("") }
    var dob by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }


    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val isLargeScreen = maxWidth > 600.dp

        // LazyColumn to display the registration form making it scrollable, couldn't find a better way to display the form
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = if (isLargeScreen) 64.dp else 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // item used to wrap instead of column
            item {
                // text for the register screen
                Text(
                    text = "Register",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 32.dp)
                )
            }

            item {
                OutlinedTextField(// OutlinedTextField for first name
                    value = fname,
                    onValueChange = { fname = it },
                    label = { Text("First Name") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    isError = fname.isNotEmpty() && (fname.length < 3 || fname.length > 30)
                )
            }

            item {
                OutlinedTextField(// OutlinedTextField for last name
                    value = lname,
                    onValueChange = { lname = it },
                    label = { Text("Last Name") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    isError = lname.isNotEmpty() && (lname.length < 3 || lname.length > 30)
                )
            }

            item {
                OutlinedTextField(// OutlinedTextField for date of birth
                    value = dob,
                    onValueChange = { dob = it },
                    label = { Text("Date of Birth") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    isError = dob.isNotEmpty() && (dob.length != 10 || dob[2] != '/' || dob[5] != '/')
                )
            }

            item {
                OutlinedTextField(// OutlinedTextField for email
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    isError = email.isNotEmpty() && !Patterns.EMAIL_ADDRESS.matcher(email).matches()
                )
            }

            item {
                OutlinedTextField(// OutlinedTextField for password
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 32.dp),
                    isError = password.isNotEmpty() && (password.length < 8 || password.length > 20)
                )
            }

            item {// Error message for invalid input
                errorMessage?.let {
                    Text(text = it, color = Color.Red, modifier = Modifier.padding(bottom = 8.dp))
                }
            }

            item {
                Button( // Button to register
                    onClick = {
                        if (email.isNotEmpty() && password.isNotEmpty()) {
                            onRegisterClick(email, password)
                        } else {
                            errorMessage = "Please enter a valid email and password"
                        }
                    },
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = "Register")
                }
            }

            item {
                Box( // Box for the bottom bar
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .background(Color.DarkGray),
                    contentAlignment = Alignment.Center
                ) {
                    Text( // Text for the bottom bar
                        text = "Venture",
                        color = Color.White,
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
