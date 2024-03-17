package io.github.cleitonmonteiro.androidarchitecturebasics.data.api

import io.github.cleitonmonteiro.androidarchitecturebasics.data.response.PostResponse
import kotlinx.coroutines.delay

class PostsApiImpl : PostsApi {
    override suspend fun fetchPosts(): List<PostResponse> {
        delay(REQUEST_DELAY)
        return List(POSTS_SIZE) { index ->
            PostResponse(
                id = index,
                title = "Guide to app architecture part ${index + 1}",
                tag = makeTag(index)
            )
        }
    }

    private fun makeTag(index: Int): String {
        return when (index % 3) {
            0 -> "Front Page"
            1 -> "Story"
            else -> "New"
        }
    }

    private companion object {
        const val REQUEST_DELAY = 700L
        const val POSTS_SIZE = 30
    }
}
