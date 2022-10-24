package com.example.newsapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.adapter.NewsAdapter
import com.example.newsapp.api.ApiManager
import com.example.newsapp.model.ArticlesItem
import com.example.newsapp.model.NewsResponse
import com.example.newsapp.model.SourcesItem
import com.example.newsapp.model.SourcesResponse
import com.google.android.material.tabs.TabLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    lateinit var tabLayout: TabLayout
    lateinit var progressBar: ProgressBar
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        getNewsSources()
    }

    val adapter = NewsAdapter(null)
    fun initViews() {
        tabLayout = findViewById(R.id.tab_layout)
        progressBar = findViewById(R.id.progress_view)
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = adapter
        adapter.onClickListener = object : NewsAdapter.OnClickListener {
            override fun onItemClick(position: Int, itemView: ArticlesItem?) {
                val intent = Intent(this@MainActivity, NewsDetailsActivity::class.java)
                intent.putExtra("title", itemView?.title)
                intent.putExtra("image", itemView?.urlToImage)
                intent.putExtra("sourceName", itemView?.author)
                intent.putExtra("datetime", itemView?.publishedAt)
                intent.putExtra("description", itemView?.description)
                startActivity(intent)
            }

        }


    }

    fun getNewsSources() {
        ApiManager.getApi()
            .getSources(Constants.apiKey)
            .enqueue(object : Callback<SourcesResponse> {
                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                    t.localizedMessage?.let {
                        t.localizedMessage?.let { it1 ->
                            Log.e(
                                "error",
                                it1
                            )
                        }
                    }
                }

                override fun onResponse(
                    call: Call<SourcesResponse>,
                    response: Response<SourcesResponse>
                ) {
                    progressBar.isVisible = false
                    addSourcesToTabLayout(response.body()?.sources)
                    Log.e("data", response.body().toString())
                }
            })

    }

    fun addSourcesToTabLayout(sources: List<SourcesItem?>?) {
        sources?.forEach {
            val tab = tabLayout.newTab()
            tab.text = it?.name
            tab.tag = it
            tabLayout.addTab(tab)
        }
        tabLayout.addOnTabSelectedListener(
            object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    val source = tab?.tag as SourcesItem
                    getNewsBySource(source)
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                    val source = tab?.tag as SourcesItem
                    getNewsBySource(source)
                }

            }
        )
        tabLayout.getTabAt(0)?.select()
    }

    fun getNewsBySource(source: SourcesItem) {
        progressBar.isVisible = true
        ApiManager.getApi()
            .getNews(Constants.apiKey, source.id ?: "")
            .enqueue(object : Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    progressBar.isVisible = false
                    adapter.changeData(response.body()?.articles)
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    progressBar.isVisible = false
                }

            })
    }

}