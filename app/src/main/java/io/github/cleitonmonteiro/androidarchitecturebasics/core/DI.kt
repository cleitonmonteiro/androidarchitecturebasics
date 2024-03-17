package io.github.cleitonmonteiro.androidarchitecturebasics.core

import io.github.cleitonmonteiro.androidarchitecturebasics.data.api.PostsApi
import io.github.cleitonmonteiro.androidarchitecturebasics.data.api.PostsApiImpl
import io.github.cleitonmonteiro.androidarchitecturebasics.data.repository.PostsRepository
import io.github.cleitonmonteiro.androidarchitecturebasics.data.repository.PostsRepositoryImpl
import io.github.cleitonmonteiro.androidarchitecturebasics.domain.usecase.GetPostsByTagUseCase
import io.github.cleitonmonteiro.androidarchitecturebasics.domain.usecase.GetPostsUseCase
import io.github.cleitonmonteiro.androidarchitecturebasics.core.dispatcher.DispatcherProvider
import io.github.cleitonmonteiro.androidarchitecturebasics.core.dispatcher.DispatcherProviderImpl
import io.github.cleitonmonteiro.androidarchitecturebasics.ui.feed.FeedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<PostsApi> { PostsApiImpl() }
    single<DispatcherProvider> { DispatcherProviderImpl() }
    factory<PostsRepository> {
        PostsRepositoryImpl(
            newsApi = get(),
            dispatcherProvider = get()
        )
    }
    factory { GetPostsUseCase(postsRepository = get()) }
    factory {
        GetPostsByTagUseCase(
            postsRepository = get(),
            dispatcherProvider = get()
        )
    }
    viewModel {
        FeedViewModel(
            getPostsByTagUseCase = get(),
            dispatcherProvider = get()
        )
    }
}
