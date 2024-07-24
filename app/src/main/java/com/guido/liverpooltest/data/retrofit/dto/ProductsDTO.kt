package com.guido.liverpooltest.data.retrofit.dto

import com.guido.liverpooltest.data.Products

data class ProductsDTO(
        val status: Status,
        val pageType: String,
        val plpResults: PlpResults)

data class Status(
    val status: String,
    val statusCode: Long
)

data class PlpResults(
    val plpState: PlpState,
    val records: List<Record>
)

data class PlpState(
    val firstRecNum: Long,
    val lastRecNum: Long,
    val recsPerPage: Long,
    val totalNumRecs: Long,
)

data class Record(
    val productId: String,
    val skuRepositoryId: String,
    val productDisplayName: String,
    val listPrice: Double,
    val promoPrice: Double?,
    val smImage: String?,
    val lgImage: String?,
    val xlImage: String?,
    val variantsColor: List<VariantsColor>?
)

data class VariantsColor(
    val colorName: String,
    val colorHex: String
)

fun ProductsDTO.toDomain() : List<Products> {
    return plpResults.records.map {
        Products(it.productDisplayName,
            it.listPrice,
            it.promoPrice ?: it.listPrice,
            it.smImage ?: "",
            it.variantsColor)
    }
}