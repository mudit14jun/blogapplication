package com.mm.blogapplication.screens.details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.mm.blogapplication.screens.base.BaseViewModel
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
        savedStateHandle.getLiveData<String>("blogId").value?.let {
            getBlogDetails(it)
        }
    }

    /*fun getBlogDetails(id: String) {
        viewModelScope.launch {

            getBlogDetailsUseCase.execute(id).onEach {
              *//*  when (it) {*//*
                  *//*  is Resource.Loading -> {
                        details.value = BlogDetailsStateHolder(isLoading = true)
                    }
                    is Resource.Success -> {*//*
                        details.value = BlogDetailsStateHolder(data = it.data)


                  *//*  }
                    is Resource.Error -> {
                        details.value = BlogDetailsStateHolder(error = it.message.toString())
                    }*//*
               *//* }*//*


            }.launchIn(viewModelScope)
        }

    }*/

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