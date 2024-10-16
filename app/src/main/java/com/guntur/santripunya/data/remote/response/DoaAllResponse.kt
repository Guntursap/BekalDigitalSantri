package com.guntur.santripunya.data.remote.response

import com.google.gson.annotations.SerializedName

data class DoaAllResponse(

	@field:SerializedName("request")
	val request: Request? = null,

	@field:SerializedName("data")
	val data: List<DoaItem?>? = null,

	@field:SerializedName("status")
	val status: Boolean? = null,

	@field:SerializedName("info")
	val info: Info? = null
)

data class Info(

	@field:SerializedName("min")
	val min: Int? = null,

	@field:SerializedName("max")
	val max: Int? = null
)

data class Request(

	@field:SerializedName("path")
	val path: String? = null
)

data class DoaItem(

	@field:SerializedName("indo")
	val indo: String? = null,

	@field:SerializedName("source")
	val source: String? = null,

	@field:SerializedName("judul")
	val judul: String? = null,

	@field:SerializedName("arab")
	val arab: String? = null
)
