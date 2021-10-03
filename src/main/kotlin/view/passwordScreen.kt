package view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.ButtonDefaults

import androidx.compose.material.Card
import androidx.compose.material.TextField
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application


import view.BackgroundGray
import view.CustomBlue
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
                    val aux: List<String> = parentMaxWidthStr.split(".")
                    val parentMaxWidth: Double = aux[0].toDouble() * 0.60

                    Card(
                        modifier = Modifier.width(parentMaxWidth.dp).fillMaxHeight(),
                        shape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp, bottomStart = 0.dp, bottomEnd = 0.dp),
                        backgroundColor = BackgroundGray
                    ) {
//                    LoginField()
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
}
