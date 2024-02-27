package com.example.kazifasta.ui.screens.home

import android.content.Intent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.kazifasta.R
import com.example.kazifasta.data.model.categories
import com.example.kazifasta.database
import com.example.kazifasta.ui.common.CategoryTile
import com.example.kazifasta.ui.common.FreelancerProfileTile
import com.example.kazifasta.ui.common.FreelancerProfileTile_2
import com.example.kazifasta.ui.common.SearchBar
import com.example.kazifasta.ui.screens.category.CategoryDetailsActivity
import com.example.kazifasta.ui.screens.profile.ProfileActivity
import com.example.kazifasta.ui.screens.search.SearchActivity
import com.example.kazifasta.ui.theme.green
import com.example.kazifasta.ui.theme.mateWhite
import com.example.kazifasta.ui.utils.Dimens

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(navController: NavController){

    val context = LocalContext.current
    val currentUser = database.profilesList.last()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
        rememberTopAppBarState()
    )

    Scaffold(

        containerColor = green,

        topBar = {
            TopAppBar(
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.topAppBarColors(containerColor = green),
                title = {
                    Text(
                        text = "KaziFasta",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = mateWhite,)
                },
            )

        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        content = { innerPadding ->
            CompositionLocalProvider(
                LocalOverscrollConfiguration provides null
            ) {
                LazyColumn(modifier = Modifier
                    .padding(innerPadding)
                    .background(mateWhite)) {
                    item {
                        //             Top Section
                        Column(
                            modifier = Modifier
                                .background(color = green)
                                .padding(horizontal = Dimens.normalPadding),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Spacer(modifier = Modifier.height(8.dp))
                            //        Hero Title
                            Text(
                                "${stringResource(id = R.string.greeting)}, ${currentUser.firstName}",
                                color = mateWhite,
                            )
                            Text(
                                "${stringResource(id = R.string.title_hero)}\n${
                                    stringResource(
                                        id = R.string.title_hero_2
                                    )
                                }",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = mateWhite,
                                lineHeight = 25.sp

                            )
                            //            Search Bar
                            val navigateToSearch =
                                Intent(context, SearchActivity::class.java)
                            SearchBar(
                                onClick = { context.startActivity(navigateToSearch) },
                                enableInput = false
                            )
                            //            Popular Categories
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    stringResource(id = R.string.title_popular_categories),
                                    color = mateWhite,
                                )
                            }
                            LazyRow(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = Dimens.normalPadding),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                items(categories.take(4)) { category ->
                                    val navigateToCategory = Intent(
                                        context,
                                        CategoryDetailsActivity::class.java
                                    ).putExtra("Category", category)

                                    CategoryTile(
                                        image = category.image,
                                        title = category.title,
                                        onClick = { context.startActivity(navigateToCategory) })
                                }
                            }
                            Spacer(modifier = Modifier.height(12.dp))
                        }
                        //        Bottom Section
                        Column {
                            Spacer(modifier = Modifier.height(20.dp))
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = Dimens.normalPadding),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    "Top Rated",
                                    color = green,
                                    fontWeight = FontWeight.Bold
                                )
                                Icon(
                                    modifier = Modifier.clickable { database.fetchProfiles() },
                                    painter = painterResource(id = R.drawable.right_arrow_circle),
                                    contentDescription = null,
                                    tint = green
                                )
                            }
                            Spacer(modifier = Modifier.height(Dimens.smallPadding))
                            //            Top Rated Freelancers
                            LazyRow(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(20.dp)
                            ) {
                                items(database.profilesList) { profile ->
                                    val navigateToProfile = Intent(
                                        context,
                                        ProfileActivity::class.java
                                    ).putExtra("Profile", profile)
                                    when (profile) {
                                        database.profilesList.first() -> {
                                            FreelancerProfileTile(
                                                profile = profile,
                                                paddingStart = Dimens.normalPadding,
                                                onClick = { context.startActivity(navigateToProfile) }
                                            )
                                        }

                                        database.profilesList.last() -> {
                                            FreelancerProfileTile(
                                                profile = profile,
                                                paddingEnd = Dimens.normalPadding,
                                                onClick = { context.startActivity(navigateToProfile) }
                                            )
                                        }

                                        else -> {
                                            FreelancerProfileTile(
                                                profile = profile,
                                                onClick = { context.startActivity(navigateToProfile) }
                                            )
                                        }
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(20.dp))
                            //        All Freelancers
                            Column(modifier = Modifier.padding(horizontal = Dimens.normalPadding)) {
                                Text(
                                    "All Freelancers",
                                    fontWeight = FontWeight.Bold,
                                    color = green
                                )
                                Spacer(modifier = Modifier.height(15.dp))

                            }
                        }
                    }
                    items(database.profilesList) { profile ->
                        val navigateToProfile = Intent(
                            context,
                            ProfileActivity::class.java
                        ).putExtra("Profile", profile)
                        Box(
                            modifier = Modifier
                                .padding(
                                    horizontal = Dimens.smallPadding,
                                    vertical = Dimens.smallPadding
                                ) // Add padding here
                        ) {
                            FreelancerProfileTile_2(
                                profile = profile,
                                onClick = { context.startActivity(navigateToProfile) }
                            )
                        }

                    }
                    item {
                        Column(modifier = Modifier.padding(innerPadding)) {
                        }
                    }
                }
            }

        }
    )
}