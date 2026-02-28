package com.wahid.androidnavigationwithcompose.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.createGraph
import androidx.navigation.toRoute
import com.wahid.androidnavigationwithcompose.model.UserCredentials
import com.wahid.androidnavigationwithcompose.navigation.navType.UserCredentialsNavType
import com.wahid.androidnavigationwithcompose.ui.screen.HomeScreen
import com.wahid.androidnavigationwithcompose.ui.screen.LoginScreen
import com.wahid.androidnavigationwithcompose.ui.screen.SignUp
import kotlin.math.log
import kotlin.reflect.typeOf


@Composable
fun AppNav(
    navHostController: NavHostController,
    navGraph: NavGraph,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navHostController,
        graph = navGraph,
        modifier = modifier
    )
}

@Composable
fun AppNav(
    navHostController: NavHostController,
    onError: (String) -> Unit,
    modifier: Modifier = Modifier

) {
    val navGraph = navHostController.createGraph(startDestination = ScreenDestinations.SignUp) {
        composable<ScreenDestinations.SignUp> {
            var signUpUserCredentialsState by remember { mutableStateOf(UserCredentials("", "")) }
            var signUpConfirmTextValueState by remember { mutableStateOf("") }

            SignUp(
                userCredentials = signUpUserCredentialsState,
                onUsernameValueChange = {
                    signUpUserCredentialsState = signUpUserCredentialsState.copy(
                        username = it,
                        password = signUpUserCredentialsState.password
                    )
                },
                onPasswordValueChange = {
                    signUpUserCredentialsState = signUpUserCredentialsState.copy(
                        username = signUpUserCredentialsState.username,
                        password = it
                    )
                },
                onConfirmPasswordValueChange = {
                    signUpConfirmTextValueState = it
                },
                onSignUpClick = { userCredentials ->
                    if (signUpConfirmTextValueState == signUpUserCredentialsState.password){
                        navHostController.navigate(
                            ScreenDestinations.Login(
                                username = userCredentials.username,
                                password =  userCredentials.password
                            )
                        )
                    }else{
                        onError("Password doesn't match")
                    }


                },
                confirmPasswordTextValue = signUpConfirmTextValueState
            )

        }
        composable<ScreenDestinations.Login>(
            typeMap = mapOf(typeOf<UserCredentials>() to NavType.UserCredentialsNavType)
        ) { backEntry ->
            val userCredentialsBackEntry = backEntry.toRoute<UserCredentials>()

            var loginUpUserCredentialsState by remember { mutableStateOf(UserCredentials("", "")) }


            LoginScreen(
                userCredentials = loginUpUserCredentialsState,
                onUsernameValueChange = { usernameString ->
                    loginUpUserCredentialsState = loginUpUserCredentialsState.copy(
                        password = loginUpUserCredentialsState.password,
                        username = usernameString
                    )

                },
                onPasswordValueChange = { passwordString ->
                    loginUpUserCredentialsState = loginUpUserCredentialsState.copy(
                        username = loginUpUserCredentialsState.username,
                        password = passwordString
                    )
                },
                onLoginClick = {
                    if (loginUpUserCredentialsState.username == userCredentialsBackEntry.username) {
                        if (loginUpUserCredentialsState.password == userCredentialsBackEntry.password) {
                            navHostController.navigate(
                                ScreenDestinations.Home(
                                    username = loginUpUserCredentialsState.username,
                                    password = loginUpUserCredentialsState.password
                                )
                            )
                        } else {
                            onError("Password is not correct, pleas enter the correct password and try again.")
                        }
                    } else {
                        onError("Username is not correct, pleas enter the username password and try again.")

                    }
                }
            )
        }
        composable<ScreenDestinations.Home>(
            typeMap = mapOf(typeOf<UserCredentials>() to NavType.UserCredentialsNavType)
        ) { userCredentialsBackEntry ->
            val userCredentials = userCredentialsBackEntry.toRoute<UserCredentials>()

            HomeScreen(
                onLogout = {
                    navHostController.navigateUp()
                },
                username = userCredentials.username
            )
        }
    }

    AppNav(
        navHostController = navHostController,
        navGraph = navGraph,
        modifier = modifier
    )

}