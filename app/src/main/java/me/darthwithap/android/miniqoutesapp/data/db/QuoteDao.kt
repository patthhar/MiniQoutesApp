package me.darthwithap.android.miniqoutesapp.data.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import me.darthwithap.android.miniqoutesapp.domain.models.Quote

@Dao
interface QuoteDao {
  @Upsert
  fun upsertQuote(quotes: List<QuoteEntity>)

  @Query("SELECT * FROM QuoteEntity")
  suspend fun getQuotes(): List<Quote>
}