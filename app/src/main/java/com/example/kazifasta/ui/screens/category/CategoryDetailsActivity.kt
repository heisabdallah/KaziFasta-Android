package com.example.kazifasta.ui.screens.category

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.example.kazifasta.data.model.Category
import com.example.kazifasta.database
import com.example.kazifasta.ui.common.AppBar
import com.example.kazifasta.ui.common.FreelancerProfileTile
import com.example.kazifasta.ui.screens.profile.ProfileActivity
import com.example.kazifasta.ui.theme.KaziFastaTheme
import com.example.kazifasta.ui.theme.mateWhite
import com.example.kazifasta.ui.utils.Dimens.bigPadding
import com.example.kazifasta.ui.utils.Dimens.smallPadding

class CategoryDetailsActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge(SystemBarStyle.dark(1))

        super.onCreate(savedInstanceState)

        setContent {
            KaziFastaTheme {

                val category = intent.getParcelableExtra<Category>("Category")

                val filteredProfiles = database.profilesList.filter { profile -> profile.category == category?.title }.toMutableList()


                if (category != null) {
                    Scaffold(
                        containerColor = mateWhite,
                        topBar = {
                            AppBar(onClick = { super.onBackPressed() }, title = category.title)
//                            TopAppBar(
//                                modifier = Modifier.padding(top = 100.dp),
//                                scrollBehavior = scrollBehavior,
//                                title = {
//                                    Box(modifier = Modifier.fillMaxWidth(),contentAlignment = Alignment.BottomStart) {
//                                        Image(
//                                            modifier = Modifier
//                                                .fillMaxWidth()
//                                                .height(230.dp),
//                                            painter = painterResource(id = category.image),
//                                            contentDescription = "Category Image",
//                                            contentScale = ContentScale.Crop
//                                        )
//                                        Text(
//                                            text = category.title,
//                                            fontSize = 40.sp,
//                                            fontWeight = FontWeight.Bold,
//                                            color = Color.White,
//                                            modifier = Modifier.padding(
//                                                start =
//                                                normalPadding
//                                            )
//                                        )
//                                    }
//                                }
//                            )

                        },
                        content = { innerPadding ->

                            LazyVerticalGrid(
                                modifier = Modifier
                                    .padding(innerPadding).padding(horizontal = smallPadding),
                                columns = GridCells.Fixed(2),
//                                verticalArrangement = Arrangement.spacedBy(normalPadding),
                                horizontalArrangement = Arrangement.spacedBy(bigPadding),
//                                userScrollEnabled = false
                            ) {
                                items(filteredProfiles) { profile ->
                                    FreelancerProfileTile(
                                        paddingTop = smallPadding,
                                        profile = profile,
                                        onClick = {
                                            val navigateToProfile = Intent(
                                                this@CategoryDetailsActivity,
                                                ProfileActivity::class.java
                                            ).putExtra("Profile", profile)
                                            startActivity(navigateToProfile)
                                        })
//                                        }

                                }
                            }
                        }
                    )
                }

            }
        }
    }
}
