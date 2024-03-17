package io.github.cleitonmonteiro.androidarchitecturebasics.data.api

import io.github.cleitonmonteiro.androidarchitecturebasics.data.response.PostResponse

interface PostsApi {
    suspend fun fetchPosts(): List<PostResponse>
}
