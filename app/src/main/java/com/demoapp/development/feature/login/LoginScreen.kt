package com.demoapp.development.feature.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.demoapp.development.R


@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(), onNavigateToHome: (username: String) -> Unit
) {

    val username by viewModel.username
    val password by viewModel.password


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = username,
            onValueChange = {
                viewModel.onUsernameChanged(it)
            },

            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            placeholder = { Text(stringResource(id = R.string.input_email)) },
            singleLine = true,
            visualTransformation = VisualTransformation.None
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            onValueChange = {
                viewModel.onPasswordChanged(it)
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done, keyboardType = KeyboardType.Password
            ),
            keyboardActions = KeyboardActions(onDone = {
                //   submit()
            }),
            placeholder = { Text(stringResource(id = R.string.input_password)) },
            singleLine = true,
        )
        Spacer(modifier = Modifier.height(16.dp))
        FilledIconButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onNavigateToHome(username) }) {
            Text(text = stringResource(id = R.string.action_login))
        }

    }


}







