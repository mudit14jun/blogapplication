package com.mm.blogapplication.screens.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mm.domain.use_cases.BlogsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val blogsUseCase: BlogsUseCase) : ViewModel() {


    val homeState = mutableStateOf(HomeState())

    init {
        getBlogs()
    }

    fun getBlogs() {
        viewModelScope.launch {
            blogsUseCase.execute().collect {
                homeState.value = HomeState(data = it.data)
            }
        }
    }

}