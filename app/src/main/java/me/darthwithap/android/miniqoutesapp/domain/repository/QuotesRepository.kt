package me.darthwithap.android.miniqoutesapp.domain.repository

import kotlinx.coroutines.flow.Flow
import me.darthwithap.android.miniqoutesapp.domain.models.Quote
import me.darthwithap.android.miniqoutesapp.util.Resource

interface QuotesRepository {
  fun getQuotes(): Flow<Resource<List<Quote>>>
}