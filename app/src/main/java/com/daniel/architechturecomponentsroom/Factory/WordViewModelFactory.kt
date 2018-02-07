package com.daniel.architechturecomponentsroom.Factory

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.daniel.architechturecomponentsroom.Application
import com.daniel.architechturecomponentsroom.WordViewModel

class WordViewModelFactory(var application: android.app.Application) : ViewModelProvider.Factory {

  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    if (modelClass.isAssignableFrom(WordViewModel::class.java)) {
      return WordViewModel(application) as (T)
    }
    throw  IllegalArgumentException("Unknown View Model Class")
  }
}