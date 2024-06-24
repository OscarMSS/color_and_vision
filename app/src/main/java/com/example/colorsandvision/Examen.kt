@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.colorsandvision

import android.icu.util.Calendar
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.wear.compose.material.MaterialTheme
import com.example.colorsandvision.model.ExamenModel
import com.example.colorsandvision.model.VentaModel
import com.example.colorsandvision.viewModels.ExamenViewModel
import com.example.colorsandvision.viewModels.PacienteViewModel
import com.example.colorsandvision.viewModels.VentaViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
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
fun ExamenVista(navigationController: NavHostController, pacienteVM: PacienteViewModel) {
    FondoExamen()

    val navegation = navigationController

    var nombre by remember { mutableStateOf("") }
    var apellidop by remember { mutableStateOf("") }
    var apellidom by remember { mutableStateOf("") }
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
    var ejeOI by remember { mutableStateOf("") }
    var ejeOD by remember { mutableStateOf("") }
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

    var examenes by remember { mutableStateOf(emptyList<ExamenModel>()) }

    val scroll = rememberScrollState(0) // Estado scroll
    val examenVM = ExamenViewModel()
    val calendar = Calendar.getInstance().time
    val dateFormat = DateFormat.getDateInstance().format(calendar)

    // Expresiones regulares para las validaciones
    val numberRegex = Regex("^-?\\d{0,5}(\\.\\d{0,2})?$")
    val phoneNumberRegex = Regex("^\\d+$")

    // Function to fetch patient data from Firebase
    fun fetchPatientData(celular: String) {
        val db = FirebaseFirestore.getInstance()
        db.collection("paciente")
            .whereEqualTo("celular", celular)
            .get()
            .addOnSuccessListener { documents ->
                if (!documents.isEmpty) {
                    val document = documents.first()
                    nombre = document.getString("nombre") ?: ""
                    apellidop = document.getString("apellidop") ?: ""
                    apellidom = document.getString("apellidom") ?: ""
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

    fun guardarExamen() {
        val examen = ExamenModel(
            ExamenId = UUID.randomUUID().toString(), // Genera un ID único para el examen,
            dateFormat = dateFormat,
            nombre = nombre,
            apellidop = apellidop,
            apellidom = apellidom,
            edad = edad,
            lineaOD = lineaOD,
            lineaOI = lineaOI,
            lineaAOOD = lineaAOOD,
            lineaAOOI = lineaAOOI,
            esferaOD = esferaOD,
            esferaOI = esferaOI,
            ejeOI = ejeOI,
            ejeOD = ejeOD,
            cilindroOD = cilindroOD,
            cilindroOI = cilindroOI,
            presbiciaOD = presbiciaOD,
            presbiciaOI = presbiciaOI,
            observaciones = obser
        )
        // Llama al ViewModel para guardar el examen
        examenVM.addExamen(examen)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scroll)   // Habilitar el scroll verticalmente
            .navigationBarsPadding(), // Habilitar padding para la barra de navegación
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

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
        var celularPaciente by remember { mutableStateOf("") }
        var celularError by remember { mutableStateOf(false) }
        OutlinedTextField(
            value = celularPaciente,
            onValueChange = {
                celularPaciente = it
                celularError = !phoneNumberRegex.matches(it)
                if (!celularError) {
                    fetchPatientData(celularPaciente)
                }
            },
            label = {
                Text(
                    text = "Número de Celular",
                    color = colorResource(id = R.color.AzulMarino),
                    fontFamily = FontFamily.Serif
                )
            },
            trailingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Buscador")
            },
            isError = celularError,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        if (celularError) {
            Text(
                text = "Número de celular inválido",
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

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
                    text = "Nombre: $nombre $apellidop $apellidom",
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
        }, label = {
            Text(text = "Linea OD",
                color = colorResource(id = R.color.AzulMarino),
                fontFamily = FontFamily.Serif)
        })
        if (lineaODError) {
            Text(
                text = "Formato no válido",
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
        }, label = {
            Text(text = "Linea OI",
                color = colorResource(id = R.color.AzulMarino),
                fontFamily = FontFamily.Serif)
        })
        if (lineaOIError) {
            Text(
                text = "Formato no válido",
                color = Color.Red,
                fontSize = 12.sp
            )
        }

        // Línea AO OD
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = lineaAOOD, onValueChange = {
            if (it.isEmpty() || numberRegex.matches(it)) {
                lineaAOOD = it
                lineaAOODError = false
            } else {
                lineaAOODError = true
            }
        }, label = {
            Text(text = "Linea AO OD",
                color = colorResource(id = R.color.AzulMarino),
                fontFamily = FontFamily.Serif)
        })
        if (lineaAOODError) {
            Text(
                text = "Formato no válido",
                color = Color.Red,
                fontSize = 12.sp
            )
        }

        // Línea AO OI
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = lineaAOOI, onValueChange = {
            if (it.isEmpty() || numberRegex.matches(it)) {
                lineaAOOI = it
                lineaAOOIError = false
            } else {
                lineaAOOIError = true
            }
        }, label = {
            Text(text = "Linea AO OI",
                color = colorResource(id = R.color.AzulMarino),
                fontFamily = FontFamily.Serif)
        })
        if (lineaAOOIError) {
            Text(
                text = "Formato no válido",
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
        }, label = {
            Text(text = "Esfera OD",
                color = colorResource(id = R.color.AzulMarino),
                fontFamily = FontFamily.Serif)
        })
        if (esferaODError) {
            Text(
                text = "Formato no válido",
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
        }, label = {
            Text(text = "Esfera OI",
                color = colorResource(id = R.color.AzulMarino),
                fontFamily = FontFamily.Serif)
        })
        if (esferaOIError) {
            Text(
                text = "Formato no válido",
                color = Color.Red,
                fontSize = 12.sp
            )
        }

        // Eje OD
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = ejeOD, onValueChange = {
            if (it.isEmpty() || numberRegex.matches(it)) {
                ejeOD = it
            }
        }, label = {
            Text(text = "Eje OD",
                color = colorResource(id = R.color.AzulMarino),
                fontFamily = FontFamily.Serif)
        })

        // Eje OI
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = ejeOI, onValueChange = {
            if (it.isEmpty() || numberRegex.matches(it)) {
                ejeOI = it
            }
        }, label = {
            Text(text = "Eje OI",
                color = colorResource(id = R.color.AzulMarino),
                fontFamily = FontFamily.Serif)
        })

        // Cilindro OD
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = cilindroOD, onValueChange = {
            if (it.isEmpty() || numberRegex.matches(it)) {
                cilindroOD = it
            }
        }, label = {
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
        }, label = {
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
        }, label = {
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
        }, label = {
            Text(text = "Presbicia OI",
                color = colorResource(id = R.color.AzulMarino),
                fontFamily = FontFamily.Serif)
        })

        // Observaciones
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = obser,
            onValueChange = {
                obser = it
            },
            label = {
                Text(
                    text = "Observaciones",
                    color = colorResource(id = R.color.AzulMarino),
                    fontFamily = FontFamily.Serif
                )
            }
        )

        // Botón Guardar
        Spacer(modifier = Modifier.height(16.dp))
        Button(modifier = Modifier
            .width(200.dp)
            .height(50.dp),
            onClick = {
                if (validateFields()) {
                    guardarExamen()
                } else {
                    // Mostrar mensaje de error o resaltar campos obligatorios
                    lineaODError = lineaOD.isEmpty()
                    lineaOIError = lineaOI.isEmpty()
                    lineaAOODError = lineaAOOD.isEmpty()
                    lineaAOOIError = lineaAOOI.isEmpty()
                    esferaODError = esferaOD.isEmpty()
                    esferaOIError = esferaOI.isEmpty()
                }
                navigationController.navigate("Menu")
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xff1C2D66)
            ),
            shape = CutCornerShape(8.dp)
        ) {
            Text(
                text = "Guardar",
                color = Color.White
            )
        }
    }
}

