package io.github.cleitonmonteiro.androidarchitecturebasics.ui.feed

import androidx.annotation.StringRes
import io.github.cleitonmonteiro.androidarchitecturebasics.data.model.Post

data class FeedUiState(
    val isLoading: Boolean = false,
    val posts: List<Post> = emptyList(),
    @StringRes val errorMessageId: Int? = null
)
