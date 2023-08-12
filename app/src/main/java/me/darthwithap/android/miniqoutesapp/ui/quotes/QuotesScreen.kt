package me.darthwithap.android.miniqoutesapp.ui.quotes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import me.darthwithap.android.miniqoutesapp.data.repository.QuotesRepositoryImpl
import me.darthwithap.android.miniqoutesapp.domain.models.Quote

@Composable
fun QuotesScreen(
  modifier: Modifier,
  scaffoldState: ScaffoldState,
  repository: QuotesRepositoryImpl
) {
  val viewModel: QuoteViewModel = viewModel(
    modelClass = QuoteViewModel::class.java,
    factory = QuoteViewModelFactory(repository)
  )

  var quote by remember { mutableStateOf<Quote?>(null) }
  var errorMsg by remember { mutableStateOf<String?>(null) }

  LaunchedEffect(key1 = true) {
    viewModel.state.collect {
      when (it) {
        is QuoteViewModel.QuoteState.QuotesError -> {
          errorMsg = it.errorMsg
        }

        is QuoteViewModel.QuoteState.QuoteSuccess -> {
          quote = it.quote
          errorMsg = null
        }

        is QuoteViewModel.QuoteState.QuotesLoading -> {
          quote = null
          errorMsg = null
        }
      }
    }
  }

  Box(
    modifier = modifier
      .fillMaxSize()
      .background(Color.Cyan),
    contentAlignment = Alignment.Center
  ) {
    if (quote != null) {
      QuoteItem(
        quote = quote!!,
        onPreviousClick = {
          viewModel.previousQuote()
        }, onNextClick = {
          viewModel.nextQuote()
        })
    } else {
      CircularProgressIndicator()
    }
    if (errorMsg != null) {
      Text(text = errorMsg!!, modifier = modifier.align(Alignment.BottomCenter))
    }
  }
}
