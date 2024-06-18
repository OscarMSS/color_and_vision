@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.colorsandvision

import android.icu.util.Calendar
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.wear.compose.material.MaterialTheme
import com.example.colorsandvision.model.ExamenModel
import com.example.colorsandvision.model.VentaModel
import com.example.colorsandvision.viewModels.ExamenViewModel
import com.example.colorsandvision.viewModels.PacienteViewModel
import com.example.colorsandvision.viewModels.VentaViewModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.util.UUID


@Composable
fun FondoExamen(){
    val backgroundImage = painterResource(id = R.drawable.fondo4)
    Box (
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Image(
            painter = backgroundImage,
            contentDescription = "Imagen de fondo",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )
    }
}


@Composable
fun ExamenVista(navigationController: NavHostController, pacienteVM: PacienteViewModel){
    FondoExamen()

    val navegation = navigationController


    var nombre by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var enfermedades by remember { mutableStateOf("") }
    var IDPaciente by remember { mutableStateOf("") }
    var observaciones by remember { mutableStateOf("") }
    var lineaOD by remember { mutableStateOf("") }
    var lineaOI by remember { mutableStateOf("") }
    var lineaAOOI by remember { mutableStateOf("") }
    var lineaAOOD by remember { mutableStateOf("") }
    var esferaOD by remember { mutableStateOf("") }
    var esferaOI by remember { mutableStateOf("") }
    var ejeoi by remember { mutableStateOf("") }
    var ejeod by remember { mutableStateOf("") }
    var cilindroOD by remember { mutableStateOf("") }
    var cilindroOI by remember { mutableStateOf("") }
    var presbiciaOD by remember { mutableStateOf("") }
    var presbiciaOI by remember { mutableStateOf("") }
    var obser by remember { mutableStateOf("") }

    // Variables para los campos obligatorios
    var lineaODError by remember { mutableStateOf(false) }
    var lineaOIError by remember { mutableStateOf(false) }
    var lineaAOODError by remember { mutableStateOf(false) }
    var lineaAOOIError by remember { mutableStateOf(false) }
    var esferaODError by remember { mutableStateOf(false) }
    var esferaOIError by remember { mutableStateOf(false) }


    val scroll = rememberScrollState(0) //Estado scroll
    val examenVM = ExamenViewModel()
    val calendar = Calendar.getInstance().time
    val dateFormat = DateFormat.getDateInstance().format(calendar)

    // Expresiones regulares para las validaciones
    val numberRegex = Regex("^-?\\d{0,5}(\\.\\d{0,2})?$")


    // Function to fetch patient data from Firebase
    fun fetchPatientData(id: String) {
        val db = FirebaseFirestore.getInstance()
        db.collection("paciente").document(id)
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    nombre = document.getString("nombre") ?: ""
                    edad = document.getString("edad") ?: ""
                    enfermedades = document.getString("enfermedades") ?: ""
                    observaciones = document.getString("observaciones") ?: ""
                }
            }
            .addOnFailureListener { exception ->
                // Handle the error
            }
    }


    // Function to check if all required fields are filled
    fun validateFields(): Boolean {
        return lineaOD.isNotEmpty() && lineaOI.isNotEmpty() && lineaAOOI.isNotEmpty() &&
                lineaAOOD.isNotEmpty() && esferaOD.isNotEmpty() && esferaOI.isNotEmpty() &&
                cilindroOD.isNotEmpty() && cilindroOI.isNotEmpty() &&
                presbiciaOD.isNotEmpty() && presbiciaOI.isNotEmpty()
    }


    Column (
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scroll)   //Habilitar el scroll verticalmente
            .navigationBarsPadding(), // Habilitar padding para la barra de navegación
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        // Texto Examen
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = "Examen de la Vista",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif,
            color = colorResource(id = R.color.AzulMarino)
        )

        // Buscador
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
                    text = "Enfermedades: $enfermedades",
                    color = colorResource(id = R.color.AzulMarino),
                    fontFamily = FontFamily.Serif
                )
                Text(
                    text = "Observaciones: $observaciones",
                    color = colorResource(id = R.color.AzulMarino),
                    fontFamily = FontFamily.Serif
                )
            }
        }


        // Linea OD
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = lineaOD, onValueChange = {
            if (it.isEmpty() || numberRegex.matches(it)) {
                lineaOD = it
                lineaODError = false
            } else {
                lineaODError = true
            }
        }, label={
            Text(text = "Linea OD",
                color = colorResource(id = R.color.AzulMarino),
                fontFamily = FontFamily.Serif)
        })
        if (lineaODError) {
            Text(
                text = "Linea OD es un campo obligatorio",
                color = Color.Red,
                fontSize = 12.sp
            )
        }


        // Linea OI
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = lineaOI, onValueChange = {
            if (it.isEmpty() || numberRegex.matches(it)) {
                lineaOI = it
                lineaOIError = false
            } else {
                lineaOIError = true
            }
        }, label={
            Text(text = "Linea OI",
                color = colorResource(id = R.color.AzulMarino),
                fontFamily = FontFamily.Serif)
        })
        if (lineaOIError) {
            Text(
                text = "Línea OI es un campo obligatorio",
                color = Color.Red,
                fontSize = 12.sp
            )
        }

        // Linea AOOD
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = lineaAOOD, onValueChange = {
            if (it.isEmpty() || numberRegex.matches(it)) {
                lineaAOOD = it
                lineaAOODError = false
            } else {
                lineaAOODError = true
            }
        }, label={
            Text(text = "Linea AOOD",
                color = colorResource(id = R.color.AzulMarino),
                fontFamily = FontFamily.Serif)
        })
        if (lineaAOODError) {
            Text(
                text = "Linea AOOD es un campo obligatorio",
                color = Color.Red,
                fontSize = 12.sp
            )
        }

        // Linea AOOI
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = lineaAOOI, onValueChange = {
            if (it.isEmpty() || numberRegex.matches(it)) {
                lineaAOOI = it
                lineaAOOIError = false
            } else {
                lineaAOOIError = true
            }
        }, label={
            Text(text = "Linea AOOI",
                color = colorResource(id = R.color.AzulMarino),
                fontFamily = FontFamily.Serif)
        })
        if (lineaAOOIError) {
            Text(
                text = "Linea AOOI es un campo obligatorio",
                color = Color.Red,
                fontSize = 12.sp
            )
        }

        // Esfera OD
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = esferaOD, onValueChange = {
            if (it.isEmpty() || numberRegex.matches(it)) {
                esferaOD = it
                esferaODError = false
            } else {
                esferaODError = true
            }
        }, label={
            Text(text = "Esfera OD",
                color = colorResource(id = R.color.AzulMarino),
                fontFamily = FontFamily.Serif)
        })
        if (esferaODError) {
            Text(
                text = "Esfera OD es un campo obligatorio",
                color = Color.Red,
                fontSize = 12.sp
            )
        }

        // Esfera OI
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = esferaOI, onValueChange = {
            if (it.isEmpty() || numberRegex.matches(it)) {
                esferaOI = it
                esferaOIError = false
            } else {
                esferaOIError = true
            }
        }, label={
            Text(text = "Esfera OI",
                color = colorResource(id = R.color.AzulMarino),
                fontFamily = FontFamily.Serif)
        })
        if (esferaOIError) {
            Text(
                text = "Esfera OI es un campo obligatorio",
                color = Color.Red,
                fontSize = 12.sp
            )
        }

        // Cilindro OD
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = cilindroOD, onValueChange = {
            if (it.isEmpty() || numberRegex.matches(it)) {
                cilindroOD = it
            }
        }, label={
            Text(text = "Cilindro OD",
                color = colorResource(id = R.color.AzulMarino),
                fontFamily = FontFamily.Serif)
        })


        // Cilindro OI
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = cilindroOI, onValueChange = {
            if (it.isEmpty() || numberRegex.matches(it)) {
                cilindroOI = it
            }
        }, label={
            Text(text = "Cilindro OI",
                color = colorResource(id = R.color.AzulMarino),
                fontFamily = FontFamily.Serif)
        })


        // Presbicia OD
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = presbiciaOD, onValueChange = {
            if (it.isEmpty() || numberRegex.matches(it)) {
                presbiciaOD = it
            }
        }, label={
            Text(text = "Presbicia OD",
                color = colorResource(id = R.color.AzulMarino),
                fontFamily = FontFamily.Serif)
        })


        // Presbicia OI
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = presbiciaOI, onValueChange = {
            if (it.isEmpty() || numberRegex.matches(it)) {
                presbiciaOI = it
            }
        }, label={
            Text(text = "Presbicio OI",
                color = colorResource(id = R.color.AzulMarino),
                fontFamily = FontFamily.Serif)

        })

        // Ejeoi
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = ejeoi, onValueChange = {
            if (it.isEmpty() || numberRegex.matches(it)) {
                ejeoi = it
            }
        }, label={
            Text(text = "EjeOI",
                color = colorResource(id = R.color.AzulMarino),
                fontFamily = FontFamily.Serif)

        })

        // Ejeod
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = ejeod, onValueChange = {
            if (it.isEmpty() || numberRegex.matches(it)) {
                ejeod = it
            }
        }, label={
            Text(text = "EjeOD",
                color = colorResource(id = R.color.AzulMarino),
                fontFamily = FontFamily.Serif)

        })

        // Observaciones
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = obser, onValueChange = {
            obser = it
        }, label={
            Text(text = "Observaciones",
                color = colorResource(id = R.color.AzulMarino),
                fontFamily = FontFamily.Serif)
        },
            maxLines = 50)

        // Boton Añadir
        Spacer(modifier = Modifier.height(16.dp))
        Button(modifier = Modifier
            .width(200.dp)
            .height(50.dp),
            onClick = {
                var allFieldsValid = true

                if (lineaOD.isBlank() || !numberRegex.matches(lineaOD)) {
                    lineaODError = true
                    allFieldsValid = false
                }
                if (lineaOI.isBlank() || !numberRegex.matches(lineaOI)) {
                    lineaOIError = true
                    allFieldsValid = false
                }
                if (lineaAOOD.isBlank() || !numberRegex.matches(lineaAOOD)) {
                    lineaAOODError = true
                    allFieldsValid = false
                }
                if (lineaAOOI.isBlank() || !numberRegex.matches(lineaAOOI)) {
                    lineaAOOIError = true
                    allFieldsValid = false
                }
                if (esferaOD.isBlank() || !numberRegex.matches(esferaOD)) {
                    esferaODError = true
                    allFieldsValid = false
                }
                if (esferaOI.isBlank() || !numberRegex.matches(esferaOI)) {
                    esferaOIError = true
                    allFieldsValid = false
                }


                if (allFieldsValid) {
                    val examen = ExamenModel(
                        ExamenId = UUID.randomUUID()
                            .toString(), // o cualquier otro identificador único
                        lineaOD = lineaOD,
                        lineaOI = lineaOI,
                        lineaAOOD = lineaAOOD,
                        lineaAOOI = lineaAOOI,
                        cilindroOD = cilindroOD,
                        cilindroOI = cilindroOI,
                        presbiciaOD = presbiciaOD,
                        presbiciaOI = presbiciaOI,
                        observaciones = observaciones,
                        esferaOD = esferaOD,
                        esferaOI = esferaOI,
                        ejeoi =  ejeoi,
                        ejeod = ejeod
                    )
                    examenVM.addExamen(examen)
                    navegation.navigate("Menu")
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xff1C2D66)
            ),
            shape = CutCornerShape(8.dp)
        ) {
            Text(text = "Añadir",
                color = colorResource(id = R.color.white),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif)
        }


    }
}
