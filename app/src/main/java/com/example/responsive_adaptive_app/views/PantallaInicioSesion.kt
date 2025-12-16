package com.example.responsive_adaptive_app.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.responsive_adaptive_app.R
import androidx.compose.material3.TextFieldDefaults
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import com.example.responsive_adaptive_app.viewModel.formularioUser
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.runtime.MutableState

@Composable
fun PantallaInicioSesion (navController: NavController, viewModel: formularioUser, windowSize: WindowSizeClass) {
    val emailUsuario = rememberSaveable { mutableStateOf("") }
    val contrasena = rememberSaveable { mutableStateOf("") }
    when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Compact -> InicioSesionCompacto(navController, viewModel, windowSize, emailUsuario, contrasena)
        WindowWidthSizeClass.Medium -> InicioSesionMedium(navController, viewModel, windowSize, emailUsuario, contrasena)
        WindowWidthSizeClass.Expanded -> InicioSesionExpanded(navController, viewModel, windowSize, emailUsuario, contrasena)
        else -> InicioSesionCompacto(navController, viewModel, windowSize, emailUsuario, contrasena)
    }
}

@Composable
fun InicioSesionCompacto (navController: NavController, viewModel: formularioUser, windowSize: WindowSizeClass, emailUsuario: MutableState<String>, contrasena: MutableState<String>) {
    Column(
        modifier = Modifier.fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Banner()
        FormularioLogin(navController = navController, viewModel = viewModel, modifier = Modifier.fillMaxWidth(1f), windowSize = windowSize, emailUsuario = emailUsuario, contrasena = contrasena
        )
    }
}

@Composable
fun InicioSesionMedium (navController: NavController, viewModel: formularioUser, windowSize: WindowSizeClass, emailUsuario: MutableState<String>, contrasena: MutableState<String>) {
    Column(modifier = Modifier.fillMaxSize()) {
        Banner()
        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 32.dp, vertical = 24.dp),
            contentAlignment = Alignment.Center
        ) {
            FormularioLogin(
                navController = navController,
                viewModel = viewModel,
                modifier = Modifier.fillMaxWidth(1f
            ),windowSize = windowSize, emailUsuario = emailUsuario,
            contrasena = contrasena)
        }
    }
}

@Composable
fun InicioSesionExpanded (navController: NavController, viewModel: formularioUser, windowSize: WindowSizeClass, emailUsuario: MutableState<String>, contrasena: MutableState<String>) {
    Row(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())
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
            modifier = Modifier.weight(1f).fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {
            FormularioLogin(navController = navController,
                viewModel = viewModel, modifier = Modifier.fillMaxWidth(0.7f), windowSize = windowSize, emailUsuario = emailUsuario, contrasena = contrasena
            )
        }
    }
}

@Composable
private fun Banner() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp)
            .background(Color(16, 16, 15)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_app_responsive),
            contentDescription = "Logo web",
            modifier = Modifier
                .height(120.dp)
                .padding(bottom = 14.dp)
        )
    }
}
@Composable
private fun FormularioLogin(
    navController: NavController,
    viewModel: formularioUser,
    modifier: Modifier,
    windowSize: WindowSizeClass,
    emailUsuario: MutableState<String>,
    contrasena: MutableState<String>
) {
    val botonHabilitado = emailUsuario.value.isNotBlank() && contrasena.value.isNotBlank()
    val validador = viewModel.login(emailUsuario.value, contrasena.value)
    val usuarioInexistente = LocalContext.current

    if (windowSize.widthSizeClass == WindowWidthSizeClass.Medium) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = emailUsuario.value,
                onValueChange = { emailUsuario.value = it },
                label = {Text("Escribe tu email o nombre de usuario", fontSize = 16.sp)},
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Black,
                    unfocusedIndicatorColor = Color.DarkGray
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                value = contrasena.value,
                onValueChange = { contrasena.value = it },
                label = {Text("Contraseña", fontSize = 16.sp)},
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Black,
                    unfocusedIndicatorColor = Color.DarkGray
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    if (validador) {
                        navController.navigate(Routes.Confirm.route)
                    } else {
                        Toast.makeText(
                            usuarioInexistente,
                            "Usuario no encontrado.",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                },
                enabled = botonHabilitado,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White,
                    disabledContainerColor = Color.DarkGray,
                    disabledContentColor = Color.LightGray,
                )
            ) {
                Text(text = "Iniciar Sesión", fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "¿No tienes cuenta? Pulsa aqui para registrarte.",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { navController.navigate(Routes.RegistroUsuario.route) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .shadow(elevation = 2.dp, shape = RoundedCornerShape(5.dp)),
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black,
                )
            ) {
                Text("Registrarse", fontSize = 18.sp)
            }
        }

    } else {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = emailUsuario.value,
                onValueChange = { emailUsuario.value = it },
                label = { Text("Escribe tu email o nombre de usuario") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Black,
                    unfocusedIndicatorColor = Color.DarkGray
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            TextField(
                value = contrasena.value,
                onValueChange = { contrasena.value = it },
                label = { Text("Contraseña") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Black,
                    unfocusedIndicatorColor = Color.DarkGray
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    if (validador) {
                        navController.navigate(Routes.Confirm.route)
                    } else {
                        Toast.makeText(
                            usuarioInexistente,
                            "Usuario no encontrado.",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                },
                enabled = botonHabilitado,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(42.dp),
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White,
                    disabledContainerColor = Color.DarkGray,
                    disabledContentColor = Color.LightGray,
                )
            ) {
                Text(text = "Iniciar Sesión", fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "¿No tienes cuenta? Pulsa aqui para registrarte.",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { navController.navigate(Routes.RegistroUsuario.route) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(42.dp)
                    .shadow(elevation = 2.dp, shape = RoundedCornerShape(5.dp)),
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black,
                )
            ) {
                Text("Registrarse")
            }
        }
    }
}