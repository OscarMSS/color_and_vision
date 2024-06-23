package com.example.colorsandvision.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.colorsandvision.model.ExamenModel
import com.example.colorsandvision.model.PacienteModel
import com.example.colorsandvision.model.VentaModel
import com.google.firebase.firestore.FirebaseFirestore

class ExamenViewModel : ViewModel() {
    private val firestore = FirebaseFirestore.getInstance()
    private val _examenes = MutableLiveData<List<ExamenModel>>()
    val examenes: LiveData<List<ExamenModel>> get() = _examenes

    val _addExamenResult = MutableLiveData<Result<String>>()

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

    fun fetchExamenes() {
        firestore.collection("examenv")
            .get()
            .addOnSuccessListener { result ->
                val examenList = result.documents.map { it.toObject(ExamenModel::class.java)!! }
                _examenes.postValue(examenList)
            }
            .addOnFailureListener { exception ->
                _examenes.postValue(emptyList())
            }
    }
}
