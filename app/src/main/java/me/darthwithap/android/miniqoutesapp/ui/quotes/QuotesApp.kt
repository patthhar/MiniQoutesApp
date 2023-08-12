package me.darthwithap.android.miniqoutesapp.ui.quotes

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import me.darthwithap.android.miniqoutesapp.data.repository.QuotesRepositoryImpl

@Composable
fun QuotesApp(repository: QuotesRepositoryImpl) {
  Scaffold {
    QuotesScreen(Modifier.padding(it), rememberScaffoldState(), repository)
  }
}