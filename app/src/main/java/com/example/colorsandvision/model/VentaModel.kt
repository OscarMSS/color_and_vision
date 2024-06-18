package com.example.colorsandvision.model

import com.google.firebase.database.Exclude

data class VentaModel (

        val ventaId: String,
        val modelo: String,
        val serie: String,
        val material: String,
        val accerorio: String,
        val tratamiento: String,
        val precioLente: String,
        val precioAdic: String,
        val fechaentrega: String,
        val total: String
    ){
        // Añadir anotación @Exclude para evitar serialización circular al convertir a mapa
        @Exclude
        fun toMap(): MutableMap<String, Any> {
            return mutableMapOf(
                "ventaId" to this.ventaId,
                "modelo" to this.modelo,
                "serie" to this.serie,
                "material" to this.material,
                "accerorio" to this.accerorio,
                "tratamiento" to this.tratamiento,
                "precioLente" to this.precioLente,
                "precioAdic" to this.precioAdic,
                "fechaentrega" to this.fechaentrega,
                "total" to this.total
            )
        }
}
