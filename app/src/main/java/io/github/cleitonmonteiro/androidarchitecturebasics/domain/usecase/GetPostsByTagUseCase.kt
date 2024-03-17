package io.github.cleitonmonteiro.androidarchitecturebasics.domain.usecase

import io.github.cleitonmonteiro.androidarchitecturebasics.core.dispatcher.DispatcherProvider
import io.github.cleitonmonteiro.androidarchitecturebasics.data.model.Post
import io.github.cleitonmonteiro.androidarchitecturebasics.data.repository.PostsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class GetPostsByTagUseCase(
    private val postsRepository: PostsRepository,
    private val dispatcherProvider: DispatcherProvider
) {

    operator fun invoke(tags: List<String>): Flow<List<Post>> {
        return postsRepository.fetchAll()
            .map { posts ->
                posts.filter { it.tag in tags }
            }.flowOn(dispatcherProvider.io)
    }

}
