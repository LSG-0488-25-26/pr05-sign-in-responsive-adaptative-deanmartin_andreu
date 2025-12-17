package com.example.responsive_adaptive_app.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaRegistroUsuario(navController: NavController) {

    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var segundoApellido by remember { mutableStateOf("") }
    var fechaNacimiento by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var nombreUsuario by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var confirmacionContrasena by remember { mutableStateOf("") }

    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Registro de Usuario") }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            OutlinedTextField(nombre, { nombre = it }, label = { Text("Nombre") })
            OutlinedTextField(apellido, { apellido = it }, label = { Text("1º Apellido") })
            OutlinedTextField(segundoApellido, { segundoApellido = it }, label = { Text("2º Apellido") })


            OutlinedTextField(
                value = fechaNacimiento,
                onValueChange = { fechaNacimiento = it },
                label = { Text("Fecha de nacimiento") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(email, { email = it }, label = { Text("Email") })
            OutlinedTextField(telefono, { telefono = it }, label = { Text("Teléfono") })
            OutlinedTextField(nombreUsuario, { nombreUsuario = it }, label = { Text("Usuario") })

            OutlinedTextField(
                value = contrasena,
                onValueChange = { contrasena = it },
                label = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = confirmacionContrasena,
                onValueChange = { confirmacionContrasena = it },
                label = { Text("Confirmar contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("CREAR CUENTA")
            }
        }
    }
}
