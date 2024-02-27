package com.example.kazifasta.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
@Parcelize
data class Profile(
    val id: String? = null,
    @SerialName("created_at")
    val createdAt: String? = null,
    @SerialName("profile_image")
    val profileImage: String? = "",
    @SerialName("first_name")
    val firstName: String? = "",
    @SerialName("last_name")
    val lastName: String? = "",
    val title: String? = "",
    val description: String = "No description",
    val street: String? = "",
    val city: String? = "",
    val phone: String? = "",
    val category: String? = "",
    val rating: Double? = null,
    val reviews: Int? = null,
    @SerialName("user_id")
    val userId: String? = ""
) : Parcelable

// Address
@Serializable
@Parcelize
data class Address(
    val id: String? = null,
    @SerialName("created_at")
    val createdAt: String? = null,
    val street: String? = "",
    val city: String? = "",
) : Parcelable


//val ProfilesList = listOf(
//    Profile(
//        profileImage = R.drawable.photography,
//        firstName = "Abdallah",
//        lastName = "Khalfan",
//        title = "Photographer",
//        address = Address(street = "Ilala", city = "Dar es salaam"),
//        phone = "0686724390",
//        category = "Photography",
//        rating = 4.8,
//        reviews = 200,
//        description = "Hi, The modern day reading list includes more than just books. We've created a dashboard to help you track books, articles, podcasts, and videos. Each media type has its own view based on the Type property."
//    ),
//    Profile(
//        profileImage = R.drawable.webdev,
//        firstName = "Louis",
//        lastName = "Ngatale",
//        title = "Web Developer",
//        address = Address(street = "North", city = "Dodoma"),
//        phone = "0765489972",
//        category = "Web Dev",
//        rating = 4.8,
//        reviews = 300
//    ),
//    Profile(
//        profileImage = R.drawable.makeup,
//        firstName = "Mariam",
//        lastName = "Khalfan",
//        title = "Make Up",
//        address = Address(street = "Kona", city = "Arusha"),
//        phone = "0718070939",
//        category = "Make Up",
//        rating = 5.0,
//        reviews = 450,
//        description = "I analyzed many actual Android developer vacancies that have been published on popular job search resources such as www.linkedin.com, www.indeed.com, and others and compiled the following list of needed technologies. I believe that this stack will be relevant in 2023 and\n" +
//                "with a high probability - in 2024."
//    ),
//    Profile(
//        profileImage = R.drawable.mechanic,
//        firstName = "Mukrim",
//        lastName = "Khalfan",
//        title = "Mechanic",
//        address = Address(street = "Kushoto", city = "Mbeya"),
//        phone = "0711098765",
//        category = "Mechanic",
//        rating = 4.6,
//        reviews = 150
//    ),
//    Profile(
//        profileImage = R.drawable.mechanic,
//        firstName = "Mukrim",
//        lastName = "Khalfan",
//        title = "Mechanic",
//        address = Address(street = "Kushoto", city = "Mbeya"),
//        phone = "0711098765",
//        category = "Mechanic",
//        rating = 4.6,
//        reviews = 150
//    ),
//    Profile(
//        profileImage = R.drawable.photography,
//        firstName = "Abdallah",
//        lastName = "Khalfan",
//        title = "Photography",
//        address = Address(street = "Ilala", city = "Dar es salaam"),
//        phone = "0686724390",
//        category = "Photography",
//        rating = 4.8,
//        reviews = 200,
//        description = "Hi, The modern day reading list includes more than just books. We've created a dashboard to help you track books, articles, podcasts, and videos. Each media type has its own view based on the Type property."
//    ),
//    Profile(
//        profileImage = R.drawable.webdev,
//        firstName = "Louis",
//        lastName = "Ngatale",
//        title = "Web Developer",
//        address = Address(street = "North", city = "Dodoma"),
//        phone = "0765489972",
//        category = "Web Dev",
//        rating = 4.8,
//        reviews = 300
//    ),
//    Profile(
//        profileImage = R.drawable.makeup,
//        firstName = "Mariam",
//        lastName = "Khalfan",
//        title = "Make Up",
//        address = Address(street = "Kona", city = "Arusha"),
//        phone = "0718070939",
//        category = "Make Up",
//        rating = 5.0,
//        reviews = 450,
//        description = "I analyzed many actual Android developer vacancies that have been published on popular job search resources such as www.linkedin.com, www.indeed.com, and others and compiled the following list of needed technologies. I believe that this stack will be relevant in 2023 and\n" +
//                "with a high probability - in 2024."
//    ),
//    Profile(
//        profileImage = R.drawable.mechanic,
//        firstName = "Mukrim",
//        lastName = "Khalfan",
//        title = "Mechanic",
//        address = Address(street = "Kushoto", city = "Mbeya"),
//        phone = "0711098765",
//        category = "Mechanic",
//        rating = 4.6,
//        reviews = 150
//    ),
//
//    )