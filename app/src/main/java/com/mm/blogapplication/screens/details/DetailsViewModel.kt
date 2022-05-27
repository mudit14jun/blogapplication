package com.mm.blogapplication.screens.details

import com.mm.domain.model.Output
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.mm.blogapplication.screens.base.BaseViewModel
import com.mm.common.Resource
import com.mm.domain.model.Blog
import com.mm.domain.use_cases.BlogDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val blogDetailUseCase: BlogDetailUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    val details = mutableStateOf(BlogDetailsStateHolder())

    init {
        if(savedStateHandle.getLiveData<String>("blogId") != null)
        savedStateHandle.getLiveData<String>("blogId").value?.let {
            getBlogDetails(it)
        }
    }




    /**
     * Method to fetch the blog details data.
     */
    fun getBlogDetails(id: String) {
        viewModelScope.launch {
            blogDetailUseCase.execute(id).collect {
                details.value = BlogDetailsStateHolder(data = it.data)
            }
        }
    }
}