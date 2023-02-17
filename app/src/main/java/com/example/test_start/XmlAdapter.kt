package com.example.test_start

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test_start.parsing_XML.Element
import kotlinx.android.synthetic.main.item_news_layout.view.*

class XmlAdapter (val context: Context, private var elements: MutableList<Element> ) : RecyclerView.Adapter<XmlAdapter.XmlHolder>() {

    class XmlHolder(view: View) : RecyclerView.ViewHolder(view) {

        val xmlId = view.tv_id
        val xmlTitle = view.tv_title
        val xmlDescription = view.tv_description
        val xmlData = view.tv_data
        val xmlKeywords = view.tv_keywords
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): XmlHolder {
        return XmlAdapter.XmlHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_news_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: XmlHolder, position: Int) {
        val element = elements.get(position)

        holder.xmlId.text = element.id.toString()
        holder.xmlTitle.text = element.title
        holder.xmlDescription.text = element.description
        holder.xmlData.text = element.date
        holder.xmlKeywords.text = element.keywords.toString()

    }

    override fun getItemCount(): Int = elements.size
}
