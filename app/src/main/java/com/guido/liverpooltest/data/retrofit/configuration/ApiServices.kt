package com.guido.liverpooltest.data.retrofit.configuration

import com.guido.liverpooltest.data.retrofit.dto.ProductsDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("plp")
    suspend fun getProducts(@Query("search-string") searchString: String,
                            @Query("page-number") pageNumber: Int,
                            @Query("minSortPrice") minSortPrice: Int?) : ProductsDTO

}
