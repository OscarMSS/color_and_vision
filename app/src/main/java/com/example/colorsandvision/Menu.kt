package com.example.colorsandvision

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController


@Composable
fun Fondo(){
    val backgroundImage = painterResource(id = R.drawable.fondo2)
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
fun Menu(navigationController: NavHostController){
    val navegation = navigationController
    Fondo()
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        //Regitrar
        Button(modifier = Modifier
            .width(200.dp)
            .height(50.dp),
            onClick = {
                navegation.navigate("Paciente")
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xff64BDCD)),
            shape = CutCornerShape(8.dp)

        ) {
            Text(text = "Registrar",
                fontFamily = FontFamily.Serif,
                color = colorResource(id = R.color.AzulMarino),
                fontWeight = FontWeight.Bold)
        }

        //Examen
        Spacer(modifier = Modifier.height(40.dp))
        Button(modifier = Modifier
            .width(200.dp)
            .height(50.dp),
            onClick = {
                navegation.navigate("Examen")
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xff64BDCD)),
            shape = CutCornerShape(8.dp)

        ) {
            Text(text = "Examén",
                fontFamily = FontFamily.Serif,
                color = colorResource(id = R.color.AzulMarino),
                fontWeight = FontWeight.Bold)
        }

        //Catalogo
        Spacer(modifier = Modifier.height(40.dp))
        Button(modifier = Modifier
            .width(200.dp)
            .height(50.dp),
            onClick = {
                navegation.navigate("Catalogo")
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xff64BDCD)),
            shape = CutCornerShape(8.dp)

        ) {
            Text(text = "Catalogo",
                fontFamily = FontFamily.Serif,
                color = colorResource(id = R.color.AzulMarino),
                fontWeight = FontWeight.Bold)
        }

        //Compra
        Spacer(modifier = Modifier.height(40.dp))
        Button(modifier = Modifier
            .width(200.dp)
            .height(50.dp),
            onClick = {
                navegation.navigate("Venta")
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xff64BDCD)),
            shape = CutCornerShape(8.dp)

        ) {
            Text(text = "Venta",
                fontFamily = FontFamily.Serif,
                color = colorResource(id = R.color.AzulMarino),
                fontWeight = FontWeight.Bold)
        }

    }
}