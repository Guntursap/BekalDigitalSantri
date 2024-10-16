package com.guntur.santripunya.data.remote.response

import com.google.gson.annotations.SerializedName

data class SuratAllResponse(

	@field:SerializedName("request")
	val request: SuratAllRequest? = null,

	@field:SerializedName("data")
	val data: List<SuratAllItem?>? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)

data class SuratAllRequest(

	@field:SerializedName("path")
	val path: String? = null
)

data class SuratAllItem(

	@field:SerializedName("translation_id")
	val translationId: String? = null,

	@field:SerializedName("revelation")
	val revelation: String? = null,

	@field:SerializedName("name_id")
	val nameId: String? = null,

	@field:SerializedName("revelation_en")
	val revelationEn: String? = null,

	@field:SerializedName("number")
	val number: String? = null,

	@field:SerializedName("sequence")
	val sequence: String? = null,

	@field:SerializedName("name_long")
	val nameLong: String? = null,

	@field:SerializedName("audio_url")
	val audioUrl: String? = null,

	@field:SerializedName("tafsir")
	val tafsir: String? = null,

	@field:SerializedName("name_short")
	val nameShort: String? = null,

	@field:SerializedName("revelation_id")
	val revelationId: String? = null,

	@field:SerializedName("translation_en")
	val translationEn: String? = null,

	@field:SerializedName("name_en")
	val nameEn: String? = null,

	@field:SerializedName("number_of_verses")
	val numberOfVerses: String? = null
)
