package com.example.newsapp


import android.os.Bundle
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.bumptech.glide.Glide

class NewsDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)
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
        progressBar = findViewById(R.id.progress_view)
        imageView = findViewById(R.id.image)
        sourceName = findViewById(R.id.source_name)
        title = findViewById(R.id.title)
        data = findViewById(R.id.date_time)
        description = findViewById(R.id.description)
        val title = intent.getStringExtra("title")
        val source = intent.getStringExtra("sourceName")
        val date = intent.getStringExtra("datetime")
        val desc = intent.getStringExtra("description")
        val image = intent.getStringExtra("image")
        this.title.text = title
        this.sourceName.text = source
        this.data.text = date
        this.description.text = desc

    }

}