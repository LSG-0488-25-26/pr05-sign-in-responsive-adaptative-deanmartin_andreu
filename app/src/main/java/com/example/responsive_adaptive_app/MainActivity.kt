package com.example.responsive_adaptive_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.responsive_adaptive_app.ui.theme.Responsive_Adaptive_AppTheme
import com.example.responsive_adaptive_app.views.Routes
import com.example.responsive_adaptive_app.views.PantallaRegistroUsuario
import com.example.responsive_adaptive_app.views.PantallaInicioSesion
import com.example.responsive_adaptive_app.views.PantallaConfirm
import com.example.responsive_adaptive_app.viewModel.formularioUser

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {

            Responsive_Adaptive_AppTheme {
                val navigationController = rememberNavController()
                val formularioUsuario: formularioUser = viewModel()
                val windowSize = calculateWindowSizeClass(this)

                NavHost(
                    navController = navigationController,
                    startDestination = Routes.InicioSesion.route
                ) {
                    composable(Routes.InicioSesion.route) {
                        PantallaInicioSesion(navigationController, formularioUsuario, windowSize)
                    }
                    composable(Routes.RegistroUsuario.route) {
                        PantallaRegistroUsuario(navigationController, formularioUsuario, windowSize)
                    }
                    composable(Routes.Confirm.route) {
                        PantallaConfirm(navigationController)
                    }
                }
            }
        }
    }
}
