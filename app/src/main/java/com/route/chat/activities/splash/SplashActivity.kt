package com.route.chat.activities.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.route.chat.R
import com.route.chat.activities.home.HomeActivity
import com.route.chat.activities.login.LoginActivity
import com.route.chat.model.Constants
import com.route.chat.ui.theme.ChatAppRouteTheme

@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            ChatAppRouteTheme {

                SplashScreen {
                    finish()
                }
            }
        }
    }
}

@Composable
fun SplashScreen(vm: SplashViewModel = viewModel(), onFinish:()->Unit) {

    LaunchedEffect(key1 = Unit) {
        Handler(Looper.getMainLooper())
            .postDelayed({ vm.navigate() }, 2000)
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_logo),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.Center)
        )
        Image(
            painter = painterResource(id = R.drawable.img_signature),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.BottomCenter)

        )
    }
    NavigateEvents(vm.event.value, onFinish)
}

@Composable
fun NavigateEvents(event: SplashEvent, onFinish: () -> Unit) {

    val context = LocalContext.current
    when(event) {
        SplashEvent.Idle -> {}
        is SplashEvent.NavigateToHome -> {
            val intent = Intent(context, HomeActivity::class.java)
            intent.putExtra(Constants.USER_KEY, event.user)
            context.startActivity(intent)
            onFinish()
        }
        SplashEvent.NavigateToLogin -> {
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
            onFinish()
        }
    }
}

@Preview(
    showBackground = true, showSystemUi = true,
    device = "spec:parent=pixel_8_pro"
)
@Composable
fun SplashPreview() {
    ChatAppRouteTheme {
        SplashScreen{}
    }
}