package com.example.appproductos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.appproductos.viewmodels.ProductoViewModel
import androidx.compose.runtime.*
import com.example.appproductos.navigation.NavManager
import com.example.appproductos.room.AppDatabase
import com.example.appproductos.ui.theme.AppProductosTheme
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val database = AppDatabase.getDatabase(this)
        val viewModel: ProductoViewModel = ProductoViewModel(database)

        setContent {
            MyApp(viewModel) // Mantener la lógica existente
        }
    }
}

@Composable
fun MyApp(viewModel: ProductoViewModel) {
    var isDarkTheme by remember { mutableStateOf(false) } // Estado del tema en el nivel de la aplicación

    // Esta función para cambiar el tema se pasará a las pantallas
    val toggleTheme = { isDarkTheme = !isDarkTheme }

    AppProductosTheme(darkTheme = isDarkTheme) { // Usa el valor actual de isDarkTheme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            NavManager(viewModel = viewModel, toggleTheme = toggleTheme, isDarkTheme = isDarkTheme)
        }
    }
}
