package com.guido.liverpooltest.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.guido.liverpooltest.data.Products
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class ProductsRepository @Inject constructor(val productsPaggingSource: ProductsPaggingSource){

    fun getProducts(productName: String) : Flow<PagingData<Products>> {
        productsPaggingSource.productName = productName
        return Pager(
            config = PagingConfig(pageSize = 20, prefetchDistance = 3),
            pagingSourceFactory = {productsPaggingSource}
        ).flow
    }

}