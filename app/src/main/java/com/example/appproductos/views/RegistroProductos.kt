package com.example.appproductos.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import com.example.appproductos.R
import com.example.appproductos.room.AppDatabase
import com.example.appproductos.viewmodels.ProductoViewModel
import com.example.appproductos.dialogs.ProductAddedDialog // Importa el diálogo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistroProductos(
    onNavigateBack: () -> Unit,
    viewModel: ProductoViewModel
) {
    var nombre by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var fechaRegistro by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Registro de Producto",
                        color = Color.White,
                        fontSize = 24.sp,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            Icons.Default.ArrowBack, contentDescription = "Regresar",
                            tint = MaterialTheme.colorScheme.tertiary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.onPrimary
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.newlogo),
                contentDescription = "Logo de la tienda",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            TextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre del Producto", color = MaterialTheme.colorScheme.onTertiaryContainer) },
                modifier = Modifier.width(300.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = MaterialTheme.colorScheme.inverseSurface,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.inverseSurface,
                    cursorColor = MaterialTheme.colorScheme.inverseSurface,
                    focusedContainerColor = MaterialTheme.colorScheme.inversePrimary,
                    unfocusedContainerColor = MaterialTheme.colorScheme.inversePrimary
                )
            )

            TextField(
                value = descripcion,
                onValueChange = { descripcion = it },
                label = { Text("Descripción", color = MaterialTheme.colorScheme.onTertiaryContainer) },
                modifier = Modifier.width(300.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = MaterialTheme.colorScheme.inverseSurface,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.inverseSurface,
                    cursorColor = MaterialTheme.colorScheme.inverseSurface,
                    focusedContainerColor = MaterialTheme.colorScheme.inversePrimary,
                    unfocusedContainerColor = MaterialTheme.colorScheme.inversePrimary
                )
            )

            TextField(
                value = precio,
                onValueChange = { precio = it },
                label = { Text("Precio", color = MaterialTheme.colorScheme.onTertiaryContainer) },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier.width(300.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = MaterialTheme.colorScheme.inverseSurface,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.inverseSurface,
                    cursorColor = MaterialTheme.colorScheme.inverseSurface,
                    focusedContainerColor = MaterialTheme.colorScheme.inversePrimary,
                    unfocusedContainerColor = MaterialTheme.colorScheme.inversePrimary
                )
            )

            TextField(
                value = fechaRegistro,
                onValueChange = { fechaRegistro = it },
                label = { Text("Fecha de Registro", color = MaterialTheme.colorScheme.onTertiaryContainer) },
                modifier = Modifier.width(300.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = MaterialTheme.colorScheme.inverseSurface,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.inverseSurface,
                    cursorColor = MaterialTheme.colorScheme.inverseSurface,
                    focusedContainerColor = MaterialTheme.colorScheme.inversePrimary,
                    unfocusedContainerColor = MaterialTheme.colorScheme.inversePrimary
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        if (nombre.isNotBlank() && descripcion.isNotBlank() && precio.isNotBlank() && fechaRegistro.isNotBlank()) {
                            val precioDouble = precio.toDoubleOrNull()
                            if (precioDouble != null) {
                                viewModel.agregarProducto(nombre, descripcion, precioDouble, fechaRegistro)
                                showDialog = true
                            } else {
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.tertiaryContainer),
                    modifier = Modifier.padding(horizontal = 8.dp)
                ) {
                    Text("Registrar", color = MaterialTheme.colorScheme.onPrimary)
                }
                Button(
                    onClick = { onNavigateBack() },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                    modifier = Modifier.padding(horizontal = 8.dp)
                ) {
                    Text("Cancelar", color = MaterialTheme.colorScheme.tertiary)
                }
            }
        }
    }

    // Mostrar el diálogo de información después de añadir
    if (showDialog) {
        ProductAddedDialog(
            onDismiss = {
                showDialog = false
                onNavigateBack()
            }
        )
    }
}