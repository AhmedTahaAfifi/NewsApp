package com.example.newsapp.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.model.Category

class CategoryFragment : Fragment() {

    val categoryList = listOf(
        Category(
            "sports", R.string.sports,
            R.drawable.ic_sports, R.color.black
        ),
        Category(
            "technology", R.string.technology,
            R.drawable.ic_technology, R.color.black
        ),
        Category(
            "health", R.string.health,
            R.drawable.ic_health, R.color.black
        ),
        Category(
            "business", R.string.business,
            R.drawable.ic_bussines, R.color.black
        ),
        Category(
            "general", R.string.general,
            R.drawable.ic_general, R.color.black
        ),
        Category(
            "science", R.string.science,
            R.drawable.ic_science, R.color.black
        )
    )
    val adapter = CategoryAdapter(categoryList)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    lateinit var recyclerView: RecyclerView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.adapter = adapter
        adapter.onItemClickListener = object : CategoryAdapter.OnItemClickListener {
            override fun onItemClick(pos: Int, category: Category) {
                onCategoryClickListener?.onCategoryClick(category)
            }

        }
    }

    var onCategoryClickListener: OnCategoryClickListener? = null

    interface OnCategoryClickListener {
        fun onCategoryClick(category: Category)
    }
}