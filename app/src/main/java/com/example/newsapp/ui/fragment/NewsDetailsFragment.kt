package com.example.newsapp.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.newsapp.R

class NewsDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        progressBar.isVisible = false
    }

    lateinit var progressBar: ProgressBar
    lateinit var imageView: ImageView
    lateinit var sourceName: TextView
    lateinit var title: TextView
    lateinit var data: TextView
    lateinit var description: TextView

    fun initView() {
        progressBar = requireView().findViewById(R.id.progress_view)
        imageView = requireView().findViewById(R.id.image)
        sourceName = requireView().findViewById(R.id.source_name)
        title = requireView().findViewById(R.id.title)
        data = requireView().findViewById(R.id.date_time)
        description = requireView().findViewById(R.id.description)
        getdata()
    }

    private fun getdata() {
        val args = this.arguments
        val title = args?.get("title")
        val source = args?.get("sourceName")
        val date = args?.get("datetime")
        val desc = args?.get("description")
        val image = args?.get("image")
        this.title.text = title.toString()
        this.sourceName.text = source.toString()
        this.data.text = date.toString()
        this.description.text = desc.toString()
        Glide.with(imageView)
            .load(image)
            .into(imageView)
    }
}