@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.colorsandvision

import android.icu.util.Calendar
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.firestore.FirebaseFirestore
import java.text.DateFormat
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.colorsandvision.model.PacienteModel
import com.example.colorsandvision.model.VentaModel
import com.example.colorsandvision.viewModels.VentaViewModel
import java.util.UUID

// expresiones regulares para las validaciones
val nombreApellidoRegex = Regex("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]*$")
val numeroRegex = Regex("^[0-9]+$")
val precioRegex = Regex("^[0-9]*\\.?[0-9]+$")
val fechaRegex = Regex("^\\d{2}/\\d{2}/\\d{4}$")

fun isValidDate(date: String): Boolean {
    if (!fechaRegex.matches(date)) return false
    val parts = date.split("/")
    val day = parts[0].toIntOrNull() ?: return false
    val month = parts[1].toIntOrNull() ?: return false
    val year = parts[2].toIntOrNull() ?: return false
    if (day !in 1..31) return false
    if (month !in 1..12) return false
    if (year < 1900 || year > 2100) return false // You can adjust year range as needed

    // Further validation can be added to check for correct days in month, leap years, etc.
    return true
}

@Composable
fun Venta(navigationController: NavHostController) {
    FondoRegistro()
    val scroll = rememberScrollState(0)
    val navegation = navigationController
    val calendar = Calendar.getInstance().time
    val dateFormat = DateFormat.getDateInstance().format(calendar)
    val ventaVM = VentaViewModel()

    var IDPaciente by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var celular by remember { mutableStateOf("") }
    var modelo by remember { mutableStateOf("") }
    var serie by remember { mutableStateOf("") }
    var material by remember { mutableStateOf("") }
    var accerorio by remember { mutableStateOf("") }
    var tratamiento by remember { mutableStateOf("") }
    var precioLente by remember { mutableStateOf("") }
    var precioAdic by remember { mutableStateOf("") }
    var fechaentrega by remember { mutableStateOf("") }

    // Variables de estado para manejar los errores
    var precioLenteError by remember { mutableStateOf(false) }
    var precioAdicError by remember { mutableStateOf(false) }
    var fechaentregaError by remember { mutableStateOf(false) }

    val total = try {
        precioAdic.toDouble() + precioLente.toDouble() + (200 * serie.toDouble())
    } catch (e: NumberFormatException) {
        0.0
    }

    // Function to fetch patient data from Firebase
    fun fetchPatientData(id: String) {
        val db = FirebaseFirestore.getInstance()
        db.collection("paciente").document(id)
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    nombre = document.getString("nombre") ?: ""
                    edad = document.getString("edad") ?: ""
                    celular = document.getString("celular") ?: ""
                }
            }
            .addOnFailureListener { exception ->
                // Handle the error
            }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scroll)
            .navigationBarsPadding(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = "Venta",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif,
            color = colorResource(id = R.color.AzulMarino)
        )

        Spacer(modifier = Modifier.height(16.dp))
        var buscador by remember { mutableStateOf(false) }
        OutlinedTextField(
            value = IDPaciente,
            onValueChange = {
                IDPaciente = it
                fetchPatientData(IDPaciente)
            },
            label = {
                Text(
                    text = "ID Paciente",
                    color = colorResource(id = R.color.AzulMarino),
                    fontFamily = FontFamily.Serif
                )
            },
            trailingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Buscador")
            }
        )

        Spacer(modifier = Modifier.height(16.dp))
        Card(
            modifier = Modifier
                .width(350.dp)
                .height(150.dp),
            elevation = CardDefaults.cardElevation(1.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Fecha: $dateFormat",
                    color = colorResource(id = R.color.AzulMarino),
                    fontFamily = FontFamily.Serif
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Nombre: $nombre",
                    color = colorResource(id = R.color.AzulMarino),
                    fontFamily = FontFamily.Serif
                )
                Text(
                    text = "Edad: $edad",
                    color = colorResource(id = R.color.AzulMarino),
                    fontFamily = FontFamily.Serif
                )
                Text(
                    text = "Celular: $celular",
                    color = colorResource(id = R.color.AzulMarino),
                    fontFamily = FontFamily.Serif
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = modelo, onValueChange = {
                modelo = it
            },
            label = {
                Text(
                    text = "Modelo",
                    color = colorResource(id = R.color.AzulMarino),
                    fontFamily = FontFamily.Serif
                )
            }
        )

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = serie, onValueChange = {
                if (numeroRegex.matches(it)) {
                    serie = it
                }
            },
            label = {
                Text(
                    text = "Serie",
                    color = colorResource(id = R.color.AzulMarino),
                    fontFamily = FontFamily.Serif
                )
            }
        )

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = material, onValueChange = {
                if (nombreApellidoRegex.matches(it)) {
                    material = it
                }
            },
            label = {
                Text(
                    text = "Material",
                    color = colorResource(id = R.color.AzulMarino),
                    fontFamily = FontFamily.Serif
                )
            }
        )

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = accerorio, onValueChange = {
                if (nombreApellidoRegex.matches(it)) {
                    accerorio = it
                }
            },
            label = {
                Text(
                    text = "Accesorio",
                    color = colorResource(id = R.color.AzulMarino),
                    fontFamily = FontFamily.Serif
                )
            }
        )

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = tratamiento, onValueChange = {
                if (nombreApellidoRegex.matches(it)) {
                    tratamiento = it
                }
            },
            label = {
                Text(
                    text = "Tratamiento",
                    color = colorResource(id = R.color.AzulMarino),
                    fontFamily = FontFamily.Serif
                )
            }
        )

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = precioLente, onValueChange = {
                if (precioRegex.matches(it)) {
                    precioLente = it
                }
            },
            label = {
                Text(
                    text = "Precio del lente",
                    color = colorResource(id = R.color.AzulMarino),
                    fontFamily = FontFamily.Serif
                )
            }
        )
        if (precioLenteError) {
            Text(
                text = "Precio del lente es un campo obligatorio en caso de no haber un precio de lente ingrese 0",
                color = Color.Red,
                fontSize = 12.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = precioAdic, onValueChange = {
                if (precioRegex.matches(it)) {
                    precioAdic = it
                }
            },
            label = {
                Text(
                    text = "Precio adicional",
                    color = colorResource(id = R.color.AzulMarino),
                    fontFamily = FontFamily.Serif
                )
            }
        )
        if (precioAdicError) {
            Text(
                text = "Precio adicional es un campo obligatorio, en caso de no haber un precio adicional ingrese 0",
                color = Color.Red,
                fontSize = 12.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = fechaentrega,
            onValueChange = {
                if (isValidDate(it)) {
                    fechaentrega = it
                    fechaentregaError = false // Reset error if valid
                } else {
                    fechaentrega = it
                    fechaentregaError = true // Set error if invalid
                }
            },
            label = {
                Text(
                    text = "Fecha de Entrega (dd/mm/yyyy)",
                    color = colorResource(id = R.color.AzulMarino),
                    fontFamily = FontFamily.Serif
                )
            }
        )
        if (fechaentregaError) {
            Text(
                text = "Fecha de entrega debe estar en formato dd/mm/yyyy",
                color = Color.Red,
                fontSize = 12.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Total: $total",
            color = colorResource(id = R.color.AzulMarino),
            fontFamily = FontFamily.Serif
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier
                .width(200.dp)
                .height(50.dp),
            onClick = {
                // Reset all errors
                precioLenteError = false
                precioAdicError = false
                fechaentregaError = false

                var allFieldsValid = true

                // Validate fields
                if (!precioRegex.matches(precioLente)) {
                    precioLenteError = true
                    allFieldsValid = false
                }
                if (!precioRegex.matches(precioAdic)) {
                    precioAdicError = true
                    allFieldsValid = false
                }
                if (!isValidDate(fechaentrega)) {
                    fechaentregaError = true
                    allFieldsValid = false
                }

                if (allFieldsValid) {
                    val venta = VentaModel(
                        ventaId = UUID.randomUUID().toString(), // o cualquier otro identificador único
                        accerorio = accerorio,
                        modelo = modelo,
                        serie = serie,
                        tratamiento = tratamiento,
                        precioLente = precioLente,
                        material = material,
                        precioAdic = precioAdic,
                        fechaentrega = fechaentrega,
                        total = total.toString()
                    )
                    ventaVM.addVenta(venta)
                    navegation.navigate("Menu")
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xff64BDCD)
            ),
            shape = CutCornerShape(8.dp)
        ) {
            Text(
                text = "Guardar",
                color = colorResource(id = R.color.AzulMarino),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier
                .width(200.dp)
                .height(50.dp),
            onClick = {
                navegation.navigate("Menu")
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xff1C2D66)
            ),
            shape = CutCornerShape(8.dp)
        ) {
            Text(
                text = "Regresar",
                color = colorResource(id = R.color.white),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewVenta() {

}

