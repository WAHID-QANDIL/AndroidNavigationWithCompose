package com.wahid.androidnavigationwithcompose.ui.screen


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wahid.androidnavigationwithcompose.model.UserCredentials

@Composable
fun LoginScreen(
    userCredentials: UserCredentials,
    onLoginClick:(UserCredentials)-> Unit,
    onUsernameValueChange:(String)-> Unit,
    onPasswordValueChange:(String)-> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        TextField(
            value = userCredentials.username,
            onValueChange = onUsernameValueChange,
            shape = RoundedCornerShape(16.dp),
            label = {Text("Username")},
            singleLine = true,
        )
        Spacer(modifier = Modifier.height(12.dp))
        TextField(
            shape = RoundedCornerShape(16.dp),
            label = {Text("Password")},
            singleLine = true,
            value = userCredentials.password,
            onValueChange = onPasswordValueChange
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            onLoginClick(userCredentials)
        }) {
            Text("Login")
        }
    }


}