package view

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.clickable
import androidx.compose.foundation.text.selection.SelectionContainer

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
import androidx.compose.ui.unit.em

import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.ScrollbarAdapter
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.*
import androidx.compose.material.Card
import androidx.compose.runtime.saveable.rememberSaveable


import kotlin.system.exitProcess


class MainScreen {

    @Composable
    public fun render(){
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

//                        val dinamicWidth = parentMaxWidth * 0.90
//                        val dinamicHeight = parentMaxHeight * 0.10

                        val dinamicWidth = parentMaxWidth * 1
                        val dinamicHeight = parentMaxHeight * 0.15

                        /* Column that wrap passwords*/

                        val scrollState = rememberScrollState(0)


                        Column(
                            modifier = Modifier.fillMaxWidth()
                                               .fillMaxHeight()
//                                               .padding((dinamicHeight * 0.35).dp)
                                               .verticalScroll(scrollState),
                            verticalArrangement = Arrangement.Top,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            passwordListElement(dinamicWidth = dinamicWidth, dinamicHeight = dinamicHeight)
//                            passwordListElement(dinamicWidth = dinamicWidth, dinamicHeight = dinamicHeight)
//                            passwordListElement(dinamicWidth = dinamicWidth, dinamicHeight = dinamicHeight)
//                            passwordListElement(dinamicWidth = dinamicWidth, dinamicHeight = dinamicHeight)
//                            passwordListElement(dinamicWidth = dinamicWidth, dinamicHeight = dinamicHeight)
//                            passwordListElement(dinamicWidth = dinamicWidth, dinamicHeight = dinamicHeight)
//                            passwordListElement(dinamicWidth = dinamicWidth, dinamicHeight = dinamicHeight)
//                            passwordListElement(dinamicWidth = dinamicWidth, dinamicHeight = dinamicHeight)
//                            passwordListElement(dinamicWidth = dinamicWidth, dinamicHeight = dinamicHeight)
//                            passwordListElement(dinamicWidth = dinamicWidth, dinamicHeight = dinamicHeight)
//                            passwordListElement(dinamicWidth = dinamicWidth, dinamicHeight = dinamicHeight)
//                            passwordListElement(dinamicWidth = dinamicWidth, dinamicHeight = dinamicHeight)

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
                    colors = ButtonDefaults.buttonColors(CustomBlue),
                    modifier = Modifier.width(100.dp).height(58.dp)) {
                    Icon(Icons.Default.Search, contentDescription = "Localized description", tint = BackgroundGray)
                    Text("New", color = BackgroundGray)
                }
                Spacer(Modifier.width(5.dp))
                Button(
                    onClick = {},
                    shape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp, bottomStart = 0.dp, bottomEnd = 0.dp),
                    colors = ButtonDefaults.buttonColors(CustomBlue),
                    modifier = Modifier.width(100.dp).height(58.dp)) {
                    Icon(Icons.Default.Add, contentDescription = "Localized description", tint = BackgroundGray)
                    Text("New", color = BackgroundGray)
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
                colors = ButtonDefaults.buttonColors(CustomBlue),
                modifier = Modifier.width(100.dp).height(58.dp)
            )
            {
                Icon(Icons.Default.Menu, contentDescription = "Localized description", tint = BackgroundGray)
                Text("File", color = BackgroundGray)
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

            Column (
                modifier = Modifier.width(dinamicWidth.dp).height(dinamicHeight.dp)
            ){

                var password by rememberSaveable { mutableStateOf("passwordField") }
                var description by rememberSaveable { mutableStateOf("descriptionField") }

                /* password */
                Card(
                    modifier = Modifier.width(dinamicWidth.dp).height((dinamicHeight / 2).dp),
                    backgroundColor = Color.Red,
                    shape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp, bottomStart = 0.dp, bottomEnd = 0.dp),
                ){

                    SelectionContainer {
                        Text(
                            modifier = Modifier.wrapContentWidth(Alignment.Start).wrapContentHeight(Alignment.CenterVertically),
                            text = password,
                            fontSize = 1.5.em
                        )
                    }
                }

                /* description */
                Card(
                    modifier = Modifier.width(dinamicWidth.dp).height((dinamicHeight / 2).dp),
                    backgroundColor = Color.Yellow,
                    shape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp, bottomStart = 0.dp, bottomEnd = 0.dp),
                ){
                    SelectionContainer {
                        Text(
                            modifier = Modifier.wrapContentWidth(Alignment.Start).wrapContentHeight(Alignment.CenterVertically),
                            text = description,
                            fontSize = 1.em,
                        )
                    }
                }
            }
        }
    }
}
