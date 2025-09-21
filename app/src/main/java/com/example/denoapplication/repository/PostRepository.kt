// PostRepository.kt
package com.example.denoapplication.repository


import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.example.denoapplication.dao.PostDao
import com.example.denoapplication.model.PostResponseItem
import com.example.denoapplication.network.ApiInterface
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val api: ApiInterface,
    private var dao: PostDao,
    @ApplicationContext private val context: Context
) {
    fun getPostsFlow(): Flow<Result<List<PostResponseItem>>> = flow {
        if (isNetworkAvailable(context)) {
            try {
                val response = api.getPosts()
                if (response.isSuccessful) {
                    val posts = response.body() ?: emptyList()
                    dao.insertPosts(posts)               // ✅ Save to Room
                    emit(Result.success(posts))        // ✅ Emit API data
                } else {
                    emit(Result.failure(Throwable("API Error ${response.code()}")))
                }
            } catch (e: Exception) {
                val cached = dao.getAllPosts()
                emit(Result.success(cached))           // 🔄 Fallback to Room
            }
        } else {
            val cached = dao.getAllPosts()
            emit(Result.success(cached))               // ❌ No internet → Room
        }
    }.flowOn(Dispatchers.IO)


    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }



}
