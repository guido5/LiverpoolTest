package com.guido.liverpooltest.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchView(mainViewModel: MainViewModel) {
    var query by remember { mutableStateOf("") }
    var enabled by remember { mutableStateOf(false) }
    val items = mainViewModel.searchedItems.collectAsLazyPagingItems()
    Column(Modifier.fillMaxSize()) {
        SearchBar(
            query = query,
            onQueryChange = {
                query = it
                enabled = it.isNotEmpty()
            },
            onSearch = {
                validateQuery(it, mainViewModel)
            },
            active = false,
            onActiveChange = {},
            trailingIcon = {
                IconButton(onClick = {
                    validateQuery(query, mainViewModel)
                }, enabled = enabled) {
                    Icon(Icons.Filled.Search, contentDescription = "searchIsActive")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
        ) {}

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxHeight()
                .weight(15f)
                .padding(15.dp)
        ) {

            items(items.itemCount) { index ->
                println(index)
                items[index]?.let { products ->
                    ProductSearchedItem(products = products)
                }
            }
        }
    }
}

fun validateQuery(string: String, viewModel: MainViewModel) {
    if(string.isNotEmpty()) {
        viewModel.getProducts(string)
    }
}