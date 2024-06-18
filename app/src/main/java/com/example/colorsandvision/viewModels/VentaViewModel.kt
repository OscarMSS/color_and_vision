package com.example.colorsandvision.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.colorsandvision.model.PacienteModel
import com.example.colorsandvision.model.VentaModel
import com.google.firebase.firestore.FirebaseFirestore

class VentaViewModel : ViewModel() {
    private val firestore = FirebaseFirestore.getInstance()
    val _addVentaResult = MutableLiveData<Result<String>>()
    private val _searchVentaResult = MutableLiveData<Result<PacienteModel>>()
    fun addVenta(ventaModel: VentaModel) {
        val ventaMap = ventaModel.toMap()
        firestore.collection("venta")
            .add(ventaMap)
            .addOnSuccessListener {
                _addVentaResult.postValue(Result.success("Venta agregado exitosamente"))
            }
            .addOnFailureListener { exception ->
                _addVentaResult.postValue(Result.failure(exception))
            }
    }
}