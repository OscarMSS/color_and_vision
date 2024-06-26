@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.colorsandvision

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.colorsandvision.model.PacienteModel
import com.example.colorsandvision.viewModels.PacienteViewModel
import com.google.firebase.firestore.FirebaseFirestore
import java.util.UUID

@Composable
fun FondoRegistro(){
    val backgroundImage = painterResource(id = R.drawable.fondo3)
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
fun RegistroPaciente(navigationController: NavHostController){
    FondoRegistro()
    val pacienteVM = PacienteViewModel()

    val navegation = navigationController

    var nombre by remember {
        mutableStateOf("")
    }
    var apellidoP by remember {
        mutableStateOf("")
    }
    var apellidoM by remember {
        mutableStateOf("")
    }
    var celular by remember {
        mutableStateOf("")
    }
    var ocupacion by remember {
        mutableStateOf("")
    }
    var enfermedades by remember {
        mutableStateOf("")
    }
    var observaciones by remember {
        mutableStateOf("")
    }
    var edad by remember {
        mutableStateOf("")
    }

    // variables para los campos obligatorios//
    var nombreError by remember { mutableStateOf(false) }
    var apellidoPError by remember { mutableStateOf(false) }
    var apellidoMError by remember { mutableStateOf(false) }
    var celularError by remember { mutableStateOf(false) }
    var edadError by remember { mutableStateOf(false) }

    // expresiones regulares para las validaciones
    val nombreApellidoRegex = Regex("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]*$")
    val celularRegex = Regex("^[0-9]{0,10}$")
    val edadRegex = Regex("^[0-9]{1,3}$")

    //Cuestionario
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 16.dp), // Asegura espacio para que los botones no sean obstruidos
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Spacer(modifier = Modifier.height(60.dp))
            Text(
                text = "Paciente",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                color = colorResource(id = R.color.AzulMarino)
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = nombre,
                onValueChange = {
                    if (nombreApellidoRegex.matches(it)) {
                        nombre = it
                    }
                },
                label = {
                    Text(
                        text = "Nombre",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif
                    )
                }
            )
            if (nombreError) {
                Text(
                    text = "Campo obligatorio",
                    color = Color.Red,
                    fontSize = 12.sp
                )
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = apellidoP,
                onValueChange = {
                    if (nombreApellidoRegex.matches(it)) {
                        apellidoP = it
                    }
                },
                label = {
                    Text(
                        text = "Apellido Paterno",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif
                    )
                }
            )
            if (apellidoPError) {
                Text(
                    text = "Campo obligatorio",
                    color = Color.Red,
                    fontSize = 12.sp
                )
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = apellidoM,
                onValueChange = {
                    if (nombreApellidoRegex.matches(it)) {
                        apellidoM = it
                    }
                },
                label = {
                    Text(
                        text = "Apellido Materno",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif
                    )
                }
            )
            if (apellidoMError) {
                Text(
                    text = "Campo obligatorio",
                    color = Color.Red,
                    fontSize = 12.sp
                )
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = celular,
                onValueChange = {
                    if (it.length <= 10 && celularRegex.matches(it)) {
                        celular = it
                    }
                },
                label = {
                    Text(
                        text = "Celular",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif
                    )
                }
            )
            if (celularError) {
                Text(
                    text = "Campo obligatorio \n Debe contener exactamente 10 dígitos",
                    color = Color.Red,
                    fontSize = 12.sp
                )
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = edad,
                onValueChange = {
                    if (edadRegex.matches(it)) {
                        edad = it
                    }
                },
                label = {
                    Text(
                        text = "Edad",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif
                    )
                }
            )
            if (edadError) {
                Text(
                    text = "Campo obligatorio",
                    color = Color.Red,
                    fontSize = 12.sp
                )
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = ocupacion,
                onValueChange = {
                    if (nombreApellidoRegex.matches(it)) {
                        ocupacion = it
                    }
                },
                label = {
                    Text(
                        text = "Ocupación",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif
                    )
                }
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = enfermedades,
                onValueChange = {
                    if (nombreApellidoRegex.matches(it)) {
                        enfermedades = it
                    }
                },
                label = {
                    Text(
                        text = "Enfermedades",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif
                    )
                }
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = observaciones,
                onValueChange = {
                    if (nombreApellidoRegex.matches(it)) {
                        observaciones = it
                    }
                },
                label = {
                    Text(
                        text = "Observaciones",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif
                    )
                },
                maxLines = 50
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier
                    .width(200.dp)
                    .height(50.dp),
                onClick = {
                    var allFieldsValid = true

                    if (nombre.isBlank()) {
                        nombreError = true
                        allFieldsValid = false
                    }
                    if (apellidoP.isBlank()) {
                        apellidoPError = true
                        allFieldsValid = false
                    }
                    if (apellidoM.isBlank()) {
                        apellidoMError = true
                        allFieldsValid = false
                    }
                    if (celular.isBlank() || celular.length != 10) {
                        celularError = true
                        allFieldsValid = false
                    }
                    if (edad.isBlank()) {
                        edadError = true
                        allFieldsValid = false
                    }

                    if (allFieldsValid) {
                        val paciente = PacienteModel(
                            pacienteId = UUID.randomUUID().toString(),
                            nombre = nombre,
                            apellidop = apellidoP,
                            apellidom = apellidoM,
                            celular = celular,
                            edad = edad,
                            ocupacion = ocupacion,
                            enfermedades = enfermedades,
                            observaciones = observaciones
                        )
                        pacienteVM.addPaciente(paciente)
                        navegation.navigate("Examen")
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xff1C2D66)
                ),
                shape = CutCornerShape(8.dp)
            ) {
                Text(
                    text = "Añadir",
                    color = colorResource(id = R.color.white),
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif
                )
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier
                    .width(200.dp)
                    .height(50.dp),
                onClick = {

                    navegation.navigate("Menu")
                          },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xff64BDCD)
                ),
                shape = CutCornerShape(8.dp)
            ) {
                Text(
                    text = "Cancelar",
                    color = colorResource(id = R.color.AzulMarino),
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif
                )
            }
        }

        // Espaciador adicional para evitar que los botones sean obstruidos por la barra de acciones
        item {
            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}

