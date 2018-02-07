package com.daniel.architechturecomponentsroom.Database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.daniel.architechturecomponentsroom.Dao.WordDao
import com.daniel.architechturecomponentsroom.Model.Word
import com.daniel.architechturecomponentsroom.Wrapper.SingletonHolder

@Database(entities = arrayOf(Word::class), version = 1, exportSchema = false)
abstract class WordRoomDatabase : RoomDatabase() {
  abstract fun workDao(): WordDao

  companion object : SingletonHolder<WordRoomDatabase, Context>({
    Room.databaseBuilder(it.applicationContext, WordRoomDatabase::class.java,
        "word_database").build()
  })
}