package com.johnhaynes.fintrack.ui.screens.auth

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.johnhaynes.fintrack.viewmodel.AuthViewModel

@Composable
fun ChangePasswordScreen(
    context: Context,
    authViewModel: AuthViewModel,
    userId: Int,
    onPasswordChanged: () -> Unit
) {
    var newPassword by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(text = "Change Password", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = newPassword,
            onValueChange = { newPassword = it },
            label = { Text("New Password") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                authViewModel.changePassword(
                    context = context,
                    userId = userId,
                    newPassword = newPassword
                ) { success, msg ->
                    message = msg
                    if (success) onPasswordChanged()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Update Password")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(text = message)
    }
}
