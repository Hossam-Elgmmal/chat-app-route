package com.route.chat.activities.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.route.chat.R
import com.route.chat.activities.home.HomeActivity
import com.route.chat.activities.register.RegisterActivity
import com.route.chat.ui.theme.ChatAppRouteTheme
import com.route.chat.utils.appbars.LoginAppBar
import com.route.chat.utils.buttons.ForgotPasswordButton
import com.route.chat.utils.buttons.LoginAuthButton
import com.route.chat.utils.buttons.NewAccountButton
import com.route.chat.utils.dialogs.ErrorDialog
import com.route.chat.utils.dialogs.LoadingDialog
import com.route.chat.utils.textfields.EmailTextField
import com.route.chat.utils.textfields.PasswordTextField

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChatAppRouteTheme {
                LoginScreen {
                    finish()
                }
            }
        }
    }
}

@Composable
fun LoginScreen(vm: LoginViewModel = viewModel(), onFinish: () -> Unit) {

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent),
        containerColor = Color.Transparent,
        topBar = {
            LoginAppBar()
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
                Text(
                    text = stringResource(R.string.welcome_back),
                    style = TextStyle(
                        Color.Black, 32.sp,
                        FontWeight.Bold
                    ),
                    modifier = Modifier.padding(20.dp, 16.dp)
                )

                EmailTextField(vm.email, vm.errorEmail.value)

                PasswordTextField(vm.password, vm.errorPassword.value)

                ForgotPasswordButton {
                    // to do
                }
                LoginAuthButton {
                    vm.login()
                }
                NewAccountButton {
                    vm.navigateToRegister()
                }
                Spacer(
                    modifier = Modifier
                        .imePadding()
                        .consumeWindowInsets(paddingValues)
                )
            }
        }
        LoadingDialog(isLoading = vm.isLoading)
        ErrorDialog(title = vm.loginError)
    }
    LoginNavigation(vm) {
        onFinish()
    }
}

@Composable
fun LoginNavigation(vm: LoginViewModel, onFinish: () -> Unit) {
    val context = LocalContext.current
    when (vm.loginEvent.value) {

        LoginEvent.Idle -> {}

        is LoginEvent.NavigateToHome -> {
            val intent = Intent(context, HomeActivity::class.java)
            context.startActivity(intent)
            onFinish()
        }

        LoginEvent.NavigateToRegister -> {
            val intent = Intent(context, RegisterActivity::class.java)
            context.startActivity(intent)
            vm.loginEvent.value = LoginEvent.Idle

        }
    }
}

@Preview(showBackground = true, device = "id:pixel_8_pro", showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    ChatAppRouteTheme {
        LoginScreen {}
    }
}