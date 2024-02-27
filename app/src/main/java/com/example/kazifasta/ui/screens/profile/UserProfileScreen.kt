package com.example.kazifasta.ui.screens.profile

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.kazifasta.R
import com.example.kazifasta.data.model.Profile
import com.example.kazifasta.database
import com.example.kazifasta.ui.common.Description
import com.example.kazifasta.ui.common.FreelancerProfileTile_2
import com.example.kazifasta.ui.common.SectionTitle
import com.example.kazifasta.ui.common.Title
import com.example.kazifasta.ui.screens.settings.SettingsActivity
import com.example.kazifasta.ui.theme.green
import com.example.kazifasta.ui.theme.mateWhite
import com.example.kazifasta.ui.theme.primaryTextColor
import com.example.kazifasta.ui.utils.Dimens

@Composable
fun UserProfileScreen(profile: Profile){

    val context = LocalContext.current

    val navigateToSettings = Intent(context, SettingsActivity::class.java)

    Scaffold(
        containerColor = mateWhite,
        topBar = {
            ProfileAppBar(
                onClick = { context.startActivity(navigateToSettings) },
                title = "${profile.title}",
            )
        },
        content = { innerPadding ->
            Box {
                Column(
                    Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    //            Top Section
                    Box {
                        AsyncImage(
                            model = profile.profileImage,
                            contentDescription = "profile Pic",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(450.dp)
                                .fillMaxWidth()
                        )

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = Dimens.smallPadding),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Spacer(modifier = Modifier.height(Dimens.appBarSpacing * 10))
                            Title(text = "${profile.firstName} ${profile.lastName}")
//                            SubTitle(text = profile.title!!)

//                            Text(
//                                "${profile.address!!.street}, ${profile.address.city}",
//                                color = primaryTextColor
//                            )
                            Row {
                                Text(
                                    "⭐\uFE0F ${profile.rating}",
                                    color = primaryTextColor
                                )
                                Spacer(modifier = Modifier.width(20.dp))
                                Text(
                                    "${profile.reviews}+ Reviews",
                                    color = primaryTextColor
                                )
                            }
                        }
                    }
                    //            Bottom Section
                    Column(
                        modifier = Modifier.padding(Dimens.normalPadding),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(Dimens.normalPadding))
                        SectionTitle(title = stringResource(id = R.string.title_profile_about))

                        Description(text = profile.description)
                        Spacer(modifier = Modifier.height(Dimens.normalPadding))
                        SectionTitle(title = stringResource(id = R.string.title_profile_portfolio))
                        LazyRow {
                            items(database.profilesList.reversed()) { profile ->
                                AsyncImage(
                                    model = profile.profileImage,
                                    contentDescription = stringResource(id = R.string.title_profile_portfolio),
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .size(200.dp)
                                        .padding(horizontal = 4.dp)
                                        .clickable {
                                        }
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(Dimens.normalPadding))
                        SectionTitle(title = stringResource(id = R.string.title_profile_reviews))
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(
                                Dimens.smallPadding / 4
                            ), modifier = Modifier.background(
                                green
                            )
                        ) {
                            items(database.profilesList) { profile ->
                                FreelancerProfileTile_2(profile = profile) {

                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(Dimens.normalPadding))
                        SectionTitle(title = stringResource(id = R.string.title_profile_address))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Start
                        ) {
//                            Description(text = profile.address!!.street!!)
                            Description(text = ", ")
//                            Description(text = profile.address.city!!)

                        }

                        Spacer(modifier = Modifier.height(100.dp))


                    }
                }

            }
//                    Box End
        }
    )
}


