package com.example.appproductos.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appproductos.model.Producto
import com.example.appproductos.viewmodels.ProductoViewModel
import com.example.appproductos.dialogs.ProductModifiedDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditarProducto(
    producto: Producto,
    onNavigateBack: () -> Unit,
    viewModel: ProductoViewModel
) {
    var nombre by remember { mutableStateOf(producto.Nombre) }
    var descripcion by remember { mutableStateOf(producto.Descripcion) }
    var precio by remember { mutableStateOf(producto.Precio.toString()) }
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Editar Producto",
                        color = Color.White,
                        fontSize = 24.sp,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { onNavigateBack() }) {
                        Icon(
                            Icons.Default.ArrowBack, contentDescription = "Regresar",
                            tint = MaterialTheme.colorScheme.tertiary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // TextField para Nombre
            TextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.inversePrimary,
                    unfocusedContainerColor = MaterialTheme.colorScheme.inversePrimary,
                    focusedIndicatorColor = MaterialTheme.colorScheme.inverseSurface,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.inverseSurface,
                    cursorColor = MaterialTheme.colorScheme.inverseSurface
                ),
                modifier = Modifier.widthIn(max = 300.dp)
            )

            TextField(
                value = descripcion,
                onValueChange = { descripcion = it },
                label = { Text("Descripción") },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.inversePrimary,
                    unfocusedContainerColor = MaterialTheme.colorScheme.inversePrimary,
                    focusedIndicatorColor = MaterialTheme.colorScheme.inverseSurface,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.inverseSurface,
                    cursorColor = MaterialTheme.colorScheme.inverseSurface
                ),
                modifier = Modifier.widthIn(max = 300.dp)
            )

            TextField(
                value = precio,
                onValueChange = { precio = it },
                label = { Text("Precio") },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.inversePrimary,
                    unfocusedContainerColor = MaterialTheme.colorScheme.inversePrimary,
                    focusedIndicatorColor = MaterialTheme.colorScheme.inverseSurface,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.inverseSurface,
                    cursorColor = MaterialTheme.colorScheme.inverseSurface
                ),
                modifier = Modifier.widthIn(max = 300.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                // Botón para guardar cambios
                Button(
                    onClick = {
                        viewModel.editarProducto(
                            producto.copy(Nombre = nombre, Descripcion = descripcion, Precio = precio.toDouble())
                        )
                        showDialog = true
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    modifier = Modifier
                        .width(120.dp)
                        .padding(end = 8.dp)
                ) {
                    Text("Guardar")
                }

                Button(
                    onClick = { onNavigateBack() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error,
                        contentColor = MaterialTheme.colorScheme.onError
                    ),
                    modifier = Modifier
                        .width(120.dp)
                        .padding(start = 8.dp)
                ) {
                    Text("Cancelar")
                }
            }
        }
    }

    // Mostrar el diálogo de información después de editar
    if (showDialog) {
        ProductModifiedDialog(
            onDismiss = {
                showDialog = false
                onNavigateBack()
            }
        )
    }
}
