package com.yonasoft.jetnote.model.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yonasoft.jetnote.R
import com.yonasoft.jetnote.model.Note
import com.yonasoft.jetnote.model.components.NoteButton
import com.yonasoft.jetnote.model.components.NoteComponentText
import com.yonasoft.jetnote.util.Utils



//Main screen containing text input field, add button, and list of notes.
@Composable
fun NoteScreen(
    notes:List<Note>,
    onAddNote:(Note) -> Unit,
    onRemoveNote: (Note) -> Unit
){
    //Title mutable state for the title field. Updates based on the title text input field
    var title by remember{
        mutableStateOf("")
    }
    //Detail mutable state for the detail field. Updates based on the detail text input field
    var detail by remember{
        mutableStateOf("")
    }
    val context = LocalContext.current
    //Column for whole screen
    Column(modifier = Modifier.padding(6.dp)) {
        //App bar
        TopAppBar(title = {
            Text(text = stringResource(id = R.string.app_name))
        },
            actions = {
                Icon(imageVector = Icons.Rounded.Notifications,
                    contentDescription = "Icon"
                )
            },
                backgroundColor = Color(0xFFDADFE3))
        //Column of content
        Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
            ) {
            //Text field for title
            NoteComponentText(
                modifier = Modifier.padding(
                    top = 9.dp,
                    bottom = 9.dp
                ),
                text = title,
                label = "Title",
                onTextChange = {
                    if(it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        }) title = it
                })
            //Text field for details
            NoteComponentText(
                modifier = Modifier.padding(
                    top = 9.dp,
                    bottom = 9.dp
                ),
                text = detail,
                label = "Add details",
                onTextChange = {
                    if(it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        }) detail = it
                })
            //Save if title and description is not empty.
            //Reset title and detail fields
            NoteButton(text = "Save",
                onClick = {
                    if (title.isNotEmpty() && detail.isNotEmpty()) {
                        onAddNote(Note(title = title,
                            detail = detail))
                        title = ""
                        detail = ""
                        Toast.makeText(context, "Note Added",
                            Toast.LENGTH_SHORT).show()
                    }
                })
        }
        //Divide text fields/buttons from the list of notes
        Divider(modifier = Modifier.padding(10.dp))
        LazyColumn{
            items(notes){note ->
                NoteRow(note = note, onNoteClicked = onRemoveNote)
            }
        }
    }
}

@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    note:Note,
    onNoteClicked: (Note) -> Unit
){
    val context = LocalContext.current
    Surface(modifier = modifier
        .padding(8.dp)
        .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
        .fillMaxWidth(),color = Color(0xFFDFE6EB),
        elevation = 6.dp
    ) {
        Column(modifier.clickable {
            onNoteClicked(note)
            Toast.makeText(
                context, "Note Removed",
                Toast.LENGTH_SHORT).show()
            }.padding(horizontal = 14.dp,
            vertical = 6.dp
            ),
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = note.title,
                style = MaterialTheme.typography.subtitle2
                )
            Text(text = note.detail,
                style = MaterialTheme.typography.subtitle1
            )
            Text(text = Utils().formatDate(note.entryDate.time),
            style = MaterialTheme.typography.caption
                )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotesScreenPreview(){
    NoteScreen(notes = emptyList(), onAddNote = {}, onRemoveNote = {})
}

