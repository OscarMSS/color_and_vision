@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.colorsandvision

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun Catalogo(navigationController: NavHostController){
    FondoExamen()
    val navegation = navigationController
    val scroll = rememberScrollState(0) //Estado scroll


    Column (
        modifier = Modifier.fillMaxSize()
            .verticalScroll(scroll)   //Habilitar el scroll verticalmente
            .navigationBarsPadding(), // Habilitar padding para la barra de navegación,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente1), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: 1017 \n Marca: susan  \n Color: morado  \n Material: metalico \n" +
                            " Precio: 250",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //Lente 2
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente2), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: D51 \n Marca: hagnus \n Color: rosa \n" +
                            " Material: acetato  \n Precio: 250",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //Lente 3
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente3), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: 135 \n Marca: IMPack \n Color: azul \n Material: metalico \n Precio: 250",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //lente 4
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente4), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: 5016K \n Marca: Hagnus \n Color: azul  \n" +
                            " Material: acetato  \n Precio: 250",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //Lente 5
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente5), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: STEEL \n Marca: ELLIS \n Color: rosa \n" +
                            " Material: metalico \n Precio: 250",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //Lente 6
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente6), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: D47 \n Marca: hagnus \n Color: lila \n" +
                            " Material: acetato  \n Precio: 250",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //Lente 7
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente7), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: D51 \n Marca: hagnus \n Color: Negro \n" +
                            " Material: acetato  \n Precio: 250",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //Lente 8
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente8), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: D50 \n Marca: Andre Optic \n Color: morado  \n" +
                            " Material: metalico \n Precio: 250",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //lente 9
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente9), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: TR006 \n Marca: Karolyna  \n Color: azul metalico  \n" +
                            " Material: acetato  \n Precio: 400",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //Lente 10
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente10), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: 140 \n Marca: Curty  \n Color: rojo  \n" +
                            " Material: acetato  \n Precio: 400",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //Lentes 11
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente11), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: 602 \n Marca: Karolyna   \n Color: azul marino   \n Material: acetato  \n" +
                            " Precio: 400",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //Lente 12
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente12), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: 142 \n Marca: Itis? \n Color: rosa \n" +
                            " Material: acetato  \n Precio: 400",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //Lente 13
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente13), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: 143 \n Marca: Onola \n Color: azul marino  \n Material: acetato  \n Precio: 400",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //lente 14
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lentes14), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: 2016R \n Marca: elizabeth  \n Color: vino   \n" +
                            " Material: acetato  \n Precio: 400",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //Lente 15
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente15), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: 140 \n Marca: Itis? \n Color: cafe \n" +
                            " Material: acetato  \n Precio: 400",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //Lente 16
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente16), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: D55 \n Marca: hagnus \n Color: rojo  \n" +
                            " Material: acetato  \n Precio: 400",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //Lente 17
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente17), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: 147 \n Marca: elizabeth  \n Color: negro-rosa \n" +
                            " Material: acetato  \n Precio: 400",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //Lente 18
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente18), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: D54 \n Marca: hagnus \n Color: gris  \n" +
                            " Material: acetato  \n Precio: 400",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //lente 19
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente19), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: 140 \n Marca: Itis?  \n Color: negro-morado  \n" +
                            " Material: acetato  \n Precio: 400",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //Lente 20
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente20), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: 143 \n Marca: Onola  \n Color: cafe  \n" +
                            " Material: acetato  \n Precio: 400",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //Lente 21
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lentes21), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: D56 \n Marca: elizabeth   \n Color: rojo   \n Material: acetato  \n" +
                            " Precio: 400",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //Lente 22
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente22), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: 142 \n Marca: Itis? \n Color: palo de rosa  \n" +
                            " Material: acetato  \n Precio: 400",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //Lente 23
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente9), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: 138 \n Marca: Itis? \n Color: azul- gris \n Material: acetato  \n Precio: 400",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //lente 24
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente24), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: 140 \n Marca: Curty  \n Color: negor-azul   \n" +
                            " Material: acetato  \n Precio: 650",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //Lente 25
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente25), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: 150 \n Marca: Onola \n Color: beige  \n" +
                            " Material: acetato  \n Precio: 650",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //Lente 26
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente26), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: 143 \n Marca: Onola \n Color: transparente  \n" +
                            " Material: acetato  \n Precio: 650",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //Lente 27
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente27), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: 400 \n Marca: color  \n Color: transparente  \n" +
                            " Material: acetato-metalico  \n Precio: 650",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //Lente 28
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente28), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: 401 \n Marca: color \n Color: negro   \n" +
                            " Material: acetato-metalico \n Precio: 650",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //lente 29
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente29), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: 402 \n Marca: color   \n Color: negro  \n" +
                            " Material: acetato-metalico  \n Precio: 650",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //Lente 30
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente30), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: 150 \n Marca: color   \n Color: transparente   \n" +
                            " Material: acetato  \n Precio: 650",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //Lentes 31
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente31), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: 403 \n Marca: color    \n Color:transparente   \n Material: acetato  \n" +
                            " Precio: 650",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //Lente 32
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente32), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: 150 \n Marca: color \n Color: negro  \n" +
                            " Material: acetato  \n Precio: 650",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //Lente 33
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente33), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: 145 \n Marca: mosson \n Color: azul-dorado   \n Material: acetato-metalico  \n Precio: 650",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //lente 34
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente34), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: 405 \n Marca: color   \n Color: negro-dorado  \n" +
                            " Material: metalico   \n Precio: 650",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //Lente 35
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente35), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: 406 \n Marca: color \n Color: negro-dorado \n" +
                            " Material: metalico   \n Precio: 650",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //Lente 36
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente36), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: 407 \n Marca: color  \n Color: verde-dorado  \n" +
                            " Material: metalico   \n Precio: 650",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //Lente 37
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente37), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: 408 \n Marca: elizabeth  \n Color:fiusha-dorado \n" +
                            " Material: metalico  \n Precio: 650",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //Lente 38
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente38), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: 409 \n Marca: color \n Color: negro  \n" +
                            " Material: ultralijero  \n Precio: 650",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //lente 39
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente39), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: 145 \n Marca: olive  \n Color: negro  \n" +
                            " Material: acetato  \n Precio: 1000",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //Lente 40
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente40), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: D57 \n Marca: olive  \n Color: negro-azul  \n" +
                            " Material: acetato  \n Precio: 1000",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //Lente 41
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente41), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: 145 \n Marca: miss-moe  \n Color: morado  \n Material: acetato \n" +
                            " Precio: 1000",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //Lente 42
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente42), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: 140 \n Marca: marina \n Color: palo de rosa  \n" +
                            " Material: metalico  \n Precio: 1000",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //Lente 43
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente43), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: 145 \n Marca: olive \n Color: negro-naranja \n Material: acetato  \n Precio: 1000",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //lente 44
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente44), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: D49 \n Marca: olive \n Color: azul  \n" +
                            " Material: acetato  \n Precio: 1000",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //Lente 45
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente45), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: 142 \n Marca: olive \n Color: morado  \n" +
                            " Material: acetato \n Precio: 1000",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //Lente 46
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente46), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: 140 \n Marca: olive \n Color: negro \n" +
                            " Material: acetato  \n Precio: 1000",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //Lente 47
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente47), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: 142 \n Marca: olive \n Color: azul  \n" +
                            " Material: ultralijero  \n Precio: 1000",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //Lente 48
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente48), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: 145 \n Marca: olive \n Color: gris  \n" +
                            " Material: metalico \n Precio: 1000",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //lente 49
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente49), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: 145 \n Marca: olive  \n Color: negro \n" +
                            " Material: acetato-metalico  \n Precio: 1000",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //Lente 50
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente50), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: 138 \n Marca: pinky  \n Color: cafe  \n" +
                            " Material: metalico   \n Precio: 1000",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //Lentes 51
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente51), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: 145 \n Marca: olive   \n Color: negro   \n Material: acetato  \n" +
                            " Precio: 1000",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //Lente 52
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente51), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: 142 \n Marca: emilia anne \n Color: dorado \n" +
                            " Material: metalico   \n Precio: 1000",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //Lente 53
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente53), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: D56 \n Marca: botox \n Color: negro  \n Material: metalico  \n Precio: 250",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //lente 54
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente54), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: 140 \n Marca: soraya  \n Color: negro   \n" +
                            " Material: metalico  \n Precio: 250",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //Lente 55
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente55), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: 145 \n Marca: soraya \n Color: negro \n" +
                            " Material: metalico  \n Precio: 250",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //Lente 56
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente56), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: 140 \n Marca: botox \n Color: rojo  \n" +
                            " Material: metalico   \n Precio: 250",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //Lente 57
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente57), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: 140 \n Marca: susan   \n Color: dorado \n" +
                            " Material: metalico   \n Precio: 250",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //Lente 58
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente58), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: 145 \n Marca: soraya \n Color: morado   \n" +
                            " Material: metalico   \n Precio: 250",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //lente 59
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente59), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: 140 \n Marca: susan  \n Color: plata  \n" +
                            " Material: metalico   \n Precio: 250",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //Lente 60
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente60), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: 137 \n Marca: soraya  \n Color: negro  \n" +
                            " Material: metalico   \n Precio: 250",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //Lente 61
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente61), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: 140 \n Marca: susan    \n Color: negro   \n Material: metalico  \n" +
                            " Precio: 250",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //Lente 62
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente62), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: 138 \n Marca: soraya \n Color: negro  \n" +
                            " Material: metalico   \n Precio: 250",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //Lente 63
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente63), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: 140 \n Marca: Curty  \n Color: negro \n Material: metalico  \n Precio: 250",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //lente 64
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente64), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: 140 \n Marca: Curty  \n Color: negro   \n" +
                            " Material: metalico   \n Precio: 250",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //Lente 65
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente65), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: D65 \n Marca: botox \n Color: negro  \n" +
                            " Material: metalico  \n Precio: 250",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //Lente 66
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente66), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: D56 \n Marca: staineless \n Color: cafe  \n" +
                            " Material: metalico  \n Precio: 250",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
        //Lente 67
        Spacer(modifier = Modifier.height(30.dp))
        Card (modifier = Modifier
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(3.dp),//Elevacion de la card
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = CutCornerShape(8.dp)
        ){
            Column (){
                Image(painter = painterResource(id = R.drawable.lente67), contentDescription = null )
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = " Modelo: 138 \n Marca: caffsen  \n Color: plata  \n" +
                            " Material: metalico  \n Precio: 250",
                        color = colorResource(id = R.color.AzulMarino),
                        fontFamily = FontFamily.Serif)

                }
            }
        }
    }
    TopAppBar(
        title = {
            Text(text = "Catalogo",
                color = colorResource(id = R.color.AzulMarino),
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Normal
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                navegation.navigate("Menu")
            }) {
                Icon(imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Regresar",
                    tint = colorResource(id = R.color.AzulMarino)
                )
            }
        }
    )
}

