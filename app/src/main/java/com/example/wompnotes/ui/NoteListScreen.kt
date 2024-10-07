package com.example.wompnotes.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.wompnotes.models.Note

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteListScreen(
    notes: List<Note>,
    onAddNoteClick: () -> Unit,
    onNoteClick: (Note) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Notas y Tareas") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddNoteClick) {
                Text("+")
            }
        }
    ) { paddingValues ->
        // Aquí se pasa el paddingValues para que respete los márgenes
        NoteList(
            notes = notes,
            onNoteClick = onNoteClick,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
fun NoteList(
    notes: List<Note>,
    onNoteClick: (Note) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(notes) { note ->
            NoteItem(note = note, onClick = { onNoteClick(note) })
        }
    }
}

@Composable
fun NoteItem(note: Note, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = note.title, style = MaterialTheme.typography.headlineSmall)
            Text(text = note.description, style = MaterialTheme.typography.bodySmall)
        }
    }
}
