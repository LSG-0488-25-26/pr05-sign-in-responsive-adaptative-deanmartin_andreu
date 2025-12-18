package com.example.responsive_adaptive_app.views

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.responsive_adaptive_app.model.Datos
import android.util.Patterns
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.responsive_adaptive_app.viewModel.formularioUser

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaRegistroUsuario(navController: NavController) {
    val context = LocalContext.current
    val formularioVM: formularioUser = viewModel()

    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var segundoApellido by remember { mutableStateOf("") }
    var fechaNacimiento by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var nombreUsuario by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var confirmacionContrasena by remember { mutableStateOf("") }
    var terminosAceptados by remember { mutableStateOf(false) }

    var errores by remember { mutableStateOf(mapOf<String, String>()) }

    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Registro de Usuario") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                    navigationIconContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            // Espacio para añadir el nombre
            CustomTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = "Nombre",
                icon = Icons.Default.Person,
                error = errores["nombre"]
            )

            // Espacios para añadir ambos apellidos
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CustomTextField(
                    value = apellido,
                    onValueChange = { apellido = it },
                    label = "1º Apellido",
                    modifier = Modifier.weight(1f),
                    error = errores["apellido"]
                )
                CustomTextField(
                    value = segundoApellido,
                    onValueChange = { segundoApellido = it },
                    label = "2º Apellido",
                    modifier = Modifier.weight(1f)
                )
            }

            // Opción para fecha de nacimiento
            CustomTextField(
                value = fechaNacimiento,
                onValueChange = { fechaNacimiento = it },
                label = "Fecha Nacimiento (DD/MM/AAAA)",
                icon = Icons.Default.DateRange,
                error = errores["fecha"]
            )

            // Textfield para poner el email
            CustomTextField(
                value = email,
                onValueChange = { email = it },
                label = "Correo Electrónico",
                icon = Icons.Default.Email,
                keyboardType = KeyboardType.Email,
                error = errores["email"]
            )

            // Textfield para poner el teléfono
            CustomTextField(
                value = telefono,
                onValueChange = { telefono = it },
                label = "Teléfono",
                icon = Icons.Default.Phone,
                keyboardType = KeyboardType.Phone,
                error = errores["telefono"]
            )

            // Añadir nombre de usario en la app
            CustomTextField(
                value = nombreUsuario,
                onValueChange = { nombreUsuario = it },
                label = "Nombre de Usuario",
                icon = Icons.Default.AccountCircle,
                error = errores["usuario"]
            )

            // Contraseña con privacidad
            CustomTextField(
                value = contrasena,
                onValueChange = { contrasena = it },
                label = "Contraseña",
                icon = Icons.Default.Lock,
                isPassword = true,
                error = errores["contrasena"]
            )

            // Confirmación contraseña privada
            CustomTextField(
                value = confirmacionContrasena,
                onValueChange = { confirmacionContrasena = it },
                label = "Confirmar Contraseña",
                icon = Icons.Default.Lock,
                isPassword = true,
                error = errores["confirmacion"]
            )

            // Checkbox para aceptar los términos y condiciones
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Checkbox(
                        checked = terminosAceptados,
                        onCheckedChange = { terminosAceptados = it }
                    )
                    Text("Acepto los términos y condiciones", style = MaterialTheme.typography.bodyMedium)
                }
                errores["terminos"]?.let {
                    Text(it, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall, modifier = Modifier.padding(start = 12.dp))
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Button para hacer el registro del usuario con los datos
            Button(
                onClick = {
                    val nuevosErrores = mutableMapOf<String, String>()
                    if (nombre.isBlank()) nuevosErrores["nombre"] = "Campo obligatorio"
                    if (apellido.isBlank()) nuevosErrores["apellido"] = "Campo obligatorio"
                    if (fechaNacimiento.isBlank()) nuevosErrores["fecha"] = "Indica tu fecha de nacimiento"
                    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) nuevosErrores["email"] = "Email no válido"
                    if (telefono.isNotBlank() && telefono.length < 9) nuevosErrores["telefono"] = "Teléfono no válido"
                    if (nombreUsuario.isBlank()) nuevosErrores["usuario"] = "Nombre de usuario requerido"
                    if (contrasena.length < 6) nuevosErrores["contrasena"] = "Mínimo 6 caracteres"
                    if (contrasena != confirmacionContrasena) nuevosErrores["confirmacion"] = "No coinciden"
                    if (!terminosAceptados) nuevosErrores["terminos"] = "Debes aceptar los términos"

                    errores = nuevosErrores

                    if (nuevosErrores.isEmpty()) {
                        // Guardamos usuario y contraseña en el ViewModel
                        formularioVM.registroUser(nombreUsuario, email, contrasena)

                        // Mostrar Toast de éxito
                        Toast.makeText(context, "¡Cuenta creada con éxito!", Toast.LENGTH_LONG).show()

                        // Volver a la pantalla de login una vez hecho el registro
                        navController.popBackStack()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.DarkGray,
                    contentColor = Color.White,
                    disabledContainerColor = Color.DarkGray,
                    disabledContentColor = Color.LightGray
                )
            ) {
                Text(
                    text = "CREAR CUENTA",
                    fontSize = 18.sp
                )
            }
        }
    }
}

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector? = null,
    modifier: Modifier = Modifier.fillMaxWidth(),
    isPassword: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    error: String? = null
) {
    Column(modifier = modifier) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            leadingIcon = icon?.let { { Icon(it, contentDescription = null) } },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            isError = error != null,
            singleLine = true
        )
        if (error != null) {
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
    }
}
