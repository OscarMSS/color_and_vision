@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.colorsandvision

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.colorsandvision.model.LenteModel
import com.example.colorsandvision.viewModels.LenteViewModel
import com.google.firebase.Firebase
import com.google.firebase.storage.storage


@Composable
fun LenteCard(lente: LenteModel, lenteViewModel: LenteViewModel = viewModel()) {
    var showDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(3.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray),
        shape = CutCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = rememberAsyncImagePainter(lente.imagen),
                contentDescription = null,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Text(
                text = "Modelo: ${lente.modelo}",
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.AzulMarino)
            )
            Text(text = "Marca: ${lente.marca}", color = colorResource(id = R.color.AzulMarino))
            Text(text = "Color: ${lente.color}", color = colorResource(id = R.color.AzulMarino))
            Text(text = "Material: ${lente.material}", color = colorResource(id = R.color.AzulMarino))
            Text(text = "Precio: ${lente.precio}", color = colorResource(id = R.color.AzulMarino))

            Spacer(modifier = Modifier.height(8.dp))

            // Botón eliminar alineado a la derecha
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(
                    onClick = { showDialog = true },
                    modifier = Modifier.size(48.dp) // Tamaño del IconButton
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Eliminar",
                        tint = colorResource(id = R.color.Rojo)
                    )
                }
            }

            // Dialogo de confirmación para eliminar
            if (showDialog) {
                ConfirmationDialog(
                    showDialog = showDialog,
                    onConfirm = { lenteViewModel.eliminarLente(lente.lenteid) },
                    onDismiss = { showDialog = false }
                )
            }
        }
    }
}


@Composable
fun Catalogo(navigationController: NavHostController, lenteViewModel: LenteViewModel = viewModel()) {
    val navegation = navigationController
    val lentes by lenteViewModel.lentes.observeAsState(emptyList())
    var showMenu by remember { mutableStateOf(false) }
    FondoExamen()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Catálogo",
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
                actions = {
                        IconButton(onClick = { showMenu = !showMenu }) {
                            Icon(
                                imageVector = Icons.Filled.MoreVert,
                                contentDescription = "Boton para agregar",
                                tint = colorResource(id = R.color.AzulMarino)
                            )
                        }
                        DropdownMenu(
                            expanded = showMenu,
                            onDismissRequest = { showMenu = false },
                            modifier = Modifier.width(150.dp)
                        ) {
                            DropdownMenuItem(
                                text = { Text(text = "Añadir",
                                        color = colorResource(id = R.color.AzulMarino))},
                                onClick = { navegation.navigate("selectAndUpload") }
                            )

                        }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xff64BDCD)) // Color de fondo del TopAppBar
            )
        },
        content = { padding ->
            LazyColumn(
                contentPadding = padding,
                modifier = Modifier.fillMaxSize()
            ) {
                items(lentes) { lente ->
                    LenteCard(lente = lente, lenteViewModel = lenteViewModel)                }
            }
        }
    )
}

@Composable
fun SelectImageAndUploadScreen(lenteViewModel: LenteViewModel = viewModel()) {
    FondoExamen()
    //val navegation = navigationController
    val context = LocalContext.current
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var modelo by remember { mutableStateOf(TextFieldValue("")) }
    var marca by remember { mutableStateOf(TextFieldValue("")) }
    var color by remember { mutableStateOf(TextFieldValue("")) }
    var material by remember { mutableStateOf(TextFieldValue("")) }
    var precio by remember { mutableStateOf(TextFieldValue("")) }
    val scroll = rememberScrollState(0) //Estado scroll

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        imageUri = uri
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scroll)   //Habilitar el scroll verticalmente
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(onClick = { launcher.launch("image/*") },
            modifier = Modifier.size(200.dp) ) {
            Image(
                painter = painterResource(id = R.drawable.nuevo), // Recurso de imagen
                contentDescription = "Seleccionar Imagen",
                modifier = Modifier
                    //.padding(horizontal = 12.dp)
                    .size(2000.dp) // Tamaño personalizado para la imagen// Ajuste del padding
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        imageUri?.let {
            Image(painter = rememberAsyncImagePainter(it), contentDescription = null, modifier = Modifier.size(300.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = modelo,
            onValueChange = { modelo = it },
            label = { Text("Modelo",
                color = colorResource(id = R.color.AzulMarino),
                fontFamily = FontFamily.Serif) }
        )
        Text(text = "Campo Obligatorio",
            color = colorResource(id = R.color.Rojo),
            fontFamily = FontFamily.Serif)

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = marca,
            onValueChange = { marca = it },
            label = { Text("Marca",
                color = colorResource(id = R.color.AzulMarino),
                fontFamily = FontFamily.Serif) }
        )
        Text(text = "Campo Obligatorio",
            color = colorResource(id = R.color.Rojo),
            fontFamily = FontFamily.Serif)

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = color,
            onValueChange = { color = it },
            label = { Text("Color",
                color = colorResource(id = R.color.AzulMarino),
                fontFamily = FontFamily.Serif) }
        )
        Text(text = "Campo Obligatorio",
            color = colorResource(id = R.color.Rojo),
            fontFamily = FontFamily.Serif)

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = material,
            onValueChange = { material = it },
            label = { Text("Material",
                color = colorResource(id = R.color.AzulMarino),
                fontFamily = FontFamily.Serif) }
        )
        Text(text = "Campo Obligatorio",
            color = colorResource(id = R.color.Rojo),
            fontFamily = FontFamily.Serif)

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = precio,
            onValueChange = { precio = it },
            label = { Text("Precio",
                color = colorResource(id = R.color.AzulMarino),
                fontFamily = FontFamily.Serif) }
        )
        Text(text = "Campo Obligatorio",
            color = colorResource(id = R.color.Rojo),
            fontFamily = FontFamily.Serif)

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            imageUri?.let { uri ->
                val storageRef = Firebase.storage.reference.child("images/${System.currentTimeMillis()}.jpg")
                val uploadTask = storageRef.putFile(uri)

                uploadTask.addOnSuccessListener {
                    storageRef.downloadUrl.addOnSuccessListener { downloadUri ->
                        val lente = LenteModel(
                            lenteid = "",
                            imagen = downloadUri.toString(),
                            modelo = modelo.text,
                            marca = marca.text,
                            color = color.text,
                            material = material.text,
                            precio = precio.text
                        )
                        lenteViewModel.guardarLente(lente)
                        Log.d("Firebase", "Imagen subida y datos guardados correctamente")
                    }
                }.addOnFailureListener {
                    Log.e("Firebase", "Error al subir la imagen", it)
                }
            }
            //navegation.navigate("catalogo")
        },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xff1C2D66)),
            shape = CutCornerShape(8.dp)
        ){
            Text(text = "Subir y Guardar",
                color = colorResource(id = R.color.white),
                fontFamily = FontFamily.Serif)
        }
    }
}

@Composable
fun ConfirmationDialog(
    showDialog: Boolean,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            title = {
                Text("Eliminar Lente",
                    color = colorResource(id = R.color.AzulMarino),
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif)
            },
            text = {
                Text("¿Está seguro que desea eliminar este lente?",
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
                    Text("Eliminar",
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
