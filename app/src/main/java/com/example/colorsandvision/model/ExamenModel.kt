package com.example.colorsandvision.model

import com.google.firebase.database.Exclude

data class ExamenModel(
    val ExamenId: String = "",
    val lineaOD: String = "",
    val lineaOI: String = "",
    val lineaAOOI: String = "",
    val lineaAOOD: String = "",
    val esferaOD: String = "",
    val esferaOI: String = "",
    val cilindroOD: String ="",
    val cilindroOI: String="",
    val presbiciaOD: String="",
    val presbiciaOI: String="",
    val observaciones: String="",
    val ejeOD : String="",
    val ejeOI : String="",
    val nombre : String="",
    val apellidop : String="",
    val apellidom : String="",
    val edad : String="",
    val dateFormat : String=""
){
    // Añadir anotación @Exclude para evitar serialización circular al convertir a mapa
    @Exclude
    fun toMap(): MutableMap<String, Any> {
        return mutableMapOf(
            "ExamenId" to this.ExamenId,
            "lineaOD" to this.lineaOD,
            "lineaOI" to this.lineaOI,
            "lineaAOOI" to this.lineaAOOI,
            "lineaAOOD" to this.lineaAOOD,
            "esferaOD" to this.esferaOD,
            "esferaOI" to this.esferaOI,
            "cilindroOD" to this.cilindroOD,
            "cilindroOI" to this.cilindroOI,
            "presbiciaOD" to this.presbiciaOD,
            "presbiciaOI" to this.presbiciaOI,
            "observaciones" to this.observaciones,
            "ejeOD" to this.ejeOD,
            "ejeOI" to this.ejeOI,
            "nombre" to this.nombre,
            "apellidop" to this.apellidop,
            "apellidom" to this.apellidom,
            "edad" to this.edad,
            "fecha" to this.dateFormat

        )
    }

}