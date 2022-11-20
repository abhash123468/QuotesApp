package com.example.quotesapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quotesapplication.R

class QuoteAdapter : RecyclerView.Adapter<QuoteAdapter.QuoteViewHolder>() {
    private var quoteList = ArrayList<com.example.quotesapplication.models.Result>()


    //***** here we make a function
    fun setMovieList(quoteList : List<com.example.quotesapplication.models.Result>){
        this.quoteList = quoteList as ArrayList<com.example.quotesapplication.models.Result>
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {


        val view = LayoutInflater.from(parent.context).inflate(R.layout.quote_list_single_item_design,parent,false)
        val viewHolder = QuoteViewHolder(view)

        return viewHolder
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        val currentQuotes = quoteList[position]
        holder.tvContent.text = currentQuotes.content
        holder.tvAuthor.text = currentQuotes.author
        holder.tvDateAdded.text = currentQuotes.dateAdded
        holder.tvDateModified.text = currentQuotes.dateModified
    }

    override fun getItemCount(): Int {
      return quoteList.size
    }

    // here we make a view holder class
    class QuoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        //****here we access the view of the sing item_new.xml with the help fo item view.
        val tvContent :TextView = itemView.findViewById(R.id.tvContent)
        val tvAuthor :TextView = itemView.findViewById(R.id.tvAuthor)
        val tvDateAdded :TextView = itemView.findViewById(R.id.tvDateAdded)
        val tvDateModified :TextView = itemView.findViewById(R.id.tvDateModified)
    }

}