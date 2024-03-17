package io.github.cleitonmonteiro.androidarchitecturebasics.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import io.github.cleitonmonteiro.androidarchitecturebasics.databinding.FragmentPostDetailBinding

class PostDetailFragment : Fragment() {

    private var _binding: FragmentPostDetailBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<PostDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() = binding.postItem.run {
        postTitle.text = args.post.title
        postTag.text = args.post.tag
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
