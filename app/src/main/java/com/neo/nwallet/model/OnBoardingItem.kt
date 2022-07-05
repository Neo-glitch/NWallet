package com.neo.nwallet.model

import com.neo.nwallet.R

data class OnBoardingItem(
    val image: Int,
    val title: String,
    val description: String
){

    companion object{

        fun getOnBoardingItems(): List<OnBoardingItem>{
            val items = ArrayList<OnBoardingItem>()

            val item1 = OnBoardingItem(R.drawable.ic_onboarding_1, "Title",
                "This is the long description text for the onboarding Item one. Save ur test Ethereum, Matic, BNB here")
            val item2 = OnBoardingItem(R.drawable.ic_onboarding_2, "Title",
                "This is the long description text for the onboarding Item two. Save ur test Ethereum, Matic, BNB here")
            val item3 = OnBoardingItem(R.drawable.ic_onboarding_3, "Title",
                "This is the long description text for the onboarding Item three. Save ur test Ethereum, Matic, BNB here")

            items.add(item1)
            items.add(item2)
            items.add(item3)
            return items
        }

    }
}
