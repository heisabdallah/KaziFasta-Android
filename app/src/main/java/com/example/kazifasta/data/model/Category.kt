package com.example.kazifasta.data.model

import android.os.Parcelable
import com.example.kazifasta.R
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class Category(
    val id: UUID = UUID.randomUUID(),
    val image: Int,
    val title: String,
) : Parcelable

val categories = listOf(
    Category(image = R.drawable.photography, title = "Photography"),
    Category(image = R.drawable.makeup, title = "Make Up"),
    Category(image = R.drawable.mechanic, title = "Mechanic"),
    Category(image = R.drawable.webdev, title = "Web Dev"),
    Category(image = R.drawable.photography, title = "Photography"),
    Category(image = R.drawable.makeup, title = "Make Up"),
    Category(image = R.drawable.mechanic, title = "Mechanic"),
    Category(image = R.drawable.webdev, title = "Web Dev"),
    Category(image = R.drawable.photography, title = "Photography"),
    Category(image = R.drawable.makeup, title = "Make Up"),
    Category(image = R.drawable.mechanic, title = "Mechanic"),
    Category(image = R.drawable.webdev, title = "Web Dev"),

    )