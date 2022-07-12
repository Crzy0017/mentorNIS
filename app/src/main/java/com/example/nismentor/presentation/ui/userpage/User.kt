package com.example.nismentor.presentation.ui.userpage

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class User(
    val id: Int,
    val token: String
): Parcelable
