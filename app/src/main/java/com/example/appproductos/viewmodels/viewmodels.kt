package com.example.appproductos.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appproductos.model.Producto
import com.example.appproductos.room.AppDatabase
import kotlinx.coroutines.launch

class ProductoViewModel(private val database: AppDatabase) : ViewModel() {

    val todosLosProductos: LiveData<List<Producto>> = database.productoDao().obtenerTodosLosProductos()

    fun agregarProducto(
        nombre: String,
        descripcion: String,
        precioDouble: Double,
        fechaRegistro: String
    ) {
        val nuevoProducto = Producto(
            Nombre = nombre,
            Descripcion = descripcion,
            Precio = precioDouble,
            FechaRegistro = fechaRegistro
        )

        viewModelScope.launch {
            database.productoDao().insertarProducto(nuevoProducto)
        }
    }

    fun editarProducto(producto: Producto) {
        viewModelScope.launch {
            database.productoDao().actualizarProducto(producto)
        }
    }

    fun eliminarProducto(producto: Producto) {
        viewModelScope.launch {
            database.productoDao().eliminarProducto(producto)
        }
    }

    fun obtenerProductoPorId(id: Int): Producto? {
        return todosLosProductos.value?.find { it.id.toInt() == id }
    }

}