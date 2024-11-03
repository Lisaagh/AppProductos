package com.example.appproductos.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "productos")
data class Producto(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val Nombre: String,
    val Descripcion: String,
    val Precio: Double,
    val FechaRegistro: String
)