@Composable
fun PacienteCard(paciente: PacienteModel, onEdit: (PacienteModel) -> Unit) {
    var showEditDialog by remember { mutableStateOf(false) }

    if (showEditDialog) {
        EditPacienteDialog(
            paciente = paciente,
            onDismiss = { showEditDialog = false },
            onSave = { updatedPaciente ->
                onEdit(updatedPaciente)
                showEditDialog = false
            }
        )
    }
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = CutCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "${paciente.nombre} ${paciente.apellidop} ${paciente.apellidom}",
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.AzulMarino)
            )
            Text(text = "Celular: ${paciente.celular}", color = colorResource(id = R.color.AzulMarino))
            Text(text = "Edad: ${paciente.edad}", color = colorResource(id = R.color.AzulMarino))
            Text(text = "Enfermedades: ${paciente.enfermedades}", color = colorResource(id = R.color.AzulMarino))
            Text(text = "Ocupación: ${paciente.ocupacion}", color = colorResource(id = R.color.AzulMarino))
            Text(text = "Observaciones: ${paciente.observaciones}", color = colorResource(id = R.color.AzulMarino))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(
                    onClick = { showEditDialog = true },
                    modifier = Modifier.size(48.dp) // Tamaño del IconButton
                ) {
                    Icon(
                        imageVector = Icons.Default.Create,
                        contentDescription = "Editar",
                        tint = colorResource(id = R.color.Verde)
                    )
                }
            }
        }
    }
}

@Composable
fun PacientesScreen(navigationController: NavHostController, viewModel: PacienteViewModel = viewModel()) {
    val pacientes by viewModel.pacientes.observeAsState(emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Consulta Pacientes",
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
                items(pacientes) { paciente ->
                    PacienteCard(
                        paciente = paciente,
                        onEdit = { updatedPaciente ->
                            viewModel.updatePaciente(updatedPaciente)
                        }
                    )
                }
            }
        }
    )
}


@Composable
fun EditPacienteDialog(
    paciente: PacienteModel,
    onDismiss: () -> Unit,
    onSave: (PacienteModel) -> Unit
) {
    var edad by remember { mutableStateOf(paciente.edad) }
    var enfermedades by remember { mutableStateOf(paciente.enfermedades) }
    var ocupacion by remember { mutableStateOf(paciente.ocupacion) }
    var observaciones by remember { mutableStateOf(paciente.observaciones) }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(text = "Editar Paciente",
            color = colorResource(id = R.color.AzulMarino),
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif) },
        text = {
            Column {
                TextField(
                    value = edad,
                    onValueChange = { edad = it },
                    label = { Text("Edad",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif) }
                )
                Spacer(modifier = Modifier.height(10.dp))
                TextField(
                    value = enfermedades,
                    onValueChange = { enfermedades = it },
                    label = { Text("Enfermedades",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif) }
                )
                Spacer(modifier = Modifier.height(10.dp))
                TextField(
                    value = ocupacion,
                    onValueChange = { ocupacion = it },
                    label = { Text("Ocupación",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif) }
                )
                Spacer(modifier = Modifier.height(10.dp))
                TextField(
                    value = observaciones,
                    onValueChange = { observaciones = it },
                    label = { Text("Observaciones",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif) }
                )
            }
        },
        confirmButton = {
            Button(onClick = {
                val updatedPaciente = paciente.copy(
                    edad = edad,
                    enfermedades = enfermedades,
                    ocupacion = ocupacion,
                    observaciones = observaciones
                )
                onSave(updatedPaciente)
            },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xff1C2D66)
                ),
                shape = CutCornerShape(8.dp)) {
                Text("Guardar",
                    color = colorResource(id = R.color.white),
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif)
            }
        },
        dismissButton = {
            Button(onClick = { onDismiss() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xff64BDCD)
                ),
                shape = CutCornerShape(8.dp)) {
                Text("Cancelar",
                    color = colorResource(id = R.color.AzulMarino),
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif)
            }
        }
    )
}



