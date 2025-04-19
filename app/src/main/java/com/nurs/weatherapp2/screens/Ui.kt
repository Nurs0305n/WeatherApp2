package com.nurs.weatherapp2.screens

import android.app.AlertDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.nurs.weatherapp2.data.WeatherModel
import com.nurs.weatherapp2.ui.theme.BlueCardBg

@Composable
fun ListItem(item: WeatherModel, currentDay: MutableState<WeatherModel>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 3.dp)
            .clickable {
                if (item.hours.isEmpty()) return@clickable
                currentDay.value = item
            },
        colors = CardDefaults.cardColors(BlueCardBg),
        elevation = CardDefaults.cardElevation(0.dp),
        shape = RoundedCornerShape(5.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {
            Column(
                modifier = Modifier.padding(5.dp)
            ) {
                Text(text = item.time)
                Text(
                    text = item.condition,
                    color = Color.White
                )
            }
            Text(
                text = item.currentTemp.ifEmpty { "${item.maxTemp}/${item.minTemp}" },
                color = Color.White,
                style = TextStyle(fontSize = 25.sp)
            )
            AsyncImage(
                model = "https:${item.icon}",
                contentDescription = "im2",
                modifier = Modifier
                    .size(35.dp)
                    .padding(
                        end = 8.dp
                    )
            )
        }
    }
}

@Composable
fun MainList(list: List<WeatherModel>, currentDay: MutableState<WeatherModel>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        itemsIndexed(
            list
        ) { _, item ->
            ListItem(item, currentDay)
        }
    }
}

@Composable
fun DialogSearch(dialogState: MutableState<Boolean>) {
    AlertDialog(
        title = {
            Text(text = "Search")
        },
        text = {
            Text(text = "City")
        },
        onDismissRequest = {
            dialogState.value = true
        },
        confirmButton = {
            TextButton(
                onClick = {
                    dialogState.value = false
                }
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    dialogState.value = false
                }
            ) {
                Text("Cancel")
            }
        }
    )
//    AlertDialog(
//        onDismissRequest = {
//            dialogState.value = false
//        },
//        confirmButton = {
//            TextButton(onClick = {
//                dialogState.value = false
//            }) {
//                Text(text = "OK")
//            }
//        },
//        dismissButton = {
//            TextButton(onClick = {
//                dialogState.value = false
//            }) {
//                Text(text = "Cancel")
//            }
//        },
//        title = {
//            Column(
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Text(text = "Введите название города:")
//                TextField(
//                    value = "", onValueChange = {},
//                    textStyle = TextStyle(fontSize = 15.sp),
//                    label = {
//                        Text(text = "Город")
//                    })
//            }
//        }
//    )

}