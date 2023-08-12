package me.darthwithap.android.miniqoutesapp.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class QuoteEntity(
  @PrimaryKey(autoGenerate = true)
  val _id: Int = 0,
  val id: String,
  val author: String,
  val content: String
)