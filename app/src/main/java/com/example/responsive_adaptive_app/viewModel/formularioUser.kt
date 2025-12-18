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

    // Función de login: compara usuario/email y contraseña
    fun login(emailUsuario: String, contrasena: String): Boolean {
        val datosUser = _formulario.value ?: return false
        val userCorrecto = datosUser.email == emailUsuario || datosUser.nombreUsuario == emailUsuario
        val contrasenaCorrecto = datosUser.contrasena == contrasena
        return userCorrecto && contrasenaCorrecto
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
}
