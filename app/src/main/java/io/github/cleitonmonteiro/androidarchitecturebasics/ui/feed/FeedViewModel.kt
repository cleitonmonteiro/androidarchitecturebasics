package io.github.cleitonmonteiro.androidarchitecturebasics.ui.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.cleitonmonteiro.androidarchitecturebasics.R
import io.github.cleitonmonteiro.androidarchitecturebasics.core.dispatcher.DispatcherProvider
import io.github.cleitonmonteiro.androidarchitecturebasics.domain.usecase.GetPostsByTagUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FeedViewModel(
    private val getPostsByTagUseCase: GetPostsByTagUseCase,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    private val _uiState = MutableStateFlow(FeedUiState())
    val uiState = _uiState.asStateFlow()
    private var selectedTags = MutableStateFlow(mapOf(FRONT_PAGE_TAG to true)).also { selectedTagsFlow ->
        viewModelScope.launch(dispatcherProvider.main) {
            selectedTagsFlow.collect { tags -> getPosts(tags.toCheckedList()) }
        }
    }

    fun errorMessageShow() {
        _uiState.update { it.copy(errorMessageId = null) }
    }

    private fun getPosts(tags: List<String>) {
        viewModelScope.launch(dispatcherProvider.main) {
            getPostsByTagUseCase(tags)
                .onStart {
                    _uiState.update { it.copy(isLoading = true) }
                }
                .catch {
                    _uiState.update { it.copy(errorMessageId = R.string.load_posts_error, isLoading = false) }
                }
                .collect { posts ->
                    _uiState.update { it.copy(posts = posts, isLoading = false) }
                }
        }
    }

    fun onTagClicked(tag: String, isChecked: Boolean) {
        selectedTags.update { it.toMutableMap().apply { put(tag, isChecked) } }
    }

    private fun Map<String, Boolean>.toCheckedList() = filter { it.value }.keys.toList()

    private companion object {
        const val FRONT_PAGE_TAG = "Front Page"
    }

}


