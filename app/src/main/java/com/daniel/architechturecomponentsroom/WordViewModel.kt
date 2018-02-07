package com.daniel.architechturecomponentsroom

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.daniel.architechturecomponentsroom.Model.Word
import com.daniel.architechturecomponentsroom.Repository.WordRepository

class WordViewModel(application: Application) : AndroidViewModel(application) {
  private var wordRepository: WordRepository? = null
  private var allWords: LiveData<List<Word>>? = null

  init {
    wordRepository = WordRepository(application)
    allWords = wordRepository!!.getAllWords()
  }

  fun getAllWords(): LiveData<List<Word>>? {
    return allWords.apply { allWords }
  }

  fun insert(word: Word) {
    wordRepository?.insert(word)
  }
}