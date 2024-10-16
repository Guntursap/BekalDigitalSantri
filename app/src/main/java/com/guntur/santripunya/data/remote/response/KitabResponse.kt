package com.guntur.santripunya.data.remote.response

import com.google.gson.annotations.SerializedName

data class KitabResponse(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null
)

data class DataItem(

	@field:SerializedName("author")
	val author: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("content")
	val content: String? = null
)
