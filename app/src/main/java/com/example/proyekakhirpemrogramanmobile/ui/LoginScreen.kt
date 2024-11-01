package com.example.proyekakhirpemrogramanmobile.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyekakhirpemrogramanmobile.R

@Composable
fun LoginScreen(
    onRegisterScreenButtonClicked: () -> Unit,
    onBaseScreenButtonClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 100.dp)
    ) {

        var studentId by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        Text(
            text = stringResource(R.string.this_is_login_screen),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.align(Alignment.TopCenter)
        )

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.Center)
        ) {
            OutlinedTextField(
                value = studentId,
                onValueChange = { studentId = it },
                label = { Text(stringResource(R.string.student_id)) },
                singleLine = true,
                modifier = Modifier.width(300.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(stringResource(R.string.password)) },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.width(300.dp)
            )
            Spacer(modifier = Modifier.height(125.dp))
        }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Button(
                onClick = onRegisterScreenButtonClicked,
                modifier = Modifier
                    .width(200.dp)
                    .height(50.dp)
            ) {
                Text(text = stringResource(R.string.go_to_register_screen))
            }
            Spacer(modifier = Modifier.height(25.dp))
            Button(
                onClick = onBaseScreenButtonClicked,
                modifier = Modifier
                    .width(200.dp)
                    .height(50.dp)
            ) {
                Text(text = stringResource(R.string.go_to_base_screen))
            }
        }
    }
}