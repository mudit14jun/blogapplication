package com.mm.blogapplication.screens.home

import com.mm.domain.model.Blog

data class HomeState(
    var isLoading:Boolean=false,
    var data:List<Blog>?=null,
    var error:String=""
)