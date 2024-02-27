package com.example.kazifasta.ui.screens.gallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import coil.compose.AsyncImage
import com.example.kazifasta.R
import com.example.kazifasta.ui.common.AppBar
import com.example.kazifasta.ui.theme.KaziFastaTheme
import com.example.kazifasta.ui.utils.Dimens.smallPadding

class GalleryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge(SystemBarStyle.dark(1))

        super.onCreate(savedInstanceState)

        setContent {
            val photoVM = PhotoViewModel()

            val photos = photoVM.photos.collectAsState(initial = emptyList())

            KaziFastaTheme {
                Scaffold(
                    topBar = {
                        AppBar(onClick = { super.onBackPressed() }, title = "Api")
                    },
                    content = { innerPadding ->
                        Column(modifier = Modifier.padding(innerPadding)) {
                            if (photos.value.isEmpty()) {
                                LazyColumn {
                                    items(20) {
                                        Image(
                                            painter = painterResource(id = R.drawable.imageph),
                                            contentDescription = "PlaceHolder",
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(400.dp)
                                        )
                                        Spacer(modifier = Modifier.height(smallPadding))
                                    }
                                }
                            } else {
                                LazyColumn {
                                    items(photos.value) { photo ->
                                        AsyncImage(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(400.dp),
                                            model = photo.thumbnailUrl, // Replace with your image URL
                                            contentDescription = "Image description",
                                            placeholder = painterResource(R.drawable.imageph),
                                            error = painterResource(R.drawable.mechanic),
                                            contentScale = ContentScale.Crop
                                        )
                                        Spacer(modifier = Modifier.height(smallPadding))
                                    }
                                }
                            }
                        }

                    }
                )
            }
        }
    }
}

