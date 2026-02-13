package ies.sequeros.dam.pmdm.gestionperifl.di

import com.russhwolf.settings.Settings
import ies.sequeros.dam.pmdm.gestionperifl.aplicacion.GetUserUseCase
import ies.sequeros.dam.pmdm.gestionperifl.aplicacion.UserSessionManager
import ies.sequeros.dam.pmdm.gestionperifl.aplicacion.borrar.DeleteUserUseCase
import ies.sequeros.dam.pmdm.gestionperifl.aplicacion.login.LoginUseCase
import ies.sequeros.dam.pmdm.gestionperifl.aplicacion.register.RegisterUseCase
import ies.sequeros.dam.pmdm.gestionperifl.aplicacion.update.UpdateUserUseCase
import ies.sequeros.dam.pmdm.gestionperifl.dominio.IUserRepository
import ies.sequeros.dam.pmdm.gestionperifl.infraestructure.TokenStorage
import ies.sequeros.dam.pmdm.gestionperifl.ui.MainViewModel
import ies.sequeros.dam.pmdm.gestionperifl.infraestructure.ktor.createHttpClient
import ies.sequeros.dam.pmdm.gestionperifl.infraestructure.repository.UserRepository
import ies.sequeros.dam.pmdm.gestionperifl.ui.appsettings.AppSettings
import ies.sequeros.dam.pmdm.gestionperifl.ui.appsettings.AppViewModel
import ies.sequeros.dam.pmdm.gestionperifl.ui.login.LoginFormViewModel
import ies.sequeros.dam.pmdm.gestionperifl.ui.register.RegisterFormViewModel
import ies.sequeros.dam.pmdm.gestionperifl.ui.register.RegisterScreen
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val appModulo = module {

    /**
     * infraestructura
     */
    single {
        createHttpClient( get(), "http://localhost:8080/api/public/refresh")
    }
    //almacenamiento del token
    //repositorios
    /**
    capa de aplicación
    el sesion manager,
    el origen de los datos, se encarga de transforar el tokenstorage para trabajar con user
    casos de uso
     **/

    /**
    capa de presentación
     **/
    single { AppSettings() }
    single { TokenStorage( Settings() ) }
    single { UserSessionManager(get(), get()) }

    factory { LoginUseCase(get(), get()) }
    factory { DeleteUserUseCase(get()) }
    factory { UpdateUserUseCase(get()) }
    factory { GetUserUseCase(get()) }
    factory { RegisterUseCase(get()) }

    single<IUserRepository> { UserRepository("http://localhost:8080", get()) }

    viewModel { AppViewModel(get()) }
    viewModel { LoginFormViewModel(get()) }
    viewModel { MainViewModel(get()) }
    viewModel { RegisterFormViewModel(get()) }

}