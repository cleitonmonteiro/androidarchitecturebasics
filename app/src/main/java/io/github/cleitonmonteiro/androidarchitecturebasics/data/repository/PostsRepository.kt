package io.github.cleitonmonteiro.androidarchitecturebasics.data.repository

import io.github.cleitonmonteiro.androidarchitecturebasics.data.model.Post
import kotlinx.coroutines.flow.Flow

interface PostsRepository {
    fun fetchAll(): Flow<List<Post>>
}
