package com.example.kazifasta.data.network

import com.example.kazifasta.Config
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage

object SupabaseClient {

    val supabase = createSupabaseClient(
        supabaseUrl = Config.BASE_URL,
        supabaseKey = Config.API_KEY
    ) {
        install(GoTrue)
        install(Storage)
        install(Postgrest)
        //install other modules
    }
}
