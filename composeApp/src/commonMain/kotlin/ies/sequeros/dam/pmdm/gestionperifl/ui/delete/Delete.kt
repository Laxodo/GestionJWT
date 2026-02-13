package ies.sequeros.dam.pmdm.gestionperifl.ui.delete

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Delete(
    deleteFormViewModel: DeleteFormViewModel,
    onDelete: () -> Unit
) {
    val state by deleteFormViewModel.uiState.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Seguro que desea borrar el usuario?",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Escribe la contraseña por seguridad",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))

            if (state.messageError != null) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = state.messageError!!,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            if (state.isDeleteSuccess){
                onDelete()
            }

            if(state.isLoading){
                CircularProgressIndicator()
            }else{
                OutlinedTextField(
                    value = state.password,
                    onValueChange = { deleteFormViewModel.onChangePassword(it) },
                    visualTransformation = PasswordVisualTransformation(),
                    label = { Text("Contraseña") },
                    isError = state.passwordErr != null
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = {
                        deleteFormViewModel.delete()
                    },
                    enabled = state.isValid && !state.isLoading,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Registrar")
                }
            }
        }
    }
}