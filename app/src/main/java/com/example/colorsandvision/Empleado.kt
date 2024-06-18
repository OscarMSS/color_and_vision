package com.example.colorsandvision


import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.colorsandvision.Components.Alert
import com.example.colorsandvision.viewModels.LoginViewModel



@Composable
fun Empleado(navigationController: NavHostController){
    val navegation = navigationController
    val loginVM = LoginViewModel()
    BackgroundImage()
    LoginEmpleado(navegation, loginVM)
}

@Composable
fun LoginEmpleado(navegation:NavHostController, loginVM: LoginViewModel) {

    val scroll = rememberScrollState(0) //Estado scroll



    var preguntaSelected by remember { mutableStateOf("") }
    var expandedPregunta by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmarPassword by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var userapellido by remember { mutableStateOf("") }
    var respuesta by remember { mutableStateOf("") }



    var nombreError by remember { mutableStateOf<String?>(null) }
    var apellidoError by remember { mutableStateOf<String?>(null) }
    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var confirmarPasswordError by remember { mutableStateOf<String?>(null) }
    var passwordHasFocus by remember { mutableStateOf(false) }
    var confirmarPasswordHasFocus by remember { mutableStateOf(false) }



    fun String.isPasswordValid(): Boolean {
        val passwordRegex = Regex("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#\\\$%^&*()\\\\-_=+,.?\\\":;<>|\\\\[\\\\]{}~]).{8,}\$")
        return passwordRegex.matches(this)
    }

    fun String.isEmailValid(): Boolean {
        val emailRegex = Regex("[a-zA-Z0-9._%+-]+@(gmail\\.com|hotmail\\.com|outlook\\.com|microsoft\\.com|zoho\\.com|yahoo\\.com)")
        return emailRegex.matches(this)
    }

    fun validateFields(): Boolean {
        var isValid = true

        if (username.isBlank()) {
            nombreError = "Campo obligatorio"
            isValid = false
        } else {
            nombreError = null
        }

        if (userapellido.isBlank()) {
            apellidoError = "Campo obligatorio"
            isValid = false
        } else {
            apellidoError = null
        }

        if (email.isBlank()) {
            emailError = "Campo obligatorio"
            isValid = false
        } else if (!email.isEmailValid()) {
            emailError = "Correo electrónico inválido"
            isValid = false
        } else {
            emailError = null
        }

        if (password.isBlank()) {
            passwordError = "Campo obligatorio"
            isValid = false
        } else if (!password.isPasswordValid()) {
            passwordError = "Contraseña inválida"
            isValid = false
        } else {
            passwordError = null
        }

        if (confirmarPassword.isBlank()) {
            confirmarPasswordError = "Campo obligatorio"
            isValid = false
        } else if (confirmarPassword != password) {
            confirmarPasswordError = "Las contraseñas no coinciden"
            isValid = false
        } else {
            confirmarPasswordError = null
        }

        return isValid
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scroll)   //Habilitar el scroll verticalmente
            .navigationBarsPadding(), // Habilitar padding para la barra de navegación,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Crear Usuario", fontSize = 28.sp, fontWeight = FontWeight.Bold)

        // Nombre
        Spacer(modifier = Modifier.height(16.dp))
        // Declarar un nuevo estado para rastrear si el campo de nombre tiene foco
        var nombreHasFocus by remember { mutableStateOf(false) }
        // TextField para el nombre
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = {
                Text(
                    text = "Nombre",
                    color = colorResource(id = R.color.AzulMarino),
                    fontFamily = FontFamily.Serif
                )
            },
            trailingIcon = {
                if (nombreError != null && nombreHasFocus) {
                    Icon(Icons.Default.Warning, contentDescription = null, tint = Color.Red)
                }
            },
            isError = nombreError != null,
            modifier = Modifier
                // Lógica para mostrar el error solo cuando se pierde el foco
                .onFocusChanged { focusState ->
                    if (!focusState.isFocused) {
                        nombreError = if (username.isBlank()) "Campo obligatorio" else null
                    }
                    // Actualizar el estado del foco
                    nombreHasFocus = focusState.isFocused
                }
        )
        // Mostrar el mensaje de error solo si hay un error y el campo tiene foco
        if (nombreError != null && nombreHasFocus) {
            Text(text = nombreError!!, color = Color.Red, fontSize = 12.sp)
        }

        // Apellido
        Spacer(modifier = Modifier.height(16.dp))
        var apellidoHasFocus by remember { mutableStateOf(false) }
        OutlinedTextField(
            value = userapellido,
            onValueChange = { userapellido = it },
            label = {
                Text(
                    text = "Apellido",
                    color = colorResource(id = R.color.AzulMarino),
                    fontFamily = FontFamily.Serif
                )
            },
            trailingIcon = {
                if (apellidoError != null && apellidoHasFocus) {
                    Icon(Icons.Default.Warning, contentDescription = null, tint = Color.Red)
                }
            },
            isError = apellidoError != null,
            modifier = Modifier
                .onFocusChanged {
                focusState ->
                if (!focusState.isFocused) {
                    apellidoError = if (userapellido.isBlank()) "Campo obligatorio" else null
                }
                    //Actualizar el estado del foco
                    apellidoHasFocus = focusState.isFocused
            }
        )
        if (apellidoError != null && apellidoHasFocus) {
            Text(text = apellidoError!!, color = Color.Red, fontSize = 12.sp)
        }

        // Email
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = {
                Text(
                    text = "Email",
                    color = colorResource(id = R.color.AzulMarino),
                    fontFamily = FontFamily.Serif
                )
            },
            trailingIcon = {
                if (emailError != null) {
                    Icon(Icons.Default.Warning, contentDescription = null, tint = Color.Red)
                }
            },
            isError = emailError != null,
            modifier = Modifier.onFocusChanged { focusState ->
                if (!focusState.isFocused) {
                    emailError = when {
                        email.isBlank() -> "Campo obligatorio"
                        !email.isEmailValid() -> "Correo electrónico inválido"
                        else -> null
                    }
                }
            }
        )
        if (emailError != null) {
            Text(text = emailError!!, color = Color.Red, fontSize = 12.sp)
        }

        // Contraseña
        Spacer(modifier = Modifier.height(16.dp))
        var isPasswordVisible by remember { mutableStateOf(false) }
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = {
                Text(
                    text = "Contraseña",
                    color = colorResource(id = R.color.AzulMarino),
                    fontFamily = FontFamily.Serif
                )
            },
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                Row {
                    if (passwordError != null) {
                        Icon(Icons.Default.Warning, contentDescription = null, tint = Color.Red)
                    }
                    Icon(
                        painter = if (isPasswordVisible) painterResource(id = R.drawable.ic_visibility_off)
                        else painterResource(id = R.drawable.ic_visibility),
                        contentDescription = null,
                        modifier = Modifier.clickable { isPasswordVisible = !isPasswordVisible }
                    )
                }
            },
            isError = passwordError != null,
            modifier = Modifier.onFocusChanged { focusState ->
                if (!focusState.isFocused) {
                    passwordError = when {
                        password.isBlank() -> "Campo obligatorio"
                        !password.isPasswordValid() -> "Contraseña inválida"
                        else -> null
                    }
                }
                passwordHasFocus = focusState.isFocused
            }
        )
        if (passwordError != null) {
            Text(text = passwordError!!, color = Color.Red, fontSize = 12.sp)
        }
        if (passwordHasFocus) {
            Box(
                modifier = Modifier
                    .size(width = 250.dp, height = 45.dp)
                    .padding(top = 4.dp)
            ) {
                Text(
                    text = "Puedes usar letras mayúsculas, minúsculas, números y caracteres especiales.",
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            }
        }

        // Confirmar Contraseña
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = confirmarPassword,
            onValueChange = { confirmarPassword = it },
            label = {
                Text(
                    text = "Validar Contraseña",
                    color = colorResource(id = R.color.AzulMarino),
                    fontFamily = FontFamily.Serif
                )
            },
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                Row {
                    if (confirmarPasswordError != null) {
                        Icon(Icons.Default.Warning, contentDescription = null, tint = Color.Red)
                    }
                    Icon(
                        painter = if (isPasswordVisible) painterResource(id = R.drawable.ic_visibility_off)
                        else painterResource(id = R.drawable.ic_visibility),
                        contentDescription = null,
                        modifier = Modifier.clickable { isPasswordVisible = !isPasswordVisible }
                    )
                }
            },
            isError = confirmarPasswordError != null,
            modifier = Modifier.onFocusChanged { focusState ->
                if (!focusState.isFocused) {
                    confirmarPasswordError = when {
                        confirmarPassword.isBlank() -> "Campo obligatorio"
                        confirmarPassword != password -> "Las contraseñas no coinciden"
                        else -> null
                    }
                }
                confirmarPasswordHasFocus = focusState.isFocused
            }
        )
        if (confirmarPasswordError != null) {
            Text(text = confirmarPasswordError!!, color = Color.Red, fontSize = 12.sp)
        }
        if (confirmarPasswordHasFocus) {
            Box(
                modifier = Modifier
                    .size(width = 250.dp, height = 45.dp)
                    .padding(top = 4.dp)
            ) {
                Text(
                    text = "Recuerda que las contraseñas tienen que coincidir",
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            }
        }

        /*Pregunta
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Pregunta",
            color = colorResource(id = R.color.AzulMarino),
            fontFamily = FontFamily.Serif
        )
        OutlinedTextField(
            value = preguntaSelected,
            onValueChange = { preguntaSelected = it },
            enabled = false,
            readOnly = true,
            modifier = Modifier
                .clickable {
                    expandedPregunta = true
                }
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp),
            colors = TextFieldDefaults.colors(Color.White),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        DropdownMenu(
            expanded = expandedPregunta,
            onDismissRequest = { expandedPregunta = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            pregunta.forEach { preguntaItem ->
                DropdownMenuItem(
                    text = { Text(text = preguntaItem) },
                    onClick = {
                        expandedPregunta = false
                        preguntaSelected = preguntaItem
                    }
                )
            }
        }

        //Respuesta
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = respuesta, onValueChange = {
            respuesta = it
        }, label={
            Text(text = "Respuesta",
                color = colorResource(id = R.color.AzulMarino),
                fontFamily = FontFamily.Serif)
        })*/

        //Boton para registrarse
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if (validateFields()) {
                        loginVM.createUser(email, password, username, userapellido) {
                            navegation.navigate("Login")
                        }

                }
            },colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xff1C2D66)),
            shape = CutCornerShape(8.dp)
        ) {
            Text(text = "Registrarse",
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold)
        }
        if (loginVM.showAlert) {
            Alert(
                title = "Alerta",
                message = "Usuario No Creado",
                confirmText = "Aceptar",
                onConfirmClick = { loginVM.closeAlert() }) {
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        TextButton(
            onClick = {
                navegation.navigate("Login")
            }
        ) {
            Text(
                text = "¿Ya tienes una cuenta? Acceso",
                color = colorResource(id = R.color.AzulMarino),
                fontFamily = FontFamily.Serif
            )
        }
    }
}
