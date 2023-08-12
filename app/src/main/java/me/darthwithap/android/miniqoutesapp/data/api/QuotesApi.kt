package me.darthwithap.android.miniqoutesapp.data.api

import me.darthwithap.android.miniqoutesapp.data.api.dto.QuoteDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuotesApi {
  @GET("quotes/random")
  suspend fun getQuotes(@Query("limit") limit: Int = 50): Response<List<QuoteDto>>
}