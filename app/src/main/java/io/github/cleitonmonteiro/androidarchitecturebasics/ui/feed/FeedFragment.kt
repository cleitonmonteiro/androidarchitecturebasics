package io.github.cleitonmonteiro.androidarchitecturebasics.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.core.view.children
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.google.android.material.chip.Chip
import com.google.android.material.snackbar.Snackbar
import io.github.cleitonmonteiro.androidarchitecturebasics.data.model.Post
import io.github.cleitonmonteiro.androidarchitecturebasics.databinding.FragmentFeedBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FeedFragment : Fragment() {

    private var _binding: FragmentFeedBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<FeedViewModel>()
    private val postsAdapter by lazy { PostsAdapter(::onPostClick) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFeedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupObservers()
    }

    private fun setupView() = binding.run {
        postList.adapter = postsAdapter
        postsTags.children.forEach {
            (it as Chip).setOnCheckedChangeListener { chip, isChecked ->
                viewModel.onTagClicked(chip.text.toString(), isChecked)
            }
        }
    }

    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    uiState.errorMessageId?.let { showErrorMessage(it) }
                    showLoading(uiState.isLoading)
                    if (uiState.isLoading) {
                        return@collect
                    }
                    showPosts(uiState.posts)
                }
            }
        }
    }

    private fun showErrorMessage(@StringRes messageId: Int) {
        Snackbar.make(binding.root, messageId, Snackbar.LENGTH_SHORT).show()
        viewModel.errorMessageShow()
    }

    private fun showPosts(posts: List<Post>) {
        if (posts.isEmpty()) {
            showNoPostFeedback()
        }
        postsAdapter.submitList(posts)
    }

    private fun showNoPostFeedback(isVisible: Boolean = true) = binding.run {
        noPostText.isVisible = isVisible
    }

    private fun showLoading(isLoading: Boolean = true) = binding.run {
        loadingIndicator.isVisible = isLoading
        mainContentGroup.isVisible = !isLoading
        showNoPostFeedback(false)
    }


    private fun onPostClick(post: Post) {
        findNavController().navigate(FeedFragmentDirections.actionFeedFragmentToPostDetailFragment(post))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
