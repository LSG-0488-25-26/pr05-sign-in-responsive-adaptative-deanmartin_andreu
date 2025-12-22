package com.example.responsive_adaptive_app.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import com.example.responsive_adaptive_app.model.Datos

class formularioUser : ViewModel() {

    // Guardamos el último usuario registrado
    private val _formulario = MutableLiveData(
        Datos(
            nombre = "",
            apellido = "",
            segundoApellido = null,
            fechaNacimiento = "",
            email = "",
            telefono = "",
            nombreUsuario = "",
            contrasena = "",
            confirmacionContrasena = "",
            terminosAceptados = false
        )
    )
    val formulario: LiveData<Datos> = _formulario

    fun login(emailUsuario: String, contrasena: String): Boolean {
        val datosUser = _formulario.value
        if (datosUser == null) {
            return false
        }
        val userCorrecto = datosUser.email == emailUsuario || datosUser.nombreUsuario == emailUsuario
        val contrasenaCorrecto = datosUser.contrasena == contrasena
        if (userCorrecto && contrasenaCorrecto) {
            return true
        } else {
            return false
        }
    }

    // Función para registrar un usuario: guarda el último creado
    fun registroUser(usuario: String, email: String, contrasena: String) {
        _formulario.value = Datos(
            nombre = "",
            apellido = "",
            segundoApellido = null,
            fechaNacimiento = "",
            email = email,
            telefono = "",
            nombreUsuario = usuario,
            contrasena = contrasena,
            confirmacionContrasena = contrasena,
            terminosAceptados = true
        )
    }

    fun validarFecha(fechaNacimiento: String): String? {
        val fechaRegex = Regex("^\\d{2}/\\d{2}/\\d{4}$")
        if (fechaNacimiento.isBlank()) {
            return "Indica tu fecha."
        }
        if (!fechaRegex.matches(fechaNacimiento)) {
            return "Formato inválido (DD/MM/AAAA)."
        }
        val partes = fechaNacimiento.split("/")
        val dia = partes[0].toInt()
        val mes = partes[1].toInt()
        val anio = partes[2].toInt()
        if (dia !in 1..31 || mes !in 1..12 || anio !in 1900..2100) {
            return "Fecha inválida."
        }
        return null
    }

    fun validarTelefono(telefono: String): String? {
        val telefonoValido = telefono.replace(" ", "")
        if (telefonoValido.isBlank()) {
            return "Teléfono obligatorio."
        }
        if (!telefonoValido.all { it.isDigit() }) {
            return "Solo escriba números porfavor..."
        }
        if (telefonoValido.length != 9) {
            return "Debe tener 9 dígitos."
        }
        return null
    }

    fun validarEmail(email: String): Boolean {
        val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
        return email.isNotBlank() && emailRegex.matches(email.trim())
    }
}
