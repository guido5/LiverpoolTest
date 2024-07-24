package com.guido.liverpooltest.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.guido.liverpooltest.data.Products
import com.guido.liverpooltest.data.repositories.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val productsRepository: ProductsRepository) : ViewModel() {

    private val _searchedItems : MutableStateFlow<PagingData<Products>> = MutableStateFlow(PagingData.empty())
    val searchedItems = _searchedItems.asStateFlow()

    fun getProducts(productName : String) {
        viewModelScope.launch {
            productsRepository.getProducts(productName)
                .cachedIn(viewModelScope)
                .collect {
                    _searchedItems.value = it
                }
        }
    }
}