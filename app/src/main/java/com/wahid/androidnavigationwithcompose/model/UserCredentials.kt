package com.wahid.androidnavigationwithcompose.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable


@Serializable
@Parcelize
data class UserCredentials(
    val username: String,
    val password: String
):
Parcelable