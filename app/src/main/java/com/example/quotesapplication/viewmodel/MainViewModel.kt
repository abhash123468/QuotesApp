package com.example.quotesapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.quotesapplication.models.QuotesListResponse
import com.example.quotesapplication.repository.QuoteRepository
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@OptIn(DelicateCoroutinesApi::class)
class MainViewModel(val quoteRepository: QuoteRepository) : ViewModel() {

    //****** yha par hm init() function me quoteRepository ka class ka method call krenge
    //   ******  kyun ki jab hi MainViewModel class run hoga to first me ye GetQuotes layega.


    init {
        GlobalScope.launch {
            quoteRepository.getQuotes(1)
        }
    }


    /* In below code
   ****             maine live data define kiya he , jo Repository bale LiveData ko point kar rha he .
   ****             jese Repository class ka data change hoga to ye ViewModel ka data vi change karega
   ****            uske badh ye MainActivity ko dega

    */
    val quotes: LiveData<QuotesListResponse>
        get() = quoteRepository.quotes
}