package com.example.colorsandvision.viewModels

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.colorsandvision.model.LenteModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class LenteViewModel : ViewModel() {

    private val _lentes = MutableLiveData<List<LenteModel>>()
    val lentes: LiveData<List<LenteModel>> get() = _lentes

    init {
        cargarLentes()
    }

    private fun cargarLentes() {
        val db = Firebase.firestore
        db.collection("catalogo")
            .get()
            .addOnSuccessListener { result ->
                val listaLentes = mutableListOf<LenteModel>()
                for (document in result) {
                    val lente = document.toObject(LenteModel::class.java)
                    listaLentes.add(lente)
                }
                _lentes.value = listaLentes
            }
            .addOnFailureListener { exception ->
                Log.w("LenteViewModel", "Error getting documents: ", exception)
            }
    }
    fun guardarLente(lente: LenteModel) {
        val db = Firebase.firestore
        val lentesRef = db.collection("catalogo")

        lentesRef.add(lente.toMap())
            .addOnSuccessListener { documentReference ->
                Log.d("LenteViewModel", "Documento guardado con ID: ${documentReference.id}")
                // Aquí podrías mostrar un mensaje o realizar alguna acción adicional
            }
            .addOnFailureListener { e ->
                Log.w("LenteViewModel", "Error al guardar documento", e)
                // Manejo de errores
            }
    }
}