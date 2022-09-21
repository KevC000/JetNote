package com.yonasoft.jetnote.di

import android.content.Context
import androidx.room.Room
import com.yonasoft.jetnote.model.data.NoteDao
import com.yonasoft.jetnote.model.data.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
//Dagger Hilt module
@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Singleton
    @Provides
    fun provideNotesDao(noteDatabase: NoteDatabase): NoteDao = noteDatabase.noteDao()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context):NoteDatabase = Room.databaseBuilder(context,
        NoteDatabase::class.java,
        "notes").
    fallbackToDestructiveMigration()
        .build()
}