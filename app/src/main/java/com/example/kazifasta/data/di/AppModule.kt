package com.example.kazifasta.data.di

import com.example.kazifasta.ui.viewmodel.SupabaseAuthViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object AppModule {

    @Provides
    fun provideSupabaseAuthViewModel(): SupabaseAuthViewModel {
        return SupabaseAuthViewModel()
    }
}
