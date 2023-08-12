package me.darthwithap.android.miniqoutesapp.data.api.dto

import me.darthwithap.android.miniqoutesapp.data.db.QuoteEntity
import me.darthwithap.android.miniqoutesapp.domain.models.Quote

data class QuoteDto(
  val _id: String,
  val content: String,
  val author: String
) {
  fun toDomainModel(): Quote {
    return Quote(_id, content, author)
  }

  fun toEntity(): QuoteEntity {
    return QuoteEntity(
      id = _id,
      author = author,
      content = content
    )
  }
}