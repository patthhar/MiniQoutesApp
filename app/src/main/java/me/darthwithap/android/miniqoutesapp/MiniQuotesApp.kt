package me.darthwithap.android.miniqoutesapp

import android.app.Application
import android.content.Context
import me.darthwithap.android.miniqoutesapp.data.api.QuotesApi
import me.darthwithap.android.miniqoutesapp.data.db.QuoteDao
import me.darthwithap.android.miniqoutesapp.data.db.QuotesDatabase
import me.darthwithap.android.miniqoutesapp.data.repository.QuotesRepositoryImpl
import me.darthwithap.android.miniqoutesapp.util.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MiniQuotesApp : Application()