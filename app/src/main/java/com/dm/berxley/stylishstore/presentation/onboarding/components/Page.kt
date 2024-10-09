package com.dm.berxley.stylishstore.presentation.onboarding.components

import android.media.Image
import androidx.annotation.DrawableRes
import com.dm.berxley.stylishstore.R

data class Page(@DrawableRes val image: Int, val title: String, val description: String)
val pages = listOf<Page>(
    Page(R.drawable.products, "Choose Products", "Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint. Velit officia consequat duis enim velit mollit."),
    Page(R.drawable.payments, "Make Payment", "Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint. Velit officia consequat duis enim velit mollit."),
    Page(R.drawable.order, "Get Your Order", "Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint. Velit officia consequat duis enim velit mollit.")
)
