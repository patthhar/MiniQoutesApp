package me.darthwithap.android.miniqoutesapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [QuoteEntity::class], version = 1)
abstract class QuotesDatabase : RoomDatabase() {
  abstract fun dao(): QuoteDao

  companion object {
    private var INSTANCE: QuotesDatabase? = null
    fun getInstance(context: Context): QuotesDatabase {
      if (INSTANCE == null) {
        INSTANCE = Room.databaseBuilder(
          context,
          QuotesDatabase::class.java,
          "quotes_db"
        ).build()
      }
      return INSTANCE!!
    }
  }
}