package com.example.responsive_adaptive_app.views

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import com.example.responsive_adaptive_app.R
import com.example.responsive_adaptive_app.viewModel.formularioUser

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaRegistroUsuario(
    navController: NavController,
    formularioVM: formularioUser,
    windowSize: WindowSizeClass
) {
    when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Compact -> RegistroCompact(navController, formularioVM, windowSize)
        WindowWidthSizeClass.Medium -> RegistroMedium(navController, formularioVM, windowSize)
        WindowWidthSizeClass.Expanded -> RegistroExpanded(navController, formularioVM, windowSize)
        else -> RegistroCompact(navController, formularioVM, windowSize)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RegistroCompact(
    navController: NavController,
    formularioVM: formularioUser,
    windowSize: WindowSizeClass
) {
    Scaffold(
        topBar = { RegistroTopBar(navController) },
        containerColor = Color.White
    ) { paddingValues ->
        RegistroUsuarioContent(
            navController = navController,
            formularioVM = formularioVM,
            windowSize = windowSize,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RegistroMedium(
    navController: NavController,
    formularioVM: formularioUser,
    windowSize: WindowSizeClass
) {
    Scaffold(
        topBar = { RegistroTopBar(navController) },
        containerColor = Color.White
    ) { paddingValues ->
        RegistroUsuarioContent(
            navController = navController,
            formularioVM = formularioVM,
            windowSize = windowSize,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 32.dp, vertical = 24.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RegistroExpanded(
    navController: NavController,
    formularioVM: formularioUser,
    windowSize: WindowSizeClass
) {
    Scaffold(
        topBar = { RegistroTopBar(navController) },
        containerColor = Color.White
    ) { paddingValues ->
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(Color(16, 16, 15)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_app_responsive),
                    contentDescription = "Logo",
                    modifier = Modifier.fillMaxWidth(0.6f)
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                contentAlignment = Alignment.TopCenter
            ) {
                RegistroUsuarioContent(
                    navController = navController,
                    formularioVM = formularioVM,
                    windowSize = windowSize,
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(24.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RegistroTopBar(navController: NavController) {
    TopAppBar(
        title = { Text("Registro de Usuario") },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
            titleContentColor = Color.Black,
            navigationIconContentColor = Color.Black
        )
    )
}

@Composable
private fun RegistroUsuarioContent(
    navController: NavController,
    formularioVM: formularioUser,
    windowSize: WindowSizeClass,
    modifier: Modifier
) {
    val context = LocalContext.current

    var nombre by rememberSaveable { mutableStateOf("") }
    var apellido by rememberSaveable { mutableStateOf("") }
    var segundoApellido by rememberSaveable { mutableStateOf("") }
    var fechaNacimiento by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var telefono by rememberSaveable { mutableStateOf("") }
    var nombreUsuario by rememberSaveable { mutableStateOf("") }
    var contrasena by rememberSaveable { mutableStateOf("") }
    var confirmacionContrasena by rememberSaveable { mutableStateOf("") }
    var terminosAceptados by rememberSaveable { mutableStateOf(false) }

    var errores by rememberSaveable { mutableStateOf<Map<String, String>>(emptyMap()) }

    val botonHabilitado =
        nombre.isNotBlank() &&
                apellido.isNotBlank() &&
                fechaNacimiento.isNotBlank() &&
                email.isNotBlank() &&
                telefono.isNotBlank() &&
                nombreUsuario.isNotBlank() &&
                contrasena.isNotBlank() &&
                confirmacionContrasena.isNotBlank() &&
                terminosAceptados &&
                Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
                contrasena.length >= 6 &&
                contrasena == confirmacionContrasena &&
                formularioVM.validarFecha(fechaNacimiento) == null &&
                formularioVM.validarTelefono(telefono) == null

    val alturaBoton = if (windowSize.widthSizeClass == WindowWidthSizeClass.Compact) 42.dp else 56.dp
    val fontBoton = if (alturaBoton == 56.dp) 18.sp else 16.sp

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        CustomTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = "Nombre",
            icon = Icons.Default.Person,
            error = errores["nombre"]
        )

        CustomTextField(
            value = apellido,
            onValueChange = { apellido = it },
            label = "1º Apellido",
            error = errores["apellido"]
        )

        CustomTextField(
            value = segundoApellido,
            onValueChange = { segundoApellido = it },
            label = "2º Apellido"
        )

        CustomTextField(
            value = fechaNacimiento,
            onValueChange = { fechaNacimiento = it },
            label = "Fecha Nacimiento (DD/MM/AAAA)",
            icon = Icons.Default.DateRange,
            error = errores["fecha"]
        )

        CustomTextField(
            value = email,
            onValueChange = { email = it },
            label = "Correo Electrónico",
            icon = Icons.Default.Email,
            keyboardType = KeyboardType.Email,
            error = errores["email"]
        )

        CustomTextField(
            value = telefono,
            onValueChange = { telefono = it },
            label = "Teléfono",
            icon = Icons.Default.Phone,
            keyboardType = KeyboardType.Phone,
            error = errores["telefono"]
        )

        CustomTextField(
            value = nombreUsuario,
            onValueChange = { nombreUsuario = it },
            label = "Nombre de Usuario",
            icon = Icons.Default.AccountCircle,
            error = errores["usuario"]
        )

        CustomTextField(
            value = contrasena,
            onValueChange = { contrasena = it },
            label = "Contraseña",
            icon = Icons.Default.Lock,
            isPassword = true,
            error = errores["contrasena"]
        )

        CustomTextField(
            value = confirmacionContrasena,
            onValueChange = { confirmacionContrasena = it },
            label = "Confirmar Contraseña",
            icon = Icons.Default.Lock,
            isPassword = true,
            error = errores["confirmacion"]
        )

        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = terminosAceptados,
                    onCheckedChange = { terminosAceptados = it }
                )
                Text("Acepto los términos y condiciones", color = Color.Black)
            }
            errores["terminos"]?.let {
                Text(it, color = MaterialTheme.colorScheme.error)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val nuevosErrores = mutableMapOf<String, String>()

                if (nombre.isBlank()) nuevosErrores["nombre"] = "Campo obligatorio"
                if (apellido.isBlank()) nuevosErrores["apellido"] = "Campo obligatorio"
                formularioVM.validarFecha(fechaNacimiento)?.let { nuevosErrores["fecha"] = it }
                formularioVM.validarTelefono(telefono)?.let { nuevosErrores["telefono"] = it }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) nuevosErrores["email"] = "Email no válido"
                if (nombreUsuario.isBlank()) nuevosErrores["usuario"] = "Usuario requerido"
                if (contrasena.length < 6) nuevosErrores["contrasena"] = "Mínimo 6 caracteres"
                if (contrasena != confirmacionContrasena) nuevosErrores["confirmacion"] = "No coinciden"
                if (!terminosAceptados) nuevosErrores["terminos"] = "Debes aceptar los términos"

                errores = nuevosErrores

                if (nuevosErrores.isEmpty()) {
                    formularioVM.registroUser(nombreUsuario, email, contrasena)
                    Toast.makeText(context, "¡Cuenta creada con éxito!", Toast.LENGTH_LONG).show()
                    navController.popBackStack()
                }
            },
            enabled = botonHabilitado,
            modifier = Modifier
                .fillMaxWidth()
                .height(alturaBoton),
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White,
                disabledContainerColor = Color.DarkGray,
                disabledContentColor = Color.LightGray
            )
        ) {
            Text("Crear Cuenta", fontSize = fontBoton)
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
    Column(modifier = modifier.fillMaxWidth())  {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            onValueChange = onValueChange,
            label = { Text(label, color = Color.Black) },
            leadingIcon = icon?.let { { Icon(it, contentDescription = null, tint = Color.DarkGray) } },
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            isError = error != null,
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.DarkGray,
                cursorColor = Color.Black
            )
        )
        if (error != null) {
            Text(text = error, color = MaterialTheme.colorScheme.error)
        }
    }
}