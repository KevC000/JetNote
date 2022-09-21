package com.yonasoft.jetnote.repository

import com.yonasoft.jetnote.model.Note
import com.yonasoft.jetnote.model.data.NoteDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

//Repository for data sources
class NoteRepository @Inject constructor(private val noteDao: NoteDao){
    suspend fun addNote(note: Note) = noteDao.insert(note = note)

    suspend fun updateNote(note:Note) = noteDao.update(note = note)

    suspend fun deleteNote(note:Note) = noteDao.deleteNote(note = note)

    suspend fun deleteAllNotes() = noteDao.deleteAll()

    fun getAllNotes():Flow<List<Note>> = noteDao.getNotes().flowOn(Dispatchers.IO).conflate()
}