@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.colorsandvision

import android.icu.util.Calendar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.colorsandvision.model.VentaModel
import com.example.colorsandvision.viewModels.VentaViewModel
import com.google.firebase.firestore.FirebaseFirestore
import java.text.DateFormat
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
    var apellidop by remember { mutableStateOf("") }
    var apellidom by remember { mutableStateOf("") }
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

    // Expresiones regulares para las validaciones
    val numberRegex = Regex("^-?\\d{0,5}(\\.\\d{0,2})?$")
    val phoneNumberRegex = Regex("^\\d+$")

    // Calcular el precio total
    val total = try {
        precioAdic.toDouble() + precioLente.toDouble() + (200 * serie.toDouble())
    } catch (e: NumberFormatException) {
        0.0
    }

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
                }
            }
            .addOnFailureListener { exception ->
                // Handle the error
            }
    }

    fun guardarVenta() {
        val venta = VentaModel(
            ventaId = UUID.randomUUID().toString(), // Genera un ID único para la venta
            dateFormat = dateFormat,
            nombre = nombre,
            apellidop = apellidop,
            apellidom = apellidom,
            accesorio = accerorio,
            modelo = modelo,
            serie = serie,
            tratamiento = tratamiento,
            precioLente = precioLente.toDouble(),
            material = material,
            precioAdic = precioAdic.toDouble(),
            fechaentrega = fechaentrega,
            total = total.toDouble()
        )
        ventaVM.addVenta(venta)
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
                text = "Campo obligatorio \n En caso de no haber un precio de lente ingrese 0",
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
                text = "Campo obligatorio \n En caso de no haber un precio adicional ingrese 0",
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
                text = "Debe estar en formato dd/mm/yyyy",
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
                    guardarVenta()
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

@Composable
fun ListarVenta(navigationController: NavHostController) {
    val ventaVM: VentaViewModel = viewModel()
    val ventas by ventaVM.ventas.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        ventaVM.fetchVentas()
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
                            text = "Consulta Ventas",
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
                items(ventas) { venta ->
                    TarjetaVenta(venta = venta, ventaVM = ventaVM)
                }
            }
        }
    )
}

@Composable
fun TarjetaVenta(venta: VentaModel, ventaVM: VentaViewModel) {
    var showDialog by remember { mutableStateOf(false) }
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
            if (venta.entregado) {
                Text(
                    text = "Entregado",
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.Verde),
                    fontFamily = FontFamily.Serif
                )
            }
            Text(
                text = "Fecha de Entrega: ${venta.fechaentrega}",
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.AzulMarino),
                fontFamily = FontFamily.Serif
            )
            Text(
                text = "Nombre: ${venta.nombre} ${venta.apellidop} ${venta.apellidom}",
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.AzulMarino),
                fontFamily = FontFamily.Serif
            )
            Text(
                text = "Fecha: ${venta.dateFormat}",
                color = colorResource(id = R.color.AzulMarino),
                fontFamily = FontFamily.Serif
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Modelo: ${venta.modelo}",
                color = colorResource(id = R.color.AzulMarino),
                fontFamily = FontFamily.Serif
            )
            Text(
                text = "Serie: ${venta.serie}",
                color = colorResource(id = R.color.AzulMarino),
                fontFamily = FontFamily.Serif
            )
            Text(
                text = "Material: ${venta.material}",
                color = colorResource(id = R.color.AzulMarino),
                fontFamily = FontFamily.Serif
            )
            Text(
                text = "Accesorio: ${venta.accesorio}",
                color = colorResource(id = R.color.AzulMarino),
                fontFamily = FontFamily.Serif
            )
            Text(
                text = "Tratamiento: ${venta.tratamiento}",
                color = colorResource(id = R.color.AzulMarino),
                fontFamily = FontFamily.Serif
            )
            Text(
                text = "Precio del lente: ${venta.precioLente}",
                color = colorResource(id = R.color.AzulMarino),
                fontFamily = FontFamily.Serif
            )
            Text(
                text = "Precio Adicional: ${venta.precioAdic}",
                color = colorResource(id = R.color.AzulMarino),
                fontFamily = FontFamily.Serif
            )
            Text(
                text = "Total: ${venta.total}",
                color = colorResource(id = R.color.AzulMarino),
                fontFamily = FontFamily.Serif
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(
                    onClick = {
                        showDialog = true
                    },
                    modifier = Modifier.size(48.dp) // Tamaño del IconButton
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Entregado",
                        tint = colorResource(id = R.color.Verde)
                    )
                }
            }
        }
    }
    ConfirmationVenta(
        showDialog = showDialog,
        onConfirm = {
            ventaVM.markAsDelivered(venta.ventaId)
            showDialog = false
        },
        onDismiss = {
            showDialog = false
        }
    )
}

@Composable
fun ConfirmationVenta(
    showDialog: Boolean,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            title = {
                Text("Entrega",
                    color = colorResource(id = R.color.AzulMarino),
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif)
            },
            text = {
                Text("¿Está seguro que fue entregado?",
                    color = colorResource(id = R.color.AzulMarino),
                    fontFamily = FontFamily.Serif)
            },
            confirmButton = {
                Button(
                    onClick = {
                        onConfirm()
                        onDismiss()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xff1C2D66)
                    ),
                    shape = CutCornerShape(8.dp)
                ) {
                    Text("Confirmar",
                        color = colorResource(id = R.color.white),
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif)
                }
            },
            dismissButton = {
                Button(
                    onClick = { onDismiss() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xff64BDCD)
                    ),
                    shape = CutCornerShape(8.dp)
                ) {
                    Text("Cancelar",
                        color = colorResource(id = R.color.AzulMarino),
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif)
                }
            }
        )
    }
}




