package com.example.colorsandvision.model

import com.google.firebase.firestore.Exclude

data class UserModel(
    val userId: String,
    val email: String,
    val username: String,
    val userapellido : String
) {
    // Añadir anotación @Exclude para evitar serialización circular al convertir a mapa
    @Exclude
    fun toMap(): MutableMap<String, Any> {
        return mutableMapOf(
            "userId" to this.userId,
            "email" to this.email,
            "username" to this.username,
            "userapellido" to this.userapellido
        )
    }

}

