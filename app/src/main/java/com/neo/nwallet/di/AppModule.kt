package com.neo.nwallet.di

import android.content.Context
import com.neo.nwallet.utils.PreferenceDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun providePrefDataStore(@ApplicationContext context: Context) : PreferenceDataStore{
        return PreferenceDataStore(context)
    }
}