package com.example.newsapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.model.ArticlesItem


class NewsAdapter(var items: List<ArticlesItem?>?) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val sourceName: TextView = itemView.findViewById(R.id.source_name)
        val dateTime: TextView = itemView.findViewById(R.id.date_time)
        val image: ImageView = itemView.findViewById(R.id.image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items?.get(position)
        holder.title.text = item?.title
        holder.sourceName.text = item?.author
        holder.dateTime.text = item?.publishedAt
        Glide.with(holder.itemView)
            .load(item?.urlToImage)
            .into(holder.image)

        if (onClickListener != null) {
            holder.itemView.setOnClickListener {
                onClickListener?.onItemClick(position, item)
            }

        }

    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    fun changeData(data: List<ArticlesItem?>?) {
        items = data
        notifyDataSetChanged()
    }

    var onClickListener: OnClickListener? = null

    interface OnClickListener {
        fun onItemClick(position: Int, itemView: ArticlesItem?)
    }
}


