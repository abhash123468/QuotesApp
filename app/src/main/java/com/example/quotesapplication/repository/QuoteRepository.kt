package com.example.quotesapplication.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.quotesapplication.api.QuoteService
import com.example.quotesapplication.models.QuotesListResponse

//**** in this class ,we get the QuoteService Class ,so we can access by passing
//****   parameter
class QuoteRepository(private val quoteService :QuoteService) {

    private val quoteLiveData = MutableLiveData<QuotesListResponse>()
    val quotes :LiveData<QuotesListResponse>
    get() = quoteLiveData


    //**** yha par hm ek function bnayenge jo view model se access krenge
    suspend fun getQuotes(page:Int){
        var result = quoteService.getQuotes(page)
        if(result.body() !=null){
            quoteLiveData.postValue(result.body())
        }
    }
}