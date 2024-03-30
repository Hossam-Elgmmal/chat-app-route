package com.route.chat.model

import com.route.chat.R

data class Category(
    val id: String = MOVIES,
    val name: String = MOVIES,
    val imageId: Int = R.drawable.img_movies
) {
    companion object {
        private const val MOVIES = "Movies"
        private const val MUSIC = "Music"
        private const val SPORTS = "Sports"
        fun getCategories(): List<Category> {
            return listOf(
                fromId(MOVIES),
                fromId(MUSIC),
                fromId(SPORTS)
            )
        }

        private fun fromId(id: String): Category {
            return when (id) {
                MOVIES -> Category()
                MUSIC -> Category(MUSIC, MUSIC, R.drawable.img_music)
                SPORTS -> Category(SPORTS, SPORTS, R.drawable.img_sports)
                else -> Category()
            }
        }

    }
}
