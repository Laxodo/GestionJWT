package ies.sequeros.dam.pmdm.gestionperifl.infraestructure

import com.russhwolf.settings.Settings
import com.russhwolf.settings.get

class TokenStorage(private val settings: Settings) {
    companion object{
        const val ACCESS_TOKEN: String = "access_token"
        const val ID_TOKEN: String = "id_token"
        const val REFRESH_TOKEN: String = "refresh_token"
    }
    fun saveTokens(accessToken: String, refreshToken: String?, idToken: String){
        settings.putString(ACCESS_TOKEN, accessToken)
        settings.putString(ID_TOKEN, idToken)
        settings.putString(REFRESH_TOKEN, refreshToken ?: "")
    }

    fun getAccessToken(): String? {
        return settings.getStringOrNull(ACCESS_TOKEN)
    }

    fun getIdToken(): String?{
        return settings.getStringOrNull(ID_TOKEN)
    }

    fun getRefreshToken(): String?{
        return settings.getStringOrNull(REFRESH_TOKEN)
    }

    fun clear() {
        settings.remove(ACCESS_TOKEN)
        settings.remove(ID_TOKEN)
        settings.remove(REFRESH_TOKEN)
    }
}