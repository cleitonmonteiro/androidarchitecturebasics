package io.github.cleitonmonteiro.androidarchitecturebasics.core.dispatcher

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {

    val io: CoroutineDispatcher

    val main: CoroutineDispatcher

}
