// PostFragment.kt
package com.example.denoapplication.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.denoapplication.adapter.PostAdapter
import com.example.denoapplication.databinding.FragmentFirstBinding
import com.example.denoapplication.model.PostResponseItem
import com.example.denoapplication.viewmodel.PostViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private var listData: ArrayList<PostResponseItem> = java.util.ArrayList()

    private val viewModel: PostViewModel by viewModels()
    private lateinit var adapter: PostAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = PostAdapter(listData) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter  // attach adapter here

        binding.pbLoader.visibility = View.VISIBLE

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.posts.collectLatest { result ->

                result?.onSuccess { list ->
                    binding.pbLoader.visibility = View.GONE

                    adapter.updateList(list)
                }
                result?.onFailure { throwable ->
                    Toast.makeText(
                        requireContext(),
                        "Error: ${throwable.localizedMessage}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        viewModel.fetchPosts()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
