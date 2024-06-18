package com.example.colorsandvision.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth

class PasswordViewModel {

    private val firebaseAuth = FirebaseAuth.getInstance()

    // Propiedades relacionadas con la alerta y el restablecimiento de contraseña
    var showAlert by mutableStateOf(false)
    var alertMessage by mutableStateOf("")
    var alertEmail by mutableStateOf("")
    var resetPasswordEmailSent by mutableStateOf(false)

    fun closeAlert() {
        showAlert = false
    }

    fun recuperarContrasena(email: String) {
        alertMessage = "¿Estás seguro de que quieres restablecer la contraseña para $email?"
        alertEmail = email
        showAlert = true
    }

    fun confirmRecuperarContrasena(confirmado: Boolean, navController: NavHostController) {
        if (confirmado) {
            val email = alertEmail // Obtener el valor actual de alertEmail
            if (email.isNotEmpty()) { // Verificar que el email no esté vacío
                sendPasswordResetEmail(email) { success ->
                    if (success) {
                        resetPasswordEmailSent = true
                        // Navegar a la pantalla principal (Login)
                        navController.navigate("Login")
                    } else {
                        // Handle error if needed
                    }
                }
            } else {
                // Handle empty email case
                Log.e("Recuperar Contraseña", "La dirección de correo electrónico está vacía")
            }
        } else {
            closeAlert()
        }
    }

    fun sendPasswordResetEmail(email: String, onComplete: (Boolean) -> Unit) {
        firebaseAuth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("Recuperar Contraseña", "Correo de restablecimiento de contraseña enviado")
                    onComplete(true)
                } else {
                    Log.e("Recuperar Contraseña", "Error al enviar el correo de restablecimiento de contraseña", task.exception)
                    onComplete(false)
                }
            }
    }
}