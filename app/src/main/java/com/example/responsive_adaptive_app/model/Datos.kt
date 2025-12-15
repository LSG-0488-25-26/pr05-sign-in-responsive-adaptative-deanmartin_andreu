package com.example.responsive_adaptive_app.model

data class Datos(
    val nombre: String,
    val apellido: String,
    val segundoApellido: String?,
    val fechaNacimiento: String,
    val email: String,
    val telefono: String,
    val nombreUsuario: String,
    val contrasena: String,
    val confirmacionContrasena: String,
    val terminosAceptados: Boolean = false
)