package com.example.colorsandvision.model

import com.google.firebase.database.Exclude

data class LenteModel(
    val lenteid: String = "",
    val imagen: String = "",
    val modelo: String = "",
    val marca: String = "",
    val color: String = "",
    val material: String = "",
    val precio: String = ""
) {
    @Exclude
    fun toMap(): MutableMap<String, Any> {
        return mutableMapOf(
            "lenteid" to this.lenteid,
            "imagen" to this.imagen,
            "modelo" to this.modelo,
            "marca" to this.marca,
            "color" to this.color,
            "material" to this.material,
            "precio" to this.precio
        )
    }
}