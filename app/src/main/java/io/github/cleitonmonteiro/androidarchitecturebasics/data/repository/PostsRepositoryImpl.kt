package io.github.cleitonmonteiro.androidarchitecturebasics.data.repository

import io.github.cleitonmonteiro.androidarchitecturebasics.core.dispatcher.DispatcherProvider
import io.github.cleitonmonteiro.androidarchitecturebasics.data.api.PostsApi
import io.github.cleitonmonteiro.androidarchitecturebasics.data.model.Post
import io.github.cleitonmonteiro.androidarchitecturebasics.data.response.PostResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class PostsRepositoryImpl(
    private val newsApi: PostsApi,
    private val dispatcherProvider: DispatcherProvider
) : PostsRepository {

    override fun fetchAll(): Flow<List<Post>> {
        return flow {
            val response = newsApi.fetchPosts()
            emit(response.map { it.toModel() })
        }.flowOn(dispatcherProvider.io)
    }

    private fun PostResponse.toModel() = Post(id, title, tag)
}
