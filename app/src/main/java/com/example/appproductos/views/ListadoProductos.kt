package com.example.appproductos.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.LiveData
import com.example.appproductos.model.Producto
import com.example.appproductos.viewmodels.ProductoViewModel
import com.example.appproductos.dialogs.ConfirmDeleteDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListadoProductos(
    productos: LiveData<List<Producto>>,
    onNavigateToInicio: () -> Unit,
    onNavigateToRegistro: () -> Unit,
    onNavigateToEdit: (Producto) -> Unit,
    viewModel: ProductoViewModel
) {
    val productosList by productos.observeAsState(emptyList())

    var showDialog by remember { mutableStateOf(false) }
    var productoToDelete by remember { mutableStateOf<Producto?>(null) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Listado de Productos",
                            color = Color.White,
                            fontSize = 24.sp,
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { onNavigateToInicio() }) {
                        Icon(
                            Icons.Default.ArrowBack, contentDescription = "Regresar",
                            tint = MaterialTheme.colorScheme.tertiary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onNavigateToRegistro() },
                containerColor = MaterialTheme.colorScheme.tertiaryContainer
            ) {
                Icon(Icons.Default.Add, contentDescription = "Registrar Producto", tint = Color.White)
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (productosList.isEmpty()) {
                    Text("No hay productos disponibles.", fontSize = 20.sp)
                } else {
                    productosList.forEach { producto ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .shadow(4.dp, shape = MaterialTheme.shapes.small),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onSecondaryContainer)
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth(),
                                horizontalAlignment = Alignment.Start,
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(
                                    text = "${producto.Nombre}",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.tertiaryContainer
                                )
                                Text(
                                    text = "${producto.Descripcion}",
                                    fontSize = 14.sp,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "Precio: ${producto.Precio} $",
                                    fontSize = 16.sp,
                                    color = MaterialTheme.colorScheme.onErrorContainer
                                )
                                Text(
                                    text = "Fecha de Registro: ${producto.FechaRegistro}",
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.onBackground
                                )

                                Row(
                                    modifier = Modifier.align(Alignment.End),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Button(
                                        onClick = { onNavigateToEdit(producto) },
                                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
                                    ) {
                                        Icon(imageVector = Icons.Filled.Edit, contentDescription = "Editar", tint = MaterialTheme.colorScheme.onPrimary)
                                    }

                                    Button(
                                        onClick = {
                                            productoToDelete = producto
                                            showDialog = true
                                        },
                                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                                    ) {
                                        Icon(imageVector = Icons.Filled.Delete, contentDescription = "Eliminar", tint = Color.White)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    // Mostrar el diálogo de confirmación si se activa
    if (showDialog && productoToDelete != null) {
        ConfirmDeleteDialog(
            onConfirm = {
                viewModel.eliminarProducto(productoToDelete!!)
                showDialog = false
                productoToDelete = null
            },
            onCancel = {
                showDialog = false
                productoToDelete = null
            }
        )
    }
}
