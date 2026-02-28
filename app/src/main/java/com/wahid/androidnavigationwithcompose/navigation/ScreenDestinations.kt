package com.wahid.androidnavigationwithcompose.navigation

import android.os.Parcelable
import com.wahid.androidnavigationwithcompose.model.UserCredentials
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable


sealed class ScreenDestinations {

    @Serializable
    data class Home(val username:String, val password:String): ScreenDestinations()

    @Serializable
    data class Login(val username:String, val password:String): ScreenDestinations()

    @Serializable
    data object SignUp: ScreenDestinations()


}