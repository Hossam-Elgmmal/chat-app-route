package com.route.chat.activities.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.route.chat.R
import com.route.chat.activities.addroom.AddRoomActivity
import com.route.chat.ui.theme.ChatAppRouteTheme
import com.route.chat.ui.theme.mainBlue
import com.route.chat.utils.appbars.HomeAppBar

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChatAppRouteTheme {

                HomeScreen()
            }
        }
    }
}

@Composable
fun HomeScreen(vm: HomeViewModel = viewModel()) {

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = Color.Transparent,
        topBar = {
            HomeAppBar()
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    vm.navigateToAddRoom()
                },
                containerColor = mainBlue,
                contentColor = Color.White,
                shape = CircleShape
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = stringResource(
                        R.string.add_new_room
                    )
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = paddingValues.calculateBottomPadding()),
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_background),
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth,
                contentDescription = null,
            )

        }
    }
    HomeNavigation(vm.event.value, vm)

}

@Composable
fun HomeNavigation(event: HomeEvent, vm: HomeViewModel) {
    val context = LocalContext.current
    when (event) {
        HomeEvent.Idle -> {}
        HomeEvent.NavigateToAddRoom -> {

            val intent = Intent(context, AddRoomActivity::class.java)
            context.startActivity(intent)
            vm.resetEvent()

        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun HomeScreenPreview() {
    ChatAppRouteTheme {
        HomeScreen()
    }
}

