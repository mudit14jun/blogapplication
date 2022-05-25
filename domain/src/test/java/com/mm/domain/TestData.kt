package com.mm.domain

import com.mm.domain.model.Blog
import com.mm.domain.model.Owner

fun getDummyBlog() =
    Blog(
        id = "1",
        image = "",
        likes = 1,
        owner = getDummyOwner(),
        publishDate = "date",
        tags = listOf("x", "y", "z"),
        text = "String"
)

fun getDummyOwner() =
    Owner(
        firstName = "abc",
        id = "1",
        lastName = "xyz",
        picture = "a",
        title = "abc"
)