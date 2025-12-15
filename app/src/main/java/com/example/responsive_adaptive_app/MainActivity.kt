package com.example.responsive_adaptive_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.responsive_adaptive_app.ui.theme.Responsive_Adaptive_AppTheme
import com.example.responsive_adaptive_app.views.Routes
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.responsive_adaptive_app.views.PantallaRegistroUsuario
import com.example.responsive_adaptive_app.views.PantallaInicioSesion
import com.example.responsive_adaptive_app.views.PantallaConfirm
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Responsive_Adaptive_AppTheme {
                val navigationController = rememberNavController()

                NavHost(
                    navController = navigationController,
                    startDestination = Routes.InicioSesion.route
                ) {
                    composable(Routes.InicioSesion.route) {
                        PantallaInicioSesion(navigationController)
                    }
                    composable(Routes.RegistroUsuario.route) {
                        PantallaRegistroUsuario(navigationController)
                    }
                    composable(Routes.Confirm.route) {
                        PantallaConfirm(navigationController)
                    }
                }
            }
        }
    }
}