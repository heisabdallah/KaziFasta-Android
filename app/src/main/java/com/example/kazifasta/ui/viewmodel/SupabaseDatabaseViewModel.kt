package com.example.kazifasta.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kazifasta.data.model.Profile
import com.example.kazifasta.data.network.SupabaseClient.supabase
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.launch

class SupabaseDatabaseViewModel : ViewModel(){

    var profilesList = listOf<Profile>()
    var isLoading by mutableStateOf<Boolean>(true)


    fun fetchProfiles(){
        println("Triggered")
        viewModelScope.launch {
            println("SCOPE")
            try {
                val profile = supabase.postgrest["profile"].select().decodeList<Profile>()
                println("fetched Successfullly")
                // Process profiles
                profilesList = profile
                if (profilesList.isNotEmpty()){
                    isLoading = false
                }
                println("Fetched Profiles: ${profile}")
                println("Fetched Profiles List: ${profilesList}")

            } catch (error: Exception) {
                println("FETCHING FAILED!! $error")
            }
        }
    }
}
