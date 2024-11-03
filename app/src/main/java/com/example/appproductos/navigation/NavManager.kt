package com.example.appproductos.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appproductos.views.Inicio
import com.example.appproductos.views.ListadoProductos
import com.example.appproductos.views.CartaPresentacion
import com.example.appproductos.views.RegistroProductos
import com.example.appproductos.views.EditarProducto
import com.example.appproductos.viewmodels.ProductoViewModel

@Composable
fun NavManager(
    viewModel: ProductoViewModel,
    toggleTheme: () -> Unit,   // Parámetro para cambiar el tema
    isDarkTheme: Boolean       // Estado actual del tema
) {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavDestinations.Inicio.route
    ) {
        composable(NavDestinations.Inicio.route) {
            Inicio(
                onSelectListado = { navController.navigate(NavDestinations.ListadoProductos.route) },
                onSelectCarta = { navController.navigate(NavDestinations.CartaPresentacion.route) },
                toggleTheme = toggleTheme,  // Pasar la función de cambio de tema a la pantalla de Inicio
                isDarkTheme = isDarkTheme   // Pasar el estado actual del tema
            )
        }
        composable(NavDestinations.ListadoProductos.route) {
            ListadoProductos(
                productos = viewModel.todosLosProductos,
                onNavigateToInicio = { navController.navigate(NavDestinations.Inicio.route) },
                onNavigateToRegistro = { navController.navigate(NavDestinations.RegistroProductos.route) },
                onNavigateToEdit = { producto ->
                    navController.navigate("editar_producto/${producto.id}")
                },
                viewModel = viewModel
            )
        }
        composable(NavDestinations.CartaPresentacion.route) {
            CartaPresentacion(onNavigateBack = { navController.navigate(NavDestinations.Inicio.route) })
        }
        composable(NavDestinations.RegistroProductos.route) {
            RegistroProductos(
                onNavigateBack = { navController.navigateUp() },
                viewModel = viewModel
            )
        }
        composable("editar_producto/{productoId}") { backStackEntry ->
            val productoId = backStackEntry.arguments?.getString("productoId")?.toInt()
            val producto =
                productoId?.let { viewModel.obtenerProductoPorId(it) }
            if (producto != null) {
                EditarProducto(
                    producto = producto,
                    onNavigateBack = { navController.popBackStack() },
                    viewModel = viewModel
                )
            }
        }
    }
}
