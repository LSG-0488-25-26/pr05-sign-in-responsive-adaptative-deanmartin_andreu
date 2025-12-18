package com.example.responsive_adaptive_app.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData

import com.example.responsive_adaptive_app.model.Datos

class formularioUser: ViewModel() {

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
    fun registroUser(usuario: String, email: String, contrasena: String) {
        _formulario.value = _formulario.value?.copy(
            nombreUsuario = usuario,
            email = email,
            contrasena = contrasena
        )
    }
}