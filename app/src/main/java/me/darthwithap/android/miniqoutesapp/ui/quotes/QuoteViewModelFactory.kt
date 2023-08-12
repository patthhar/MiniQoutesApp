package me.darthwithap.android.miniqoutesapp.ui.quotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.darthwithap.android.miniqoutesapp.domain.repository.QuotesRepository

class QuoteViewModelFactory(private val repository: QuotesRepository) : ViewModelProvider.Factory {
  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    return QuoteViewModel(repository) as T
  }
}