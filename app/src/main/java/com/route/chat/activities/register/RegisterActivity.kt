package com.route.chat.activities.register

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.route.chat.R
import com.route.chat.activities.home.HomeActivity
import com.route.chat.ui.theme.ChatAppRouteTheme
import com.route.chat.utils.appbars.RegisterAppBar
import com.route.chat.utils.buttons.CreateAccountButton
import com.route.chat.utils.dialogs.ErrorDialog
import com.route.chat.utils.dialogs.LoadingDialog
import com.route.chat.utils.textfields.EmailTextField
import com.route.chat.utils.textfields.PasswordTextField
import com.route.chat.utils.textfields.UsernameTextField

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChatAppRouteTheme {
                RegisterScreen(
                    onRegisterSuccess = {
                        finishAffinity()
                    }, onFinish = {
                        finish()
                    })
            }
        }
    }
}

@Composable
fun RegisterScreen(
    vm: RegisterViewModel = viewModel(),
    onRegisterSuccess: () -> Unit,
    onFinish: () -> Unit
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent),
        containerColor = Color.Transparent,
        topBar = {
            RegisterAppBar {
                onFinish()
            }
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
                UsernameTextField(vm.username, vm.errorName.value)

                EmailTextField(vm.email, vm.errorEmail.value)

                PasswordTextField(vm.password, vm.errorPassword.value)

                CreateAccountButton { vm.register() }

                Spacer(
                    modifier = Modifier
                        .imePadding()
                        .consumeWindowInsets(paddingValues)
                )
            }
        }
        LoadingDialog(isLoading = vm.isLoading)
        ErrorDialog(title = vm.registerError)
    }
    RegisterNavigation(event = vm.event.value, onRegisterSuccess)
}

@Composable
fun RegisterNavigation(event: RegisterEvent, onRegisterSuccess: () -> Unit) {

    val context = LocalContext.current

    when (event) {
        RegisterEvent.Idle -> {}
        is RegisterEvent.NavigateToHome -> {
            val intent = Intent(context, HomeActivity::class.java)
            context.startActivity(intent)
            onRegisterSuccess()
        }
    }
}

@Preview(showSystemUi = true, showBackground = true, device = "id:pixel_8_pro")
@Composable
private fun RegisterScreenPreview() {
    RegisterScreen(onRegisterSuccess = {}, onFinish = {})
}