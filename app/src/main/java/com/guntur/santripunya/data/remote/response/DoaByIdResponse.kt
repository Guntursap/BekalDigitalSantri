package com.guntur.santripunya.data.remote.response

import com.google.gson.annotations.SerializedName

data class DoaByIdResponse(

	@field:SerializedName("request")
	val request: RequestIdDoa? = null,

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("status")
	val status: Boolean? = null,

	@field:SerializedName("info")
	val info: InfoDoa? = null
)

data class Data(

	@field:SerializedName("indo")
	val indo: String? = null,

	@field:SerializedName("source")
	val source: String? = null,

	@field:SerializedName("judul")
	val judul: String? = null,

	@field:SerializedName("arab")
	val arab: String? = null
)

data class InfoDoa(

	@field:SerializedName("min")
	val min: Int? = null,

	@field:SerializedName("max")
	val max: Int? = null
)

data class RequestIdDoa(

	@field:SerializedName("path")
	val path: String? = null,

	@field:SerializedName("id")
	val id: String? = null
)
