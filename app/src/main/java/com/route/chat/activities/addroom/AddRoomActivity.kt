package com.route.chat.activities.addroom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.route.chat.R
import com.route.chat.ui.theme.ChatAppRouteTheme
import com.route.chat.ui.theme.mainBlue
import com.route.chat.utils.appbars.AddRoomAppBar
import com.route.chat.utils.buttons.CreateRoomButton
import com.route.chat.utils.dialogs.LoadingDialog
import com.route.chat.utils.menus.CategoriesMenu
import com.route.chat.utils.textfields.RoomDescriptionTextField
import com.route.chat.utils.textfields.RoomNameTextField

class AddRoomActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChatAppRouteTheme {

                AddRoomContent {
                    finish()
                }
            }
        }
    }
}

@Composable
fun AddRoomContent(vm: AddRoomViewModel = viewModel(), onFinish: () -> Unit) {

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = Color.Transparent,
        topBar = {
            AddRoomAppBar {
                onFinish()
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_background),
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth,
                contentDescription = null,
            )

            LazyColumn(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth(0.9f)
                    .padding(top = 16.dp)
                    .padding(top = paddingValues.calculateTopPadding())
                    .background(
                        Color.White,
                        RoundedCornerShape(16.dp)
                    )
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Text(
                        text = stringResource(R.string.create_new_room),
                        style = TextStyle(Color.Black, 24.sp, FontWeight.SemiBold)
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.ic_people),
                        contentDescription = stringResource(R.string.room_group),
                        tint = mainBlue
                    )
                    RoomNameTextField(state = vm.roomName, errorMessage = vm.roomNameError.value)

                    Spacer(modifier = Modifier.padding(8.dp))
                    CategoriesMenu(vm)
                    Spacer(modifier = Modifier.padding(8.dp))
                    RoomDescriptionTextField(
                        state = vm.roomDescription,
                        errorMessage = vm.roomDescriptionError.value
                    )
                    Spacer(modifier = Modifier.padding(16.dp))
                    CreateRoomButton {
                        vm.addRoomToFirestore()
                    }
                    Spacer(modifier = Modifier
                        .padding(8.dp)
                        .imePadding())
                }
            }
        }
    }
    LoadingDialog(isLoading = vm.isLoading)
    AddRoomNavigation(vm, onFinish)
}

@Composable
fun AddRoomNavigation(vm: AddRoomViewModel, onFinish: () -> Unit) {

    when (vm.event.value) {
        AddRoomEvent.Idle -> {}
        AddRoomEvent.NavigateBack -> {

            onFinish()
        }
    }
}



@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun AddRoomContentPreview() {
    AddRoomContent {}
}


