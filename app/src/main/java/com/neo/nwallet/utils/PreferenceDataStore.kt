package com.neo.nwallet.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PreferenceDataStore @Inject constructor (val context: Context) {

    companion object{
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "NWallet_Pref")
        val HAS_SEEN_ONBOARDING = booleanPreferencesKey("HAS_SEEN_ONBOARDING")

    }

    suspend fun setHasSeenOnboarding(boolean: Boolean){
        context.dataStore.edit { pref ->
            pref[HAS_SEEN_ONBOARDING] = boolean
        }
    }

    val getHasSeenOnboarding: Flow<Boolean> = context.dataStore.data
        .map { pref ->
            // no type safety so know what you are doing
            pref[HAS_SEEN_ONBOARDING] ?: false
        }



}