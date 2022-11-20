package com.example.quotesapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quotesapplication.adapters.QuoteAdapter
import com.example.quotesapplication.api.QuoteService
import com.example.quotesapplication.api.RetrofitHelper
import com.example.quotesapplication.databinding.ActivityMainBinding
import com.example.quotesapplication.repository.QuoteRepository
import com.example.quotesapplication.viewmodel.MainViewModel
import com.example.quotesapplication.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    //****** here we make a object of ViewModel class
    lateinit var mainViewModel: MainViewModel
    private lateinit var quotesAdapter: QuoteAdapter
    private lateinit var activityMainBinding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        //******* here we call a recycler view function
        prepareRecyclerView()

        //*****  first we get the access of the QUOTESERVICE class
        val quoteService = RetrofitHelper.getInstance().create(QuoteService::class.java)

        //****** then we access the repository class
        val repository = QuoteRepository(quoteService)

        //***** then we access the MainViewModel
        mainViewModel =
            ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)


        //**** ab hm LiveData ko access krenge jo MainViewModel class me he
        mainViewModel.quotes.observe(this, Observer { quoteListResponse ->

            //****** here we first access the result , because data is stored in Result.
            //**** so with the help of quoteListResponse we get the Result class.

            val quotes = quoteListResponse.results

               quotesAdapter.setMovieList(quotes)

        })
    }


    //******* here we  make a function that set item in recyclerview

    private fun prepareRecyclerView() {
        quotesAdapter = QuoteAdapter()
        activityMainBinding.mainRecyclerViewQuotes.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = quotesAdapter
        }
    }
}