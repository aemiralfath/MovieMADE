package com.aemiralfath.core.utils

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtils {
    const val NEWEST = "NEWEST"
    const val OLDEST = "OLDEST"
    const val CHARACTER = "CHARACTER"

    fun getSortedQuery(query: String, filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM movie ")
        simpleQuery.append("WHERE title LIKE '%$query%' ")

        when (filter) {
            NEWEST -> {
                simpleQuery.append("ORDER BY releaseDate DESC")
            }
            OLDEST -> {
                simpleQuery.append("ORDER BY releaseDate ASC")
            }
            CHARACTER -> {
                simpleQuery.append("ORDER BY title ASC")
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}