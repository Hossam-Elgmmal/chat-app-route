package com.route.chat.activities.register

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.route.chat.R
import com.route.chat.ui.theme.ChatAppRouteTheme
import com.route.chat.utils.CreateAccountButton
import com.route.chat.utils.EmailTextField
import com.route.chat.utils.PasswordTextField
import com.route.chat.utils.UsernameTextField

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChatAppRouteTheme {
                RegisterScreen {
                    finish()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(vm: RegisterViewModel = viewModel(), onFinish: () -> Unit) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent),
        containerColor = Color.Transparent,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.new_account),
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults
                    .centerAlignedTopAppBarColors(
                        Color.Transparent,
                        titleContentColor = Color.White
                    ),
                navigationIcon = {
                    IconButton(onClick = {
                        onFinish()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = stringResource(R.string.back_to_login),
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ) { paddingValues ->

        Image(
            painter = painterResource(id = R.drawable.img_background),
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth,
            contentDescription = null,
        )
        LazyColumn(
            modifier = Modifier
                .imePadding()
                .consumeWindowInsets(paddingValues)
                .fillMaxSize()
        ) {

            item {
                Image(
                    painter = painterResource(id = R.drawable.img_background),
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.FillWidth,
                    contentDescription = null,
                    alpha = 00F
                )
                UsernameTextField(vm.username)

                EmailTextField(vm.email, vm.errorEmail.value)

                PasswordTextField(vm.password, vm.errorPassword.value)

                CreateAccountButton {}
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true, device = "id:pixel_8_pro")
@Composable
private fun RegisterScreenPreview() {
    RegisterScreen {}
}