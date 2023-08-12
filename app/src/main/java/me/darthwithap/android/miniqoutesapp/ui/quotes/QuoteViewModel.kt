package me.darthwithap.android.miniqoutesapp.ui.quotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import me.darthwithap.android.miniqoutesapp.domain.models.Quote
import me.darthwithap.android.miniqoutesapp.domain.repository.QuotesRepository
import me.darthwithap.android.miniqoutesapp.util.Resource

class QuoteViewModel(
  private val repository: QuotesRepository
) : ViewModel() {
  private lateinit var quotes: List<Quote>
  private var index = 0

  private val _state: MutableStateFlow<QuoteState> = MutableStateFlow(QuoteState.QuotesLoading)
  val state: StateFlow<QuoteState>
    get() = _state

  init {
    getQuotes()
  }

  private fun getQuotes() {
    viewModelScope.launch(Dispatchers.IO) {
      repository.getQuotes().collect {
        when (it) {
          is Resource.Loading -> {
            _state.emit(QuoteState.QuotesLoading)
          }

          is Resource.Success -> {
            quotes = it.data!!
            _state.emit(QuoteState.QuoteSuccess(getRandomQuote()))
          }

          is Resource.Error -> {
            _state.emit(QuoteState.QuotesError(it.errorMsg!!))
          }
        }
      }
    }
  }

  fun previousQuote() {
    viewModelScope.launch(Dispatchers.IO) {
      if (quotes.isNotEmpty() && index != 0) {
        _state.emit(QuoteState.QuoteSuccess(quotes[--index]))
      } else _state.emit(QuoteState.QuotesError("No previous quote"))
    }
  }

  fun nextQuote() {
    viewModelScope.launch {
      if (quotes.isNotEmpty() && index != quotes.lastIndex) {
        _state.emit(QuoteState.QuoteSuccess(quotes[++index]))
      } else _state.emit(QuoteState.QuotesError("No next quote"))
    }
  }

  private fun getRandomQuote(): Quote {
    val quote = quotes.random()
    index = quotes.indexOf(quote)
    return quote
  }

  sealed class QuoteState {
    object QuotesLoading : QuoteState()
    data class QuoteSuccess(val quote: Quote) : QuoteState()
    data class QuotesError(val errorMsg: String) : QuoteState()
  }
}