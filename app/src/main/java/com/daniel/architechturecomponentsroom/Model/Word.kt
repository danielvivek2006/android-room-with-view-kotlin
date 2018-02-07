package com.daniel.architechturecomponentsroom.Model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

@Entity(tableName = "word_table")
data class Word(@PrimaryKey
                @NonNull
                @ColumnInfo(name = "word") var word: String) {
}