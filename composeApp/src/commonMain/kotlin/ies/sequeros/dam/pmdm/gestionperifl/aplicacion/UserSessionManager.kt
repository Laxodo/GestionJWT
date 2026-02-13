package ies.sequeros.dam.pmdm.gestionperifl.aplicacion

import com.russhwolf.settings.Settings
import ies.sequeros.dam.pmdm.gestionperifl.dominio.User
import ies.sequeros.dam.pmdm.gestionperifl.infraestructure.TokenJwt
import ies.sequeros.dam.pmdm.gestionperifl.infraestructure.TokenStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

//TODO: revisar que vaya ahi el token storage.
class UserSessionManager(private val tokenStorage: TokenStorage) {
    //Todo: Aqui va la gestion de usuario con el token storage, aqui van los datos del usuario y del token, y no se que mas

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser.asStateFlow()

    fun session() {
        val idTokenString = tokenStorage.getIdToken()
        val accessToken = tokenStorage.getAccessToken()

        if (!idTokenString.isNullOrBlank() && !accessToken.isNullOrBlank()) {
            try {
                val jwt = TokenJwt(idTokenString)

                if (jwt.isSessionValid()) {
                    val user = userFromToken(jwt)
                    _currentUser.update { user }
                } else {
                    logout()
                }
            } catch (e: Exception) {
                logout()
            }
        }
    }

    fun saveSession(access: String, refresh: String?, idToken: String) {
        tokenStorage.saveTokens(access, refresh, idToken)
        try {
            val user = userFromToken(TokenJwt(idToken))
            _currentUser.update { user }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun logout() {
        tokenStorage.clear()
        _currentUser.update { null }
    }

    private fun userFromToken(jwt: TokenJwt): User {
        val payload = jwt.payload
        return User(
            id = payload.userId ?: payload.get("uid") ?: "unknown",
            name = payload.get("name") ?: "",
            email = payload.get("email") ?: "",
            image = payload.get("picture") ?: "",
            status = payload.get("status") ?: ""
        )
    }
}