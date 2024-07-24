package com.guido.liverpooltest.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.guido.liverpooltest.data.Products
import com.guido.liverpooltest.data.retrofit.dto.VariantsColor

@Preview
@Composable
fun PreviewCompo() {
    val product = Products("nombre", 2.5, 50.0,
        image = "imagen1",
        listOf(VariantsColor("color1", "#581e2a"), VariantsColor("color1", "#581e2a")))
    ProductSearchedItem(product)
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProductSearchedItem(products: Products) {

        Card(modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .padding(8.dp),
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()) {
                Box(
                    modifier = Modifier
                        .width(100.dp)
                        .fillMaxHeight()
                        .padding(8.dp),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    GlideImage(
                        model = products.image,
                        contentDescription = "",
                        modifier = Modifier.fillMaxSize(),
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {
                    Text(
                        text = products.name,
                        Modifier
                            .fillMaxWidth()
                            .height(30.dp)
                            .padding(2.dp),
                        textAlign = TextAlign.Justify,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        text = products.listPrice.toString(),
                        Modifier
                            .fillMaxWidth()
                            .height(24.dp)
                            .padding(2.dp),
                        textAlign = TextAlign.Justify,
                        textDecoration = TextDecoration.LineThrough,
                        fontSize = 12.sp
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = products.promoPrice.toString(),
                        Modifier
                            .fillMaxWidth()
                            .height(24.dp)
                            .padding(2.dp),
                        color = Color.Red,
                        textAlign = TextAlign.Justify,
                        fontSize = 12.sp
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Row {
                        products.variantsColor?.forEach {
                            if(it.colorHex.isNotEmpty()) {
                                Box(
                                    Modifier
                                        .width(15.dp)
                                        .height(15.dp)
                                        .background(
                                            color = Color.fromHex(it.colorHex),
                                            CircleShape
                                        ),
                                ) {}
                                Spacer(modifier = Modifier.height(4.dp))
                            }
                        }
                    }
                }
            }
        }


}

fun Color.Companion.fromHex(colorString: String) = Color(android.graphics.Color.parseColor(colorString))