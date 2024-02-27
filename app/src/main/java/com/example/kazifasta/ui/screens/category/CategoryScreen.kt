package com.example.kazifasta.ui.screens.category

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.kazifasta.data.model.categories
import com.example.kazifasta.ui.common.AppBar
import com.example.kazifasta.ui.common.CategoryTile_2
import com.example.kazifasta.ui.theme.mateBlack
import com.example.kazifasta.ui.theme.mateWhite
import com.example.kazifasta.ui.utils.Dimens

@Composable
fun CategoryScreen(){
    val context = LocalContext.current
    Scaffold (
        containerColor = mateWhite,
        topBar = {
            AppBar(onClick = { /*TODO*/ }, title = "All Categories", backButton = false)
        },
        content = { innerPadding ->
            LazyVerticalGrid(
                modifier = Modifier.padding(innerPadding).padding(top = Dimens.normalPadding),
                columns = GridCells.Fixed(count = 4),
                verticalArrangement = Arrangement.spacedBy(
                    Dimens.normalPadding
                )
            ) {
                items(categories) { category ->
                    val navigateToCategory = Intent(
                        context,
                        CategoryDetailsActivity::class.java
                    ).putExtra("Category", category)
                    CategoryTile_2(
                        image = category.image,
                        title = category.title,
                        titleColor = mateBlack,
                        onClick = { context.startActivity(navigateToCategory) }
                    )
                }
            }
        }
    )
}
