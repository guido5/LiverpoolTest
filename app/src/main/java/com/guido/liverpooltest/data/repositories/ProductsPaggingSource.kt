package com.guido.liverpooltest.data.repositories

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.guido.liverpooltest.data.Products
import com.guido.liverpooltest.data.retrofit.configuration.ApiServices
import com.guido.liverpooltest.data.retrofit.dto.toDomain
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class ProductsPaggingSource @Inject constructor(val services: ApiServices) : PagingSource<Int, Products>(){

    lateinit var productName: String
    private val STARTING_KEY = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Products> {
        val start = params.key ?: STARTING_KEY
        return try {
            val response = services.getProducts(searchString = productName,
                pageNumber = start,
                minSortPrice = null)
            val productList = response.toDomain()
            LoadResult.Page(
                data = productList,
                prevKey = if(start == STARTING_KEY) null else start -1,
                nextKey = if(response.plpResults.plpState.lastRecNum >= response.plpResults.plpState.totalNumRecs) null else start + 1       //Se toma 10055 como limite por que despues de eso, aunque pida mas paginas ya no devuelve mas informacion.
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Products>): Int? {
       return state.anchorPosition
    }
}