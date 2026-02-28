package com.wahid.androidnavigationwithcompose.navigation.navType

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavType
import androidx.savedstate.SavedState
import com.wahid.androidnavigationwithcompose.model.UserCredentials
import kotlinx.serialization.json.Json


val NavType.Companion.UserCredentialsNavType: NavType<UserCredentials>
    get() = object : NavType<UserCredentials>(isNullableAllowed = false) {
        override fun put(
            bundle: SavedState,
            key: String,
            value: UserCredentials
        ) {
            bundle.putParcelable(key,value)
        }

        @RequiresApi(Build.VERSION_CODES.TIRAMISU)
        override fun get(
            bundle: SavedState,
            key: String
        ): UserCredentials? {
            return bundle.getParcelable<UserCredentials>(key)
        }

        override fun parseValue(value: String) = Json.decodeFromString<UserCredentials>(value)


    }