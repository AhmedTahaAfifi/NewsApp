package com.example.newsapp.ui.news

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentNewsBinding
import com.example.newsapp.model.*
import com.example.newsapp.ui.fragment.NewsDetailsFragment
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.HttpUrl.Companion.toHttpUrl
import javax.inject.Inject

@AndroidEntryPoint
class NewsFragment : Fragment() {

    lateinit var viewDataBinding: FragmentNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
//        return inflater.inflate(R.layout.fragment_news, container, false)
        viewDataBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_news,
            container,
            false
        )
        return viewDataBinding.root
    }


    lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[NewsViewModel::class.java]
    }

    lateinit var layoutManger: LinearLayoutManager
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        subscribeToLiveData()
        viewModel.getNewsSources(category)
    }


    @Inject
    lateinit var adapter: NewsAdapter

    fun initView() {
        viewDataBinding.recyclerView.adapter = adapter
        adapter.onClickListener = object : NewsAdapter.OnClickListener {
            override fun onItemClick(position: Int, itemView: ArticlesItem?) {
                val bundle = Bundle()
                bundle.putString("title", itemView?.title)
                bundle.putString("sourceName", itemView?.author)
                bundle.putString("datetime", itemView?.publishedAt)
                bundle.putString("description", itemView?.description)
                bundle.putString("image", itemView?.urlToImage)
                val fragment = NewsDetailsFragment()
                fragment.arguments = bundle
                fragmentManager?.beginTransaction()?.replace(
                    R.id.fragment_container,
                    fragment
                )?.commit()

            }

        }

    }

    fun subscribeToLiveData() {
        viewModel.sourcesLiveData.observe(
            viewLifecycleOwner
        ) {
            addSourcesToTabLayout(it)
        }
        viewModel.newsLiveData.observe(
            viewLifecycleOwner
        ) {
            showNews(it)
        }
        viewModel.progressVisible.observe(
            viewLifecycleOwner
        ) { isVisible ->
            viewDataBinding.progressView.isVisible = isVisible
        }
        viewModel.massageLiveData.observe(
            viewLifecycleOwner
        ) { message ->
            Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
        }
    }

    private fun showNews(newsList: List<ArticlesItem?>?) {
        adapter.changeData(newsList)
    }

    fun addSourcesToTabLayout(sources: List<SourcesItem?>?) {
        sources?.forEach {
            val tab = viewDataBinding.tabLayout.newTab()
            tab.text = it?.name
            tab.tag = it
            viewDataBinding.tabLayout.addTab(tab)
        }
        viewDataBinding.tabLayout.addOnTabSelectedListener(
            object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    val source = tab?.tag as SourcesItem
                    viewModel.getNewsBySource(source)

//                    getNewsBySource(source)
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                    val source = tab?.tag as SourcesItem
                    viewModel.getNewsBySource(source)
//                    getNewsBySource(source)
                }


            }
        )
        viewDataBinding.tabLayout.getTabAt(0)?.select()
    }

    companion object {
        fun getInstance(category: Category): NewsFragment {
            val fragment = NewsFragment()
            fragment.category = category
            return fragment
        }
    }

    lateinit var category: Category
}

