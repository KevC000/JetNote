package com.yonasoft.jetnote.model.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.yonasoft.jetnote.model.Note
import com.yonasoft.jetnote.util.DateConverter
import com.yonasoft.jetnote.util.UUIDConverter

@Database(entities=[Note::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class, UUIDConverter::class)
abstract class NoteDatabase: RoomDatabase() {
    abstract fun noteDao():NoteDao
}