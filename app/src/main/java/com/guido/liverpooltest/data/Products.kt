package com.guido.liverpooltest.data

import com.guido.liverpooltest.data.retrofit.dto.VariantsColor

data class Products(val name: String,
                    val listPrice: Double,
                    val promoPrice: Double,
                    val image: String,
                    val variantsColor: List<VariantsColor>?)
