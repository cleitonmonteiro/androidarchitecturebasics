package io.github.cleitonmonteiro.androidarchitecturebasics.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Post(
    val id: Int,
    val title: String,
    val tag: String
): Parcelable
