package com.example.colorsandvision

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.colorsandvision.viewModels.PasswordViewModel
import com.example.colorsandvision.Components.Alert

@Composable
fun Recuperar (navigationController: NavHostController,passwordViewModel: PasswordViewModel){
    BackgroundImage()
    val navController = navigationController
    val emailState = remember { mutableStateOf("") }

    // Manejar la alerta
    if (passwordViewModel.showAlert) {
        if (passwordViewModel.showAlert) {
            Alert(
                title = "Restablecer contraseña",
                message = passwordViewModel.alertMessage,
                confirmText = "Sí",
                onConfirmClick = {
                    Log.d("Recuperar", "Botón 'Sí' en cuadro de diálogo de alerta presionado")
                    passwordViewModel.confirmRecuperarContrasena(true,navigationController)
                },
                onDismissClick = {
                    Log.d("Recuperar", "Cuadro de diálogo de alerta cerrado sin confirmar")
                    passwordViewModel.closeAlert()
                }
            )
        }
    }

    Column (modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Ingresa tu correo",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif,
            color = colorResource(id = R.color.AzulMarino)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Recupera tu contraseña",
            fontFamily = FontFamily.Serif,
            color = colorResource(id = R.color.AzulMarino))

        // Email
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = emailState.value,
            onValueChange = { emailState.value = it },
            label = { Text(text = "Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            trailingIcon = {
                if (emailState.value.isNotEmpty()) {
                    Button(
                        onClick = {
                            Log.d("Recuperar", "Botón 'Enviar' presionado")
                            passwordViewModel.recuperarContrasena(emailState.value)
                        },
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "Enviar")
                    }
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
            keyboardActions = KeyboardActions(
                onSend = {
                    Log.d("Recuperar", "Tecla 'Enviar' presionada en el teclado")
                    passwordViewModel.recuperarContrasena(emailState.value)
                }
            )
        )


    }

    // Observar cambios en resetPasswordEmailSent y manejar la navegación
    if (passwordViewModel.resetPasswordEmailSent) {
        LaunchedEffect(Unit) {
            Log.d("Recuperar", "Navegando a 'inicio' después de enviar correo electrónico de recuperación")
            navigationController.navigate("inicio")
        }
    }

}


