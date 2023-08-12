package me.darthwithap.android.miniqoutesapp.data.repository

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import me.darthwithap.android.miniqoutesapp.data.api.QuotesApi
import me.darthwithap.android.miniqoutesapp.data.api.dto.QuoteDto
import me.darthwithap.android.miniqoutesapp.data.db.QuoteDao
import me.darthwithap.android.miniqoutesapp.domain.models.Quote
import me.darthwithap.android.miniqoutesapp.domain.repository.QuotesRepository
import me.darthwithap.android.miniqoutesapp.util.Resource
import me.darthwithap.android.miniqoutesapp.util.isInternetConnected
import retrofit2.Response

class QuotesRepositoryImpl(
  private val api: QuotesApi,
  private val dao: QuoteDao,
  private val context: Context
) : QuotesRepository {
  private lateinit var flow: Flow<Resource<List<Quote>>>

  override fun getQuotes(): Flow<Resource<List<Quote>>> {

    flow = flow {
      val offlineQuotes: List<Quote> = dao.getQuotes()
      if (offlineQuotes.isEmpty()) {
        if (context.isInternetConnected()) {
          val response: Response<List<QuoteDto>> = api.getQuotes()
          if (response.body() == null) emit(Resource.Error("Server error"))
          else {
            if (response.body()!!.isEmpty()) {
              emit(Resource.Error("No quotes found"))
            } else {
              dao.upsertQuote(response.body()!!.map { it.toEntity() })
              emit(Resource.Success(response.body()!!.map { it.toDomainModel() }))
            }
          }
        } else emit(Resource.Error("Internet is not connected"))

      } else emit(Resource.Success(offlineQuotes))
    }.flowOn(Dispatchers.IO)

    return flow
  }
}