package ies.sequeros.dam.pmdm.gestionperifl.ui.components.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun RegisterComponent(
    state: RegisterState,
    onUsernameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPassword: (String) -> Unit,
    onRegisterClick: () -> Unit,
    onCancel: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(24.dp).widthIn(max = 400.dp).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Registro",
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier.padding(bottom = 32.dp)
            )
            OutlinedTextField(
                value = state.username,
                onValueChange = { onUsernameChange(it) },
                label = { Text("Nombre de Usuario") },
                isError = state.usernameError != null
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = state.email,
                onValueChange = { onEmailChange(it) },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),
                isError = state.emailError != null,
                supportingText = {
                    state.emailError?.let { Text(it) }
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = state.password,
                onValueChange = { onPasswordChange(it) },
                label = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                isError = state.passwordError != null,
                supportingText = {
                    state.passwordError?.let { Text(it) }
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = state.verifyPassword,
                onValueChange = { onConfirmPassword(it) },
                label = { Text("Repetir contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                isError = state.verifyPasswordError != null,
                supportingText = {
                    state.verifyPasswordError?.let { Text(it) }
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
            if (state.errorMessage != null) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = state.errorMessage!!,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            if (state.isLoading) {
                CircularProgressIndicator()
            } else {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedButton(
                        onClick = onCancel,
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Cancelar")
                    }
                    Button(
                        onClick = {
                            onRegisterClick()
                        },
                        modifier = Modifier.weight(1f),
                        enabled = state.isValid && !state.isLoading,
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Registrar")
                    }
                    if (state.errorMessage != null) {
                        Text(
                            text = state.errorMessage,
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(bottom = 8.dp).fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}