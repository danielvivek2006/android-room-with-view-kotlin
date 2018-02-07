package com.daniel.architechturecomponentsroom

import android.app.Application
import com.daniel.architechturecomponentsroom.Database.WordRoomDatabase

class Application : Application() {
  override fun onCreate() {
    super.onCreate()
    WordRoomDatabase.getInstance(applicationContext)
  }
}