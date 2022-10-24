package com.example.newsapp.model

import java.util.logging.Filter

data class AppState(
    val categoryFilterInfo: CategoryFilterInfo = CategoryFilterInfo()
) {
    data class CategoryFilterInfo(
        val filters: Set<Filter> = emptySet(),
        val selectedFilter: Filter? = null
    )
}
