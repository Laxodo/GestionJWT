package ies.sequeros.dam.pmdm.gestionperifl.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AccessScreen(
    onLogin: () -> Unit,
    onRegister: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = onLogin,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Iniciar Sesión", fontSize = 16.sp)
        }

        OutlinedButton(
            onClick = onRegister,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Crear cuenta nueva", fontSize = 16.sp)
        }
    }
}