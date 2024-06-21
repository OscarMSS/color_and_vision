package com.example.colorsandvision.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.colorsandvision.model.PacienteModel
import com.google.firebase.firestore.FirebaseFirestore


class PacienteViewModel : ViewModel() {

    private val firestore = FirebaseFirestore.getInstance()

    private val _pacientes = MutableLiveData<List<PacienteModel>>()
    val pacientes: LiveData<List<PacienteModel>> get() = _pacientes

    val _addPacienteResult = MutableLiveData<Result<String>>()
    private val _searchPacienteResult = MutableLiveData<Result<PacienteModel>>()

    init {
        fetchPacientes()
    }

    fun addPaciente(paciente: PacienteModel) {
        val pacienteMap = paciente.toMap()
        firestore.collection("paciente")
            .add(pacienteMap)
            .addOnSuccessListener {
                _addPacienteResult.postValue(Result.success("Paciente agregado exitosamente"))
                fetchPacientes() // Actualiza la lista de pacientes después de agregar uno nuevo
            }
            .addOnFailureListener { exception ->
                _addPacienteResult.postValue(Result.failure(exception))
            }
    }

    fun searchPaciente(pacienteId: String) {
        firestore.collection("paciente").document(pacienteId).get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val paciente = document.toObject(PacienteModel::class.java)
                    _searchPacienteResult.postValue(Result.success(paciente!!))
                } else {
                    _searchPacienteResult.postValue(Result.failure(Exception("Paciente no encontrado")))
                }
            }
            .addOnFailureListener { exception ->
                _searchPacienteResult.postValue(Result.failure(exception))
            }
    }

    private fun fetchPacientes() {
        firestore.collection("paciente").get()
            .addOnSuccessListener { documents ->
                val pacientesList = documents.mapNotNull { it.toObject(PacienteModel::class.java) }
                _pacientes.postValue(pacientesList)
            }
            .addOnFailureListener { exception ->
                // Manejar el error si es necesario
            }
    }
    fun updatePaciente(paciente: PacienteModel) {
        firestore.collection("paciente").document(paciente.pacienteId)
            .set(paciente.toMap())
            .addOnSuccessListener {
                fetchPacientes()
            }
            .addOnFailureListener { exception ->
                // Manejar errores
            }
    }
    
}
