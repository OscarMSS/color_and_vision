package com.example.colorsandvision.model

import com.google.firebase.database.Exclude

data class PacienteModel(
    val pacienteId: String,
    val nombre: String,
    val apellidop: String,
    val apellidom: String,
    val celular: String,
    val edad: String,
    val enfermedades: String,
    val ocupacion: String,
    val observaciones: String
){
    // Añadir anotación @Exclude para evitar serialización circular al convertir a mapa
    @Exclude
    fun toMap(): MutableMap<String, Any> {
        return mutableMapOf(
            "pacienteId" to this.pacienteId,
            "nombre" to this.nombre,
            "apellidop" to this.apellidop,
            "apellidom" to this.apellidom,
            "celular" to this.celular,
            "edad" to this.edad,
            "ocupacion" to this.ocupacion,
            "observaciones" to this.observaciones
        )
    }
}