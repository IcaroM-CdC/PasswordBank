package view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.ButtonDefaults

import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.TopAppBar
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


import kotlin.system.exitProcess


class PasswordScreen {

    @Composable
    public fun passwordScreen(){
        Column() {

            topBar()

            Row (){

                /* Left Pannel */
                BoxWithConstraints(){
                    val boxWithConstraintsScope = this

                    val parentMaxWidthStr: String = boxWithConstraintsScope.maxWidth.toString()
                    val parentMaxHeightStr: String = boxWithConstraintsScope.maxHeight.toString()
                    
                    val aux1: List<String> = parentMaxWidthStr.split(".")
                    val aux2: List<String> = parentMaxHeightStr.split(".")
                    
                    val parentMaxWidth: Double = aux1[0].toDouble() * 0.60
                    val parentMaxHeight: Double = aux2[0].toDouble()

                    Card(
                        modifier = Modifier.width(parentMaxWidth.dp).height(parentMaxHeight.dp),
                        shape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp, bottomStart = 0.dp, bottomEnd = 0.dp),
                        backgroundColor = BackgroundGray
                    ) {

                        val dinamicWidth = parentMaxWidth * 0.90
                        val dinamicHeight = parentMaxHeight * 0.10

                        /* Column that wrap passwords*/
                        Column(
                            modifier = Modifier.fillMaxWidth().fillMaxHeight().padding((dinamicHeight * 0.30).dp),
                            verticalArrangement = Arrangement.Top,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            passwordListElement(dinamicWidth = dinamicWidth, dinamicHeight = dinamicHeight)
                            passwordListElement(dinamicWidth = dinamicWidth, dinamicHeight = dinamicHeight)
                            passwordListElement(dinamicWidth = dinamicWidth, dinamicHeight = dinamicHeight)
                            passwordListElement(dinamicWidth = dinamicWidth, dinamicHeight = dinamicHeight)
                        }
                    }
                }

                /* Right Pannel */
                BoxWithConstraints(){
                    Card(
                        modifier = Modifier.fillMaxHeight().fillMaxWidth(),
                        shape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp, bottomStart = 0.dp, bottomEnd = 0.dp),
                        backgroundColor = Color.White
                    ) {
                        val image: Painter = painterResource("drawable/keylock.png")
                        Image(painter = image,contentDescription = "", modifier = Modifier.scale(0.5.toFloat()).alpha(0.5.toFloat()))
                    }
                }

            }
        }
    }

    @Composable
    private fun topBar(){

        Box(contentAlignment = Alignment.TopStart){
            TopAppBar(backgroundColor = CustomBlue, modifier = Modifier.height(58.dp)) {}
            Row(horizontalArrangement = Arrangement.Start) {
                Spacer(Modifier.width(5.dp))
                menu()
                Spacer(Modifier.width(5.dp))
                Button(
                    onClick = {},
                    shape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp, bottomStart = 0.dp, bottomEnd = 0.dp),
                    colors = ButtonDefaults.buttonColors(Color.Gray),
                    modifier = Modifier.width(100.dp).height(58.dp)) {
                    Icon(Icons.Default.Search, contentDescription = "Localized description")
                    Text("New")
                }
                Spacer(Modifier.width(5.dp))
                Button(
                    onClick = {},
                    shape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp, bottomStart = 0.dp, bottomEnd = 0.dp),
                    colors = ButtonDefaults.buttonColors(Color.Gray),
                    modifier = Modifier.width(100.dp).height(58.dp)) {
                    Icon(Icons.Default.Add, contentDescription = "Localized description")
                    Text("New")
                }
            }
        }
    }

    @Composable
    private fun menu(){
        var expanded by remember { mutableStateOf(false) }

        Box(modifier = Modifier.wrapContentSize(Alignment.TopStart)) {
            Button(
                onClick = { expanded = true },
                shape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp, bottomStart = 0.dp, bottomEnd = 0.dp),
                colors = ButtonDefaults.buttonColors(Color.Gray),
                modifier = Modifier.width(100.dp).height(58.dp)
            )
            {
                Icon(Icons.Default.Menu, contentDescription = "Localized description")
                Text("File")
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.width(100.dp)

            ) {
                DropdownMenuItem(onClick = { /* Handle refresh! */ }) {
                    Text("Refresh")
                }
                DropdownMenuItem(onClick = { /* Handle settings! */ }) {
                    Text("Settings")
                }
                Divider()
                DropdownMenuItem(onClick = { exitProcess(-1) }) {
                    Text("Exit")
                }
            }
        }
    }

    @Composable
    private fun passwordListElement(dinamicWidth: Double, dinamicHeight: Double){
        BoxWithConstraints {
            Card (
                modifier = Modifier.width(dinamicWidth.dp).height(dinamicHeight.dp).padding(5.dp),
                shape = RoundedCornerShape(topStart = 2.dp, topEnd = 2.dp, bottomStart = 2.dp, bottomEnd = 2.dp),
                backgroundColor = CustomBlue
            ){

            }
        }
    }
}
