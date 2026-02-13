package ies.sequeros.dam.pmdm.gestionperifl.ui.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.rememberNavController

data class MenuButtom(
    val icon: ImageVector,
    val name: String,
    val action: () -> Unit
)

@Composable
fun HomeFormViewModel(
    onLogout: () -> Unit
) {
    /*val subNavController = rememberNavController()

    val botones = listOf(
        MenuButtom(Icons.Default.Person, "Ver perfil") {
            subNavController.navigate(SeeProfileRoute) { launchSingleTop = true }
        },
        MenuButtom(Icons.Default.Image, "Cambiar foto") {
        subNavController.navigate(ChangeImageRoute) { launchSingleTop = true }
        },
        MenuButtom(Icons.Default.Lock, "Cambiar contraseña") {
            subNavController.navigate(ChangePasswordRoute) { launchSingleTop = true }
        },
        MenuButtom(Icons.Default.Person, "Modificar perfil") {
            subNavController.navigate(ChangeProfileRoute) { launchSingleTop = true }
        },
        MenuButtom(Icons.Default.Person, "Borrar cuenta") {
            subNavController.navigate(DeletePerfileRoute) { launchSingleTop = true }
        }
    )*/


}