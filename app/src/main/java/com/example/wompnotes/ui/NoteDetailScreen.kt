package com.example.wompnotes.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.wompnotes.models.Note
import com.example.wompnotes.models.NoteType
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDetailScreen(
    note: Note = Note( // Creamos una nota vacía por defecto si no se pasa nada
        title = "",
        description = "",
        noteType = NoteType.NOTE
    ),
    onSaveClick: (Note) -> Unit,
    onCancelClick: () -> Unit
) {
    var title by remember { mutableStateOf(TextFieldValue(note.title)) }
    var description by remember { mutableStateOf(TextFieldValue(note.description)) }
    var noteType by remember { mutableStateOf(note.noteType) }
    var dueDate by remember { mutableStateOf(note.dueDate) }

    val dateFormatter = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Detalle de la Nota") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            // Campo para el Título
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Título") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campo para la Descripción
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Descripción") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Radio buttons para seleccionar tipo: Nota o Tarea
            Row(modifier = Modifier.fillMaxWidth()) {
                Text("Tipo: ", modifier = Modifier.padding(end = 8.dp))
                RadioButton(
                    selected = noteType == NoteType.NOTE,
                    onClick = { noteType = NoteType.NOTE }
                )
                Text("Nota", modifier = Modifier.padding(end = 16.dp))

                RadioButton(
                    selected = noteType == NoteType.TASK,
                    onClick = { noteType = NoteType.TASK }
                )
                Text("Tarea")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Mostrar DatePicker y TimePicker si es una tarea
            if (noteType == NoteType.TASK) {
                // Simulamos la fecha para el ejemplo
                Button(onClick = { dueDate = Calendar.getInstance().time }) {
                    Text("Seleccionar Fecha y Hora")
                }
                dueDate?.let {
                    Text("Fecha de Cumplimiento: ${dateFormatter.format(it)}")
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Botones de Guardar y Cancelar
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                Button(onClick = { onCancelClick() }) {
                    Text("Cancelar")
                }
                Button(
                    onClick = {
                        // Guardar los cambios
                        onSaveClick(
                            note.copy(
                                title = title.text,
                                description = description.text,
                                noteType = noteType,
                                dueDate = dueDate
                            )
                        )
                    }
                ) {
                    Text("Guardar")
                }
            }
        }
    }
}
