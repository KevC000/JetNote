package com.yonasoft.jetnote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.yonasoft.jetnote.model.Note
import com.yonasoft.jetnote.model.screen.NoteScreen
import com.yonasoft.jetnote.model.screen.NoteViewModel
import com.yonasoft.jetnote.ui.theme.JetNoteTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetNoteTheme {
                val noteViewModel = viewModel<NoteViewModel>()
                NotesApp(noteViewModel = noteViewModel)
            }
        }
    }
}

@Composable
fun NotesApp(noteViewModel: NoteViewModel = viewModel()) {
    val notesList = noteViewModel.noteList.collectAsState().value

    NoteScreen(notes = notesList,
        onRemoveNote = { noteViewModel.removeNote(it) },
        onAddNote = { noteViewModel.addNote(it) })
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetNoteTheme {
        val notes = remember {
            mutableStateListOf<Note>()
        }
        Surface(color = MaterialTheme.colors.background) {
            NoteScreen(notes, onAddNote = {
                notes.add(it)
            }, onRemoveNote = {
                notes.remove(it)
            })
        }
    }
}