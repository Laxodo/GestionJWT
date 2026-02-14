package ies.sequeros.dam.pmdm.gestionperifl.ui

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ies.sequeros.dam.pmdm.gestionperifl.aplicacion.UserSessionManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

data class ItemOption(
    val icon: ImageVector,
    val action:()->Unit,
    val name:String
)

class MainViewModel(
    private val sessionManager: UserSessionManager
): ViewModel() {
    private val _options= MutableStateFlow<List<ItemOption>>(emptyList())
    val options = _options

    val currentUserState = sessionManager.currentUser

    init {
        viewModelScope.launch {
            sessionManager.session()
        }
    }

    fun setOptions(options:List<ItemOption>){
        _options.value = options.toList()
    }

    fun logout() {
        sessionManager.logout()
    }

    fun seeProfile() {

    }

    fun changeImage() {

    }

    fun changePassword() {

    }

    fun changePerfile() {

    }
}