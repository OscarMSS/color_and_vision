package com.example.colorsandvision.Navegation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.colorsandvision.Catalogo
import com.example.colorsandvision.Empleado
import com.example.colorsandvision.ExamenVista
import com.example.colorsandvision.ListarExamenes
import com.example.colorsandvision.Login
import com.example.colorsandvision.Menu
import com.example.colorsandvision.PacientesScreen
import com.example.colorsandvision.Recuperar
import com.example.colorsandvision.RegistroPaciente
import com.example.colorsandvision.SelectImageAndUploadScreen
import com.example.colorsandvision.Venta
import com.example.colorsandvision.viewModels.PacienteViewModel
import com.example.colorsandvision.viewModels.PasswordViewModel
import com.google.firebase.database.FirebaseDatabase


@Composable
fun NavManager(){
    val navigationController = rememberNavController()
    var database = FirebaseDatabase.getInstance().reference
    var pacienteVM = PacienteViewModel()

    NavHost(navController = navigationController, startDestination = "Login"){
        composable("Login"){
            Login(navigationController)
        }
        composable("Empleado"){
            Empleado(navigationController)
        }
        composable("Menu"){
            Menu(navigationController)
        }
        composable("Paciente"){
            RegistroPaciente(navigationController)
        }
        composable("Examen"){
            ExamenVista(navigationController, pacienteVM)
        }
        composable("Venta"){
            Venta(navigationController)
        }
        composable("Catalogo"){
            Catalogo(navigationController)
        }
        composable("Recuperar"){
            Recuperar(navigationController, PasswordViewModel())
        }
        composable("selectAndUpload") {
            SelectImageAndUploadScreen()
        }
        composable("ConsultaPacientes") {
            PacientesScreen(navigationController )
        }
        composable("ConsultaExamen") {
            ListarExamenes(navigationController)
        }
    }
}