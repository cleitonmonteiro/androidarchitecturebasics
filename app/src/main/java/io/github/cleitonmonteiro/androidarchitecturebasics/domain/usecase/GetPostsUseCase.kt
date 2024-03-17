package io.github.cleitonmonteiro.androidarchitecturebasics.domain.usecase

import io.github.cleitonmonteiro.androidarchitecturebasics.data.model.Post
import io.github.cleitonmonteiro.androidarchitecturebasics.data.repository.PostsRepository
import kotlinx.coroutines.flow.Flow

class GetPostsUseCase(
    private val postsRepository: PostsRepository
) {

    operator fun invoke(): Flow<List<Post>> = postsRepository.fetchAll()

}
