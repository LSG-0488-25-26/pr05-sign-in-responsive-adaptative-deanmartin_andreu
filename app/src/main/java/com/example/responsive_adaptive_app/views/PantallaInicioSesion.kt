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
import androidx.compose.foundation.layout.statusBarsPadding
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
import androidx.lifecycle.ViewModel
import com.example.responsive_adaptive_app.viewModel.formularioUser
import androidx.compose.material3.windowsizeclass.WindowSizeClass

@Composable
fun PantallaInicioSesion (navController: NavController, viewModel: formularioUser, windowSize: WindowSizeClass) {

    var emailUsuario by rememberSaveable { mutableStateOf("") }
    var contrasena by rememberSaveable { mutableStateOf("") }
    val botonHabilitado = emailUsuario.isNotBlank() && contrasena.isNotBlank()
    val usuarioInexistente = LocalContext.current

    val validador = viewModel.login(emailUsuario, contrasena)

    Box(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
                .background(Color(16, 16, 15))
                .statusBarsPadding()
                .align(Alignment.TopCenter),
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

        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value=emailUsuario,
                onValueChange = {emailUsuario=it},
                label = {Text("Escribe tu email o nombre de usuario")},
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
                value = contrasena,
                onValueChange = { contrasena = it },
                label = { Text("Contraseña") },
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
                onClick = {navController.navigate(Routes.RegistroUsuario.route)},
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