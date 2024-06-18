package com.example.colorsandvision.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.colorsandvision.model.ExamenModel
import com.example.colorsandvision.model.PacienteModel
import com.example.colorsandvision.model.VentaModel
import com.google.firebase.firestore.FirebaseFirestore

class ExamenViewModel:ViewModel() {
    private val firestore = FirebaseFirestore.getInstance()
    val _addExamenResult = MutableLiveData<Result<String>>()
    private val _searchExamenResult = MutableLiveData<Result<PacienteModel>>()
    fun addExamen(examenModel: ExamenModel) {
        val examenvMap = examenModel.toMap()
        firestore.collection("examenv")
            .add(examenvMap)
            .addOnSuccessListener {
                _addExamenResult.postValue(Result.success("Examen de la vista agregado exitosamente"))
            }
            .addOnFailureListener { exception ->
                _addExamenResult.postValue(Result.failure(exception))
            }
    }
}