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
}