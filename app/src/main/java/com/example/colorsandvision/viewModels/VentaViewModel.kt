package com.example.colorsandvision.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.colorsandvision.model.ExamenModel
import com.example.colorsandvision.model.PacienteModel
import com.example.colorsandvision.model.VentaModel
import com.google.firebase.firestore.FirebaseFirestore

class VentaViewModel : ViewModel() {
    private val firestore = FirebaseFirestore.getInstance()
    private val _ventas = MutableLiveData<List<VentaModel>>()
    val ventas: LiveData<List<VentaModel>> get() = _ventas
    val _addVentaResult = MutableLiveData<Result<String>>()

    fun addVenta(ventaModel: VentaModel) {
        val ventaMap = ventaModel.toMap()
        firestore.collection("ventas")
            .add(ventaMap)
            .addOnSuccessListener {
                _addVentaResult.postValue(Result.success("Venta agregado exitosamente"))
            }
            .addOnFailureListener { exception ->
                _addVentaResult.postValue(Result.failure(exception))
            }
    }

    fun fetchVentas() {
        firestore.collection("ventas")
            .get()
            .addOnSuccessListener { result ->
                val ventaList = result.documents.mapNotNull { doc ->
                    val venta = doc.toObject(VentaModel::class.java)
                    venta?.copy(ventaId = doc.id)
                }
                _ventas.postValue(ventaList)
            }
            .addOnFailureListener {
                _ventas.postValue(emptyList())
            }
    }
    fun markAsDelivered(ventaId: String) {
        val docRef = firestore.collection("ventas").document(ventaId)
        docRef.update("entregado", true)
            .addOnSuccessListener {
                fetchVentas()
            }
            .addOnFailureListener {

            }
    }
}

