package com.example.test_start

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_news_layout.view.*

class NewsAdapter(val context: Context, val items: ArrayList<NewModelClass>) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val tvId = view.tv_id
        val tvTitle = view.tv_title
        val tvDescription = view.tv_description
        val tvData = view.tv_data
        val tvKeywords = view.tv_keywords
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_news_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = items.get(position)

        holder.tvId.text = item.id.toString()
        holder.tvTitle.text = item.title
        holder.tvDescription.text = item.description
        holder.tvData.text = item.date
        holder.tvKeywords.text = item.keywords.toString()
    }

    override fun getItemCount(): Int {
        return items.size
    }
}