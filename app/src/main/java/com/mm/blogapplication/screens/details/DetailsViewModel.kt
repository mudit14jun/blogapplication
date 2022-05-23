package com.mm.blogapplication.screens.details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.mm.blogapplication.screens.base.BaseViewModel
import com.mm.common.Resource
import com.mm.domain.use_cases.GetBlogDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getBlogDetailsUseCase: GetBlogDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel() {


    val details = mutableStateOf(BlogDetailsStateHolder())

    init {
        savedStateHandle.getLiveData<String>("blogId").value?.let {
            getBlogDetails(it)
        }
    }

    fun getBlogDetails(id: String) {
        getBlogDetailsUseCase(id).onEach {
            when (it) {
                is Resource.Loading -> {
                    details.value = BlogDetailsStateHolder(isLoading = true)
                }
                is Resource.Success -> {
                    details.value = BlogDetailsStateHolder(data = it.data)
                }
                is Resource.Error -> {
                    details.value = BlogDetailsStateHolder(error = it.message.toString())
                }
            }


        }.launchIn(viewModelScope)
    }


}