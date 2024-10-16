package com.guntur.santripunya.data.remote.response

import com.google.gson.annotations.SerializedName

data class HusnaResponse(

	@field:SerializedName("request")
	val request: RequestHusna? = null,

	@field:SerializedName("data")
	val data: List<HusnaItem?>? = null,

	@field:SerializedName("status")
	val status: Boolean? = null,

	@field:SerializedName("info")
	val info: InfoHusna? = null
)

data class HusnaItem(

	@field:SerializedName("indo")
	val indo: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("latin")
	val latin: String? = null,

	@field:SerializedName("arab")
	val arab: String? = null
)

data class RequestHusna(

	@field:SerializedName("path")
	val path: String? = null
)

data class InfoHusna(

	@field:SerializedName("min")
	val min: Int? = null,

	@field:SerializedName("max")
	val max: Int? = null
)
