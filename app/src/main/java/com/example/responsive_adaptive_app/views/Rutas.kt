package com.example.responsive_adaptive_app.views

sealed class Routes (val route: String) {
    object RegistroUsuario :Routes("registroUsuario")
    object InicioSesion :Routes("inicioSesion")
    object Confirm :Routes("confirm")
}