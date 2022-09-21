package com.yonasoft.jetnote.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.util.*

@Entity(tableName = "notes_table")

data class Note(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    @ColumnInfo(name= "note_title")
    val title:String,
    @ColumnInfo(name= "note_detail")
    val detail: String,
    @ColumnInfo(name= "note_date")
    val entryDate: Date = Date.from(Instant.now()),
    )
