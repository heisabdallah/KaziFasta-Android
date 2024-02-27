package com.example.kazifasta.ui.screens.profile

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.kazifasta.R
import com.example.kazifasta.data.model.Profile
import com.example.kazifasta.database
import com.example.kazifasta.ui.common.AppBar
import com.example.kazifasta.ui.common.Description
import com.example.kazifasta.ui.common.FreelancerProfileTile_2
import com.example.kazifasta.ui.common.SectionTitle
import com.example.kazifasta.ui.common.SubTitle
import com.example.kazifasta.ui.common.Title
import com.example.kazifasta.ui.theme.green
import com.example.kazifasta.ui.theme.mateWhite
import com.example.kazifasta.ui.theme.primaryTextColor
import com.example.kazifasta.ui.utils.Dimens

@Composable
fun ProfileScreen(profile: Profile, onBackPressed: () -> Unit){


//    var showFullScreenImage by remember { mutableStateOf(false) }

//    var imageName by remember { mutableStateOf("") }

//    var selectedImage: Int by remember { mutableIntStateOf(0) }

//    fun showFImage(image: AsyncImagePainter, name: String) {
//        showFullScreenImage = true
//        selectedImage = image
//        imageName = name
//    }


    Scaffold(
        containerColor = mateWhite,
        topBar = {
            AppBar(
                onClick = { onBackPressed.invoke() },
                title = stringResource(id = R.string.title_profile),
            )
        },
        content = { innerPadding ->
            Box {
                Box(contentAlignment = Alignment.BottomCenter) {
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
                                SubTitle(text = profile.title!!)

//                                Text(
//                                    "${profile.address!!.street}, ${profile.address.city}",
//                                    color = primaryTextColor
//                                )
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
//                                                showFImage(
//                                                    image = profile.profileImage,
//                                                    name = "${profile.firstName} ${profile.lastName}"
//                                                )
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
                                Description(text = profile.street!!)
                                Description(text = ", ")
                                Description(text = profile.city!!)

                            }

                            Spacer(modifier = Modifier.height(100.dp))


                        }
                    }
                    Column {
                        PhoneCallButton(phoneNumber = profile.phone!!)
                        Spacer(modifier = Modifier.fillMaxHeight(0.05f))
                    }
                }
//                if (showFullScreenImage) {
//                    FullScreenImage(
//                        image = selectedImage,
//                        name = imageName,
//                        onClick = { showFullScreenImage = false })
//                }


            }
//                    Box End
        }
    )
}

@Composable
fun PhoneCallButton(phoneNumber: String) {
//    val context = LocalContext.current
    val makeCallLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
//        result ->
//        if (result.resultCode == Activity.RESULT_OK) {
//            // Handle the result if needed
//        }
        }

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(horizontal = Dimens.normalPadding * 2), shape = RoundedCornerShape(12.dp),
        onClick = {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:$phoneNumber")
            makeCallLauncher.launch(intent)
        },
        elevation = ButtonDefaults.buttonElevation(6.dp)
    ) {
        Text(text = "CALL NOW")
    }
}

//@Composable
//fun FullScreenImage(image: Int, name: String, onClick: () -> Unit) {
//    Box(modifier = Modifier
//        .clickable(indication = null,
//            interactionSource = remember {
//                MutableInteractionSource()
//            }) { onClick.invoke() }
//        .fillMaxSize()
//        .background(dim), contentAlignment = Alignment.Center) {
//        Box(
//            modifier = Modifier
//                .padding(10.dp)
//                .clip(shape = RoundedCornerShape(10.dp))
//                .background(
//                    mateBlack
//                )
//                .width(500.dp)
//                .height(500.dp)
//        ) {
//            Text(
//                text = name,
//                fontSize = 15.sp,
//                fontWeight = FontWeight.Bold,
//                modifier = Modifier.padding(14.dp)
//            )
//            Image(
//                painter = painterResource(id = image),
//                contentDescription = null,
//                Modifier.fillMaxSize(),
//                contentScale = ContentScale.Fit
//            )
//        }
//    }
//}

