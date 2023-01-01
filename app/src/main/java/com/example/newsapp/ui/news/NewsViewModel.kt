package com.example.newsapp.ui.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.Constants
import com.example.newsapp.database.MyDataBase
import com.example.newsapp.model.*
import com.example.newsapp.repos.news.NewsOnlineDataSource
import com.example.newsapp.repos.news.NewsOnlineDataSourceImpl
import com.example.newsapp.repos.news.NewsRepository
import com.example.newsapp.repos.news.NewsRepositoryImpl
import com.example.newsapp.repos.sources.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NewsViewModel @Inject constructor(
    val newsRepository: NewsRepository,
    val sourcesRepository: SourcesRepository,
) : ViewModel() {

    val sourcesLiveData = MutableLiveData<List<SourcesItem?>?>()
    val newsLiveData = MutableLiveData<List<ArticlesItem?>?>()
    val progressVisible = MutableLiveData<Boolean>()
    val massageLiveData = MutableLiveData<String>()


    fun getNewsSources(category: Category) {

        viewModelScope.launch {
            try {
                progressVisible.value = true
                val sources = sourcesRepository.getSources(category.id)
                progressVisible.value = false
                sourcesLiveData.value = sources
            } catch (ex: java.lang.Exception) {
                progressVisible.value = false
                massageLiveData.value = ex.localizedMessage
            }
        }

//        ApiManager.getApi()
//            .getSources(Constants.apiKey, category.id)
//            .enqueue(object : Callback<SourcesResponse> {
//                override fun onResponse(
//                    call: Call<SourcesResponse>,
//                    response: Response<SourcesResponse>
//                ) {
////                    progressBar.isVisible = false
////                    addSourcesToTabLayout(response.body()?.sources)
//                    progressVisible.value = false
//                    sourcesLiveData.value = response.body()?.sources
//                    Log.e("data", response.body().toString())
//                }
//
//                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
//
//                    progressVisible.value = false
//                    massageLiveData.value = t.localizedMessage
//
////                    t.localizedMessage?.let {
////                        t.localizedMessage?.let { it1 ->
////                            Log.e(
////                                "error",
////                                it1
////                            )
////                        }
////                    }
//                }
//
//            })
    }

    fun getNewsBySource(source: SourcesItem) {
//        progressBar.isVisible = false

        viewModelScope.launch {
            try {
                progressVisible.value = true
                val newsResponse = newsRepository.getNew(source.id)
                progressVisible.value = false
                newsLiveData.value = newsResponse
            } catch (ex: Exception) {
                progressVisible.value = false
                massageLiveData.value = ex.localizedMessage
            }
        }

//        progressVisible.value = true
//        ApiManager.getApi()
//            .getNews(Constants.apiKey, source.id ?: "")
//            .enqueue(object : Callback<NewsResponse> {
//                override fun onResponse(
//                    call: Call<NewsResponse>,
//                    response: Response<NewsResponse>
//                ) {
//                    progressVisible.value = false
//                    newsLiveData.value = response.body()?.articles
////                    progressBar.isVisible = false
////                    adapter.changeData(response.body()?.articles)
//                }
//
//                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
////                    progressBar.isVisible = false
//                    progressVisible.value = false
//                    massageLiveData.value = t.localizedMessage
//                }
//
//
//            })
    }
}