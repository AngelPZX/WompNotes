package com.example.wompnotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.wompnotes.models.Note
import com.example.wompnotes.models.NoteType
import com.example.wompnotes.ui.NoteDetailScreen
import com.example.wompnotes.ui.NoteListScreen

import com.example.wompnotes.ui.theme.WompNotesTheme

import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Aplicar el tema personalizado en toda la aplicación
            WompNotesTheme {
                WompNotesApp() // Función principal de la app
            }
        }
    }
}

@Composable
fun WompNotesApp() {
    // Controlador de navegación
    val navController = rememberNavController()
    var notes by remember { mutableStateOf(sampleNotes()) } // Lista de notas de ejemplo

    // Definir las rutas de navegación con NavHost
    NavHost(
        navController = navController,
        startDestination = "note_list"
    ) {
        // Ruta para la lista de notas
        composable("note_list") {
            NoteListScreen(
                notes = notes,
                onAddNoteClick = {
                    // Navegar a la pantalla de detalles para agregar una nueva nota
                    navController.navigate("note_detail/${UUID.randomUUID()}")
                },
                onNoteClick = { note ->
                    // Navegar a la pantalla de detalles con el ID de la nota seleccionada
                    navController.navigate("note_detail/${note.id}")
                }
            )
        }

        // Ruta para los detalles de una nota
        composable(
            route = "note_detail/{noteId}",
            arguments = listOf(navArgument("noteId") { type = NavType.StringType })
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getString("noteId") ?: return@composable
            val note = notes.find { it.id.toString() == noteId } ?: Note(
                id = UUID.fromString(noteId),
                title = "",
                description = ""
            )

            NoteDetailScreen(
                note = note,
                onSaveClick = { updatedNote ->
                    // Actualizar la lista de notas con la nueva información
                    notes = notes.toMutableList().apply {
                        removeIf { it.id == updatedNote.id }
                        add(updatedNote)
                    }
                    navController.popBackStack() // Regresar a la pantalla anterior
                },
                onCancelClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}

// Notas de ejemplo para iniciar la aplicación
fun sampleNotes(): List<Note> {
    return listOf(
        Note(
            id = UUID.randomUUID(),
            title = "Tarea 1",
            description = "Descripción de la tarea 1",
            noteType = NoteType.TASK,
            dueDate = Calendar.getInstance().time
        ),
        Note(
            id = UUID.randomUUID(),
            title = "Nota de Reunión",
            description = "Revisión de requisitos de la aplicación"
        )
    )
}
