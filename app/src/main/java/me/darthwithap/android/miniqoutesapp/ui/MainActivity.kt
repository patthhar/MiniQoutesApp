package me.darthwithap.android.miniqoutesapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import me.darthwithap.android.miniqoutesapp.data.api.QuotesApi
import me.darthwithap.android.miniqoutesapp.data.db.QuotesDatabase
import me.darthwithap.android.miniqoutesapp.data.repository.QuotesRepositoryImpl
import me.darthwithap.android.miniqoutesapp.ui.quotes.QuotesApp
import me.darthwithap.android.miniqoutesapp.ui.theme.MiniQoutesAppTheme
import me.darthwithap.android.miniqoutesapp.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val appContext = this.applicationContext

    val retrofit = Retrofit.Builder()
      .baseUrl(Constants.BASE_URL)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
    val api = retrofit.create(QuotesApi::class.java)
    val dao = QuotesDatabase.getInstance(appContext).dao()
    val repository = QuotesRepositoryImpl(api, dao, appContext)
    setContent {
      MiniQoutesAppTheme {

        QuotesApp(repository)
      }
    }
  }
}