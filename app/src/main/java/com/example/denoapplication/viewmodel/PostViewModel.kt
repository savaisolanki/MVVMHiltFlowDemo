// PostViewModel.kt
package com.example.denoapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.denoapplication.model.PostResponseItem
import com.example.denoapplication.repository.PostRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val repository: PostRepository
): ViewModel() {

    private val _posts = MutableStateFlow<Result<List<PostResponseItem>>?>(null)
    val posts: StateFlow<Result<List<PostResponseItem>>?> = _posts

    fun fetchPosts() {
        viewModelScope.launch {
            repository.getPostsFlow().collect { result ->
                _posts.value = result
            }
        }
    }
}