@Composable
fun ListarExamenes(navigationController: NavHostController) {
    val examenVM: ExamenViewModel = viewModel()
    val examenes by examenVM.examenes.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        examenVM.fetchExamenes()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Consulta Exámen",
                            color = colorResource(id = R.color.AzulMarino),
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Normal
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navigationController.navigate("Menu")
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Regresar",
                            tint = colorResource(id = R.color.AzulMarino)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xff64BDCD)
                )
            )
        },
        content = { padding ->
            LazyColumn(
                contentPadding = padding,
                modifier = Modifier.fillMaxSize()
            ) {
                items(examenes) { examen ->
                    TarjetaExamen(examen = examen)
                }
            }
        }
    )
}


@Composable
fun TarjetaExamen(examen: ExamenModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = CutCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Detalles del paciente

            Text(
                text = "Nombre: ${examen.nombre} ${examen.apellidop} ${examen.apellidom}",
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.AzulMarino),
                fontFamily = FontFamily.Serif
            )
            Text(
                text = "Fecha: ${examen.dateFormat}",
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.AzulMarino),
                fontFamily = FontFamily.Serif
            )
            Text(
                text = "Edad: ${examen.edad}",
                color = colorResource(id = R.color.AzulMarino),
                fontFamily = FontFamily.Serif
            )

            // Detalles del examen visual
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Linea OD: ${examen.lineaOD}",
                color = colorResource(id = R.color.AzulMarino),
                fontFamily = FontFamily.Serif
            )
            Text(
                text = "Linea OI: ${examen.lineaOI}",
                color = colorResource(id = R.color.AzulMarino),
                fontFamily = FontFamily.Serif
            )
            Text(
                text = "Linea AOOD: ${examen.lineaAOOD}",
                color = colorResource(id = R.color.AzulMarino),
                fontFamily = FontFamily.Serif
            )
            Text(
                text = "Linea AOOI: ${examen.lineaAOOI}",
                color = colorResource(id = R.color.AzulMarino),
                fontFamily = FontFamily.Serif
            )
            Text(
                text = "Esfera OD: ${examen.esferaOD}",
                color = colorResource(id = R.color.AzulMarino),
                fontFamily = FontFamily.Serif
            )
            Text(
                text = "Esfera OI: ${examen.esferaOI}",
                color = colorResource(id = R.color.AzulMarino),
                fontFamily = FontFamily.Serif
            )
            Text(
                text = "Cilindro OD: ${examen.cilindroOD}",
                color = colorResource(id = R.color.AzulMarino),
                fontFamily = FontFamily.Serif
            )
            Text(
                text = "Cilindro OI: ${examen.cilindroOI}",
                color = colorResource(id = R.color.AzulMarino),
                fontFamily = FontFamily.Serif
            )
            Text(
                text = "Presbicia OD: ${examen.presbiciaOD}",
                color = colorResource(id = R.color.AzulMarino),
                fontFamily = FontFamily.Serif
            )
            Text(
                text = "Presbicia OI: ${examen.presbiciaOI}",
                color = colorResource(id = R.color.AzulMarino),
                fontFamily = FontFamily.Serif
            )
            Text(
                text = "Eje OD: ${examen.ejeOD}",
                color = colorResource(id = R.color.AzulMarino),
                fontFamily = FontFamily.Serif
            )
            Text(
                text = "Eje OI: ${examen.ejeOI}",
                color = colorResource(id = R.color.AzulMarino),
                fontFamily = FontFamily.Serif
            )
            Text(
                text = "Observaciones: ${examen.observaciones}",
                color = colorResource(id = R.color.AzulMarino),
                fontFamily = FontFamily.Serif
            )
        }
    }
}
