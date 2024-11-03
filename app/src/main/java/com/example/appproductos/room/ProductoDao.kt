package com.example.appproductos.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.appproductos.model.Producto

@Dao
interface ProductoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarProducto(producto: Producto)

    @Update
    suspend fun actualizarProducto(producto: Producto)

    @Delete
    suspend fun eliminarProducto(producto: Producto)

    @Query("SELECT * FROM productos")
    fun obtenerTodosLosProductos(): LiveData<List<Producto>>
}

