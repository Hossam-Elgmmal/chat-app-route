package com.route.chat.utils.menus

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.route.chat.activities.addroom.AddRoomViewModel
import com.route.chat.ui.theme.mainBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesMenu(vm: AddRoomViewModel = viewModel()) {

    ExposedDropdownMenuBox(
        expanded = vm.isMenuExpanded.value,
        onExpandedChange = {
            vm.isMenuExpanded.value = !vm.isMenuExpanded.value
        }
    ) {

        OutlinedTextField(
            value = vm.selectedCategoryItem.value.name,
            onValueChange = {},
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),
            shape = RectangleShape,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = mainBlue,
                unfocusedIndicatorColor = Color.Gray
            ),
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = vm.isMenuExpanded.value)
            },
            leadingIcon = {
                Image(
                    painter = painterResource(id = vm.selectedCategoryItem.value.imageId),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .padding(8.dp)
                )
            }

        )
        ExposedDropdownMenu(
            expanded = vm.isMenuExpanded.value,
            onDismissRequest = {
                vm.isMenuExpanded.value = !vm.isMenuExpanded.value
            },
            modifier = Modifier.background(Color.White)
        ) {

            vm.categoriesList.forEach { category ->

                DropdownMenuItem(
                    text = { Text(text = category.name) },
                    leadingIcon = {
                        Image(
                            painter = painterResource(id = category.imageId),
                            contentDescription = null,
                            modifier = Modifier
                                .size(50.dp)
                                .padding(8.dp)
                        )
                    },
                    onClick = {
                        vm.selectedCategoryItem.value = category
                        vm.isMenuExpanded.value = !vm.isMenuExpanded.value
                    }
                )
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun CategoriesMenuPreview() {

    CategoriesMenu()

}