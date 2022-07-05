package com.neo.nwallet.screens.main.onboarding

import androidx.lifecycle.ViewModel
import com.neo.nwallet.utils.PreferenceDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val preferenceDataStore: PreferenceDataStore
    ): ViewModel(){


    suspend fun setHasSeen(boolean: Boolean){
        preferenceDataStore.setHasSeenOnboarding(boolean)
    }

}