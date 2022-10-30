package com.example.newsapp.ui

import android.os.Bundle
import android.widget.ImageView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.newsapp.R
import com.example.newsapp.model.Category
import com.example.newsapp.ui.categories.CategoryFragment
import com.example.newsapp.ui.news.NewsFragment
import com.google.android.material.navigation.NavigationView
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener

class HomeActivity : AppCompatActivity() {

    val categoryFragment = CategoryFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initView()
        pushFragment(categoryFragment)
        categoryFragment.onCategoryClickListener =
            object : CategoryFragment.OnCategoryClickListener {
                override fun onCategoryClick(category: Category) {
                    pushFragment(NewsFragment.getInstance(category), true)
                }

            }
    }

    lateinit var drawerLayout: DrawerLayout
    lateinit var drawerButton: ImageView
    lateinit var navView: NavigationView

    fun initView() {
        drawerLayout = findViewById(R.id.drawer_layout)
        drawerButton = findViewById(R.id.btn_menu)
        navView = findViewById(R.id.nav_view)
        drawerButton.setOnClickListener {
            drawerLayout.open()
        }

        val mOnNavigationItemSelectedListener = OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.category -> {
                    pushFragment(categoryFragment)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }
        navView.setNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    fun pushFragment(fragment: Fragment, addToBackStack: Boolean = false) {
        val fragmentTransition = supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
        if (addToBackStack)
            fragmentTransition.addToBackStack("")

        fragmentTransition.commit()
        drawerLayout.close()
    }


}