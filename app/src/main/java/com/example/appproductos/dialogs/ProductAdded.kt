package com.example.appproductos.dialogs

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@Composable
fun ProductAddedDialog(
    title: String = "Producto Agregado",
    message: String = "El producto ha sido agregado exitosamente.",
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = {
            Text(text = title, color = MaterialTheme.colorScheme.tertiaryContainer)
        },
        text = {
            Text(text = message, color = MaterialTheme.colorScheme.onBackground)
        },
        confirmButton = {
            Button(
                onClick = { onDismiss() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text("Confirmar", color = MaterialTheme.colorScheme.onPrimary)
            }
        }
    )
}
