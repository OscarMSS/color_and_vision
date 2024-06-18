package com.example.colorsandvision.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.colorsandvision.model.PacienteModel
import com.google.firebase.firestore.FirebaseFirestore


class PacienteViewModel : ViewModel() {

    private val firestore = FirebaseFirestore.getInstance()

    val _addPacienteResult = MutableLiveData<Result<String>>()
    val addPacienteResult: LiveData<Result<String>> get() = _addPacienteResult
    private val _searchPacienteResult = MutableLiveData<Result<PacienteModel>>()
    val searchPacienteResult: LiveData<Result<PacienteModel>> get() = _searchPacienteResult
    fun addPaciente(paciente: PacienteModel) {
        val pacienteMap = paciente.toMap()
        firestore.collection("paciente")
            .add(pacienteMap)
            .addOnSuccessListener {
                _addPacienteResult.postValue(Result.success("Paciente agregado exitosamente"))
            }
            .addOnFailureListener { exception ->
                _addPacienteResult.postValue(Result.failure(exception))
            }
    }

}