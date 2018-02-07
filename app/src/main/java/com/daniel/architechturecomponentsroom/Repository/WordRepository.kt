package com.daniel.architechturecomponentsroom.Repository

import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import com.daniel.architechturecomponentsroom.Dao.WordDao
import com.daniel.architechturecomponentsroom.Database.WordRoomDatabase
import com.daniel.architechturecomponentsroom.Model.Word


class WordRepository(application: android.app.Application) {
  private var wordDao: WordDao
  private var allWords: LiveData<List<Word>>

  init {
    var db = WordRoomDatabase.getInstance(application.applicationContext)
    wordDao = db.workDao()
    allWords = wordDao.getAllWords()
  }

  fun getAllWords(): LiveData<List<Word>> {
    return allWords;
  }

  fun insert(word: Word) {
    InsertAsyncTask(wordDao).execute(word)
  }

  private class InsertAsyncTask internal constructor(
      private val mAsyncTaskDao: WordDao) : AsyncTask<Word, Void, Void>() {

    override fun doInBackground(vararg params: Word): Void? {
      mAsyncTaskDao.insert(params[0])
      return null
    }
  }

}