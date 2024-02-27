package com.example.kazifasta.ui.common

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.kazifasta.R
import com.example.kazifasta.data.model.Profile
import com.example.kazifasta.ui.theme.green
import com.example.kazifasta.ui.theme.mateWhite

//  Freelancer Profile Tile
@Composable
fun FreelancerProfileTile(
    profile: Profile,
    paddingStart: Dp? = 0.dp,
    paddingEnd: Dp? = 0.dp,
    paddingTop: Dp? = 0.dp,
    onClick: () -> Unit,
) {
    Box(modifier = Modifier
        .width(160.dp)
        .height(220.dp)
        .padding(start = paddingStart!!, end = paddingEnd!!, top = paddingTop!!)
        .clip(shape = RoundedCornerShape(12.dp))
        .background(Color.White)
        .clickable { onClick.invoke() }, contentAlignment = Alignment.BottomCenter
    ) {

        AsyncImage(
            modifier = Modifier
                .fillMaxHeight(),
            model = profile.profileImage,
            contentScale = ContentScale.Crop,
            contentDescription = "Display Image"
        )

        Column(
            modifier = Modifier
                .background(green)
                .fillMaxWidth()
                .padding(horizontal = 6.dp, vertical = 10.dp)
        ) {
//                        Spacer(modifier = Modifier.height(10.dp))
            Text(
                "${profile.firstName} ${profile.lastName}",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = mateWhite,
            )
            Column(verticalArrangement = Arrangement.spacedBy((-8).dp)) {
                Text(profile.title!!, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = mateWhite)
            }
            Text("${profile.street}, ${profile.city}", fontSize = 10.sp, color = mateWhite)
            Row {
                Icon(
                    painter = painterResource(id = R.drawable.star),
                    tint = Color.Yellow,
                    contentDescription = "Star"
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text("${profile.rating}", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = mateWhite,)
                Spacer(modifier = Modifier.width(12.dp))
                Text("${profile.reviews}+ Reviews", fontSize = 10.sp, color = mateWhite,)
            }
        }
    }
}

//  Freelancer Profile Tile 2
@Composable
fun FreelancerProfileTile_2(
    profile: Profile,
    paddingStart: Dp? = 0.dp,
    paddingEnd: Dp? = 0.dp,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .padding(start = paddingStart!!, end = paddingEnd!!)
            .fillMaxWidth().clip(shape = RoundedCornerShape(12))
            .background(green)
            .height(120.dp)
            .clickable { onClick.invoke() },
    ) {

        AsyncImage(
            modifier = Modifier
                .width(150.dp)
                .height(220.dp),
            model = profile.profileImage,
            contentScale = ContentScale.Crop,
            contentDescription = "Display Image"
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 6.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                "${profile.firstName} ${profile.lastName}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = mateWhite
            )
            Column(verticalArrangement = Arrangement.spacedBy((-8).dp)) {
                Text(
                    profile.title!!,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = mateWhite
                )
            }
            Text(
                "${profile.street}, ${profile.city}",
                fontSize = 10.sp,
                color = mateWhite
            )
            Row {
                Icon(
                    painter = painterResource(id = R.drawable.star),
                    tint = Color.Yellow,
                    contentDescription = "Star"
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    "${profile.rating}",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = mateWhite
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text("${profile.reviews}+ Reviews", fontSize = 10.sp, color = mateWhite)
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun Preview() {
//    FreelancerProfileTile(profile = ProfilesList[1]) {}
//}