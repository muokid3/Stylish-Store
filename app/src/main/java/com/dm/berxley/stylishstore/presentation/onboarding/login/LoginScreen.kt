package com.dm.berxley.stylishstore.presentation.onboarding.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dm.berxley.stylishstore.R
import com.dm.berxley.stylishstore.ui.theme.Primary40

@Composable
fun LoginScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var showPassword by remember { mutableStateOf(false) }

        Spacer(modifier = Modifier.height(36.dp))
        Text(
            text = "Welcome Back!",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Please log in to continue...",
            style = MaterialTheme.typography.headlineSmall,
        )

        Spacer(modifier = Modifier.height(48.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = email,
            onValueChange = {
                email = it
            }, leadingIcon = {
                Icon(imageVector = Icons.Filled.Email, contentDescription = "email")
            },
            label = {
                Text(text = "Username or E-mail")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            onValueChange = {
                password = it
            }, leadingIcon = {
                Icon(imageVector = Icons.Filled.Password, contentDescription = "email")
            },
            trailingIcon = {
                if (showPassword){
                    IconButton(onClick = { showPassword = false }) {
                        Icon(imageVector = Icons.Filled.VisibilityOff, contentDescription = "Show")
                    }

                }else{
                    IconButton(onClick = { showPassword = true }) {
                        Icon(imageVector = Icons.Filled.Visibility, contentDescription = "Show")
                    }
                }
            },
            label = {
                Text(text = "Password")
            },
            visualTransformation = if (showPassword) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            )
        )

        Spacer(modifier = Modifier.height(16.dp))


        TextButton(onClick = { /*TODO*/ }) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Forgot Password?",
                color = Primary40,
                textAlign = TextAlign.End
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Primary40,
                contentColor = Color.White
            )
        ) {
            Text(text = "Login")
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "- OR Continue With -",
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(18.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {

            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(MaterialTheme.colorScheme.onPrimary, CircleShape)
                    .border(1.dp, Primary40, CircleShape)

            ) {
                IconButton(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp),
                    onClick = { /*TODO*/ }) {
                    Image(
                        painter = painterResource(id = R.drawable.google),
                        contentDescription = "Google"
                    )
                }
            }


            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(MaterialTheme.colorScheme.onPrimary, CircleShape)
                    .border(1.dp, Primary40, CircleShape)

            ) {
                IconButton(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp),
                    onClick = { /*TODO*/ }) {
                    Image(
                        painter = painterResource(id = R.drawable.apple),
                        contentDescription = "Apple"
                    )
                }

            }

            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(MaterialTheme.colorScheme.onPrimary, CircleShape)
                    .border(1.dp, Primary40, CircleShape)

            ) {
                IconButton(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp),
                    onClick = { /*TODO*/ }) {
                    Image(
                        painter = painterResource(id = R.drawable.facebook),
                        contentDescription = "Facebook"
                    )
                }

            }

        }

        Spacer(modifier = Modifier.height(32.dp))

        TextButton(onClick = { /*TODO*/ }) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Don't have an account? Sign Up",
                textAlign = TextAlign.Center,
                fontSize = 18.sp
            )
        }

    }

}

@Preview(showBackground = true)
@Composable
fun PrevLogin() {
    LoginScreen(navController = rememberNavController())
}