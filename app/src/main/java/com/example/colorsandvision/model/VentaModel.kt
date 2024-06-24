package com.example.colorsandvision.model

import com.google.firebase.database.Exclude

data class VentaModel(
    val ventaId: String = "",
    val fechaentrega: String = "",
    val dateFormat: String = "",
    val nombre: String = "",
    val apellidop: String = "",
    val apellidom: String = "",
    val modelo: String = "",
    val serie: String = "",
    val material: String = "",
    val accesorio: String = "",
    val tratamiento: String = "",
    val precioLente: Double = 0.0,
    val precioAdic: Double = 0.0,
    val total: Double = 0.0,
    val entregado: Boolean = false // Nueva propiedad
) {
    fun toMap(): Map<String, Any> {
        return mapOf(
            //"ventaId" to ventaId,
            "fechaentrega" to fechaentrega,
            "dateFormat" to dateFormat,
            "nombre" to nombre,
            "apellidop" to apellidop,
            "apellidom" to apellidom,
            "modelo" to modelo,
            "serie" to serie,
            "material" to material,
            "accesorio" to accesorio,
            "tratamiento" to tratamiento,
            "precioLente" to precioLente,
            "precioAdic" to precioAdic,
            "total" to total,
            "entregado" to entregado
        )
    }
}

