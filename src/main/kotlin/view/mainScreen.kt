package view

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape

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

import androidx.compose.material.*
import androidx.compose.material.Card
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.BrushPainter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

import model.Password

class MainScreen {

    var passwords: MutableList<Password> = mutableListOf()
    var currentPasswordID: MutableState<Int> = mutableStateOf(0)

    lateinit var controlFlag: MutableState<Boolean>

    constructor(passwords: MutableList<Password>){
        this.passwords = passwords
    }

    fun handlePasswordClick(id: Int) {
        this.controlFlag.value = true
        this.currentPasswordID.value = id
    }

    @Composable
    public fun render(){

        this.controlFlag = remember { mutableStateOf(false) }

        Row (){

                /* Left Pannel */
                BoxWithConstraints(){

                    val boxWithConstraintsScope = this

                    val parentMaxWidthStr: String = boxWithConstraintsScope.maxWidth.toString()
                    val parentMaxHeightStr: String = boxWithConstraintsScope.maxHeight.toString()

                    val aux1: List<String> = parentMaxWidthStr.split(".")
                    val aux2: List<String> = parentMaxHeightStr.split(".")

                    val parentMaxWidth: Double = aux1[0].toDouble() * 0.17
                    val parentMaxHeight: Double = aux2[0].toDouble()

                    Card(
                        modifier = Modifier.width(parentMaxWidth.dp).height(parentMaxHeight.dp),
                        shape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp, bottomStart = 0.dp, bottomEnd = 0.dp),
                        backgroundColor = SideMenuGreen
                    ) {
                        
                    }
                }

                Column {
                    topBar()

                    Row {

                        /* Middle Pannel */
                        BoxWithConstraints(){
                            val boxWithConstraintsScope = this

                            val parentMaxWidthStr: String = boxWithConstraintsScope.maxWidth.toString()
                            val parentMaxHeightStr: String = boxWithConstraintsScope.maxHeight.toString()

                            val aux1: List<String> = parentMaxWidthStr.split(".")
                            val aux2: List<String> = parentMaxHeightStr.split(".")

                            val parentMaxWidth: Double = aux1[0].toDouble() * 0.48
                            val parentMaxHeight: Double = aux2[0].toDouble()

                            Card(
                                modifier = Modifier.width(parentMaxWidth.dp).height(parentMaxHeight.dp).border(BorderStroke(2.dp, Color.White)),
                                shape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp, bottomStart = 0.dp, bottomEnd = 0.dp),
                                backgroundColor = BackgroundGray
                            ) {

                                val dinamicWidth = parentMaxWidth * 1
                                val dinamicHeight = parentMaxHeight * 0.10

                                /* Column that wrap passwords*/

                                val scrollState = rememberScrollState(0)

                                Column(
                                    modifier = Modifier.fillMaxWidth()
                                                       .fillMaxHeight()
                                                       .verticalScroll(scrollState)
                                                       .padding(20.dp),
                                    verticalArrangement = Arrangement.Top,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {

                                    passwords.forEach({
                                        passwordListElement(
                                            dinamicWidth = dinamicWidth,
                                            dinamicHeight = dinamicHeight,
                                            id = it.getID(),
                                            name = it.getName(),
                                            description = it.getDescription()
                                        )
                                    })
                                }
                            }
                        }

                        /* Right Pannel */
                        BoxWithConstraints(){

                            val boxWithConstraintsScope = this

                            val parentMaxWidthStr: String = boxWithConstraintsScope.maxWidth.toString()
                            val parentMaxHeightStr: String = boxWithConstraintsScope.maxHeight.toString()

                            val aux1: List<String> = parentMaxWidthStr.split(".")
                            val aux2: List<String> = parentMaxHeightStr.split(".")

                            val parentMaxWidth: Double = aux1[0].toDouble()
                            val parentMaxHeight: Double = aux2[0].toDouble()


                            Card(
                                modifier = Modifier.width(parentMaxWidth.dp).height(parentMaxHeight.dp).border(BorderStroke(2.dp, Color.White)),
                                shape = RoundedCornerShape(0.dp, 0.dp, 0.dp, 0.dp),
                                backgroundColor = BackgroundGray
                            ) {

                                // this conditional statement controls the state of the right pannel, switching from the placeholder image to the password informations

                                if (controlFlag.value == false){
                                    val image: Painter = painterResource("drawable/keylock.png")
                                    Image(painter = image,contentDescription = "", modifier = Modifier.scale(0.5.toFloat()).alpha(0.5.toFloat()))
                                }
                                else {
                                    passwordDetailsElement(parentMaxWidth, parentMaxHeight)
                                }
                            }
                        }
                    }
                }
            }
    }

    @Composable
    private fun topBar(){
        Box(contentAlignment = Alignment.TopStart){
            TopAppBar(backgroundColor = Color.White, modifier = Modifier.height(58.dp)) {
                Row(horizontalArrangement = Arrangement.Start) {

                }
            }
        }
    }

    @Composable
    private fun passwordListElement(
        dinamicWidth: Double,
        dinamicHeight: Double,
        id: Int,
        name: String,
        description: String
    ){

        var passwordInfoWidth = dinamicWidth * 0.90
        var passwordIconWidth = dinamicWidth * 0.10

        var cardColor by remember { mutableStateOf(BackgroundGray)}
        var cardHighlight by remember { mutableStateOf(false) }


        BoxWithConstraints {

            Row (
                modifier = Modifier
                    .width(dinamicWidth.dp)
                    .padding(5.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        if (cardHighlight == false){
                            cardColor = SecondaryBlue
                            cardHighlight = true

                            handlePasswordClick(id)
                        }
                        else {
                            cardColor = BackgroundGray
                            cardHighlight = false
                        }
                    }
            ){

                /* Password Icon */
                Card (
                    modifier = Modifier.width(passwordIconWidth.dp).height(dinamicHeight.dp),
                    backgroundColor = cardColor,
                    shape = RoundedCornerShape(5.dp, 0.dp, 0.dp, 5.dp),
                    elevation = 0.dp
                ){
                    val image: Painter = painterResource("drawable/passwordIcon.png")
                    Image(painter = image,contentDescription = "", modifier = Modifier.scale(0.7.toFloat()).alpha(1.toFloat()))
                }

                Column (
                    modifier = Modifier.width(passwordInfoWidth.dp).height(dinamicHeight.dp)
                ){

                    /* Password */
                    Card(
                        elevation = 0.dp,
                        modifier = Modifier.width(passwordInfoWidth.dp).height((dinamicHeight / 2).dp),
                        backgroundColor = cardColor,
                        shape = RoundedCornerShape(0.dp, 5.dp, 0.dp, 0.dp),
                    ){
                        Text(
                            modifier = Modifier.wrapContentWidth(Alignment.Start).wrapContentHeight(Alignment.Bottom),
                            text = name,
                            fontSize = 1.5.em,
//                            fontFamily = FontFamily.SansSerif
                        )
                    }

                    /* Description */
                    Card(
                        elevation = 0.dp,
                        modifier = Modifier.width(passwordInfoWidth.dp).height(((dinamicHeight / 2) + dinamicHeight * 0.01).dp),
                        backgroundColor = cardColor,
                        shape = RoundedCornerShape(0.dp, 0.dp, 5.dp, 0.dp),
                    ){
                        Text(
                            modifier = Modifier.wrapContentWidth(Alignment.Start).wrapContentHeight(Alignment.Top).alpha(0.8.toFloat()),
                            text = description,
                            fontSize = 1.em,
                        )
                    }
                }
            }
        }
    }

    @Composable
    private fun passwordDetailsElement(parentMaxWidth: Double, parentMaxHeight: Double){

        var textFieldWidth = parentMaxWidth * 0.85
        var textFieldHeight = parentMaxHeight * 0.07

        Column(
            modifier = Modifier.background(color = BackgroundGray),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {

            }

            Column() {

                val image: Painter = painterResource("drawable/passwordIcon.png")

                Spacer(modifier = Modifier.height(80.dp))
                Image(
                    painter = image,
                    contentDescription = "",
                    modifier = Modifier.scale(1.toFloat()).alpha(1.toFloat()).width(textFieldWidth.dp),
                    alignment = Alignment.Center
                )
                Spacer(modifier = Modifier.height(45.dp))
                Text(
                    modifier = Modifier.width(textFieldWidth.dp).background(color = Color.Transparent),
                    text = passwords[currentPasswordID.value - 1].getName(),
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 50.sp,
                    ),
                )
                Spacer(modifier = Modifier.height(80.dp))

                Card(
                    shape = RoundedCornerShape(5.dp, 5.dp, 0.dp, 0.dp),
                    elevation = 0.dp
                ){
                    Text(
                        modifier = Modifier.width(textFieldWidth.dp)
                            .height(28.dp)
                            .background(color = aaaa)
                            .padding(start = (textFieldWidth * 0.02).dp)
                            .wrapContentHeight(Alignment.Bottom),
                        text = "Username",
                        style = TextStyle(
                            fontSize = 17.sp,
                        ),
                    )
                }

                TextField(
                    modifier = Modifier.width(textFieldWidth.dp).height(textFieldHeight.dp).wrapContentHeight(Alignment.Top),
                    value = passwords[currentPasswordID.value - 1].getUsername(),
                    onValueChange = {},
                    shape = RoundedCornerShape(0.dp, 0.dp, 0.dp, 0.dp),
                    readOnly = true,
                    singleLine = true,
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 25.sp
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedLabelColor = Color.Black,
                        backgroundColor = aaaa,
                        unfocusedLabelColor = Color.Black
                    )
                )

                Card(
                    shape = RoundedCornerShape(0.dp, 0.dp, 0.dp, 0.dp),
                    elevation = 0.dp
                ){
                    Text(
                        modifier = Modifier.width(textFieldWidth.dp)
                            .height(28.dp)
                            .background(color = aaaa)
                            .padding(start = (textFieldWidth * 0.02).dp)
                            .wrapContentHeight(Alignment.Bottom),
                        text = "Password",
                        style = TextStyle(
                            fontSize = 17.sp,
                        ),
                    )
                }

                TextField(
                    modifier = Modifier.width(textFieldWidth.dp).height(textFieldHeight.dp).wrapContentHeight(Alignment.Top),
                    value = passwords[currentPasswordID.value - 1].getPassword(),
                    onValueChange = {},
                    shape = RoundedCornerShape(0.dp, 0.dp, 5.dp, 5.dp),
                    readOnly = true,
                    singleLine = true,
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 25.sp
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedLabelColor = Color.Black,
                        backgroundColor = aaaa,
                        unfocusedLabelColor = Color.Black
                    )
                )



//                TextField(
//                    modifier = Modifier.width(textFieldWidth.dp).height(textFieldHeight.dp),
//                    value = passwords[currentPasswordID.value - 1].getPassword(),
//                    onValueChange = {},
//                    shape = RoundedCornerShape(0.dp, 0.dp, 5.dp, 5.dp),
//                    readOnly = true,
//                    singleLine = true,
//                    textStyle = TextStyle(
//                        color = Color.Black,
//                        fontSize = 25.sp
//                    ),
//                    colors = TextFieldDefaults.textFieldColors(
//                        focusedIndicatorColor = Color.Transparent,
//                        unfocusedIndicatorColor = Color.Transparent,
//                        backgroundColor = aaaa
//                    )
//                )
                Spacer(modifier = Modifier.height(50.dp))
                TextField(
                    modifier = Modifier.width(textFieldWidth.dp).height(textFieldHeight.dp),
                    value = passwords[currentPasswordID.value - 1].getDescription(),
                    onValueChange = {},
                    shape = RoundedCornerShape(0.dp, 0.dp, 5.dp, 5.dp),
                    readOnly = true,
                    singleLine = true,
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 20.sp
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedLabelColor = Color.Black,
                        unfocusedLabelColor = Color.Black,
                        backgroundColor = Color.Transparent
                    ),
                    label = {
                        Text(
                            text = "Website",
                            style = TextStyle(
                                color = azuldoido,
                                fontSize = 16.sp,
                            ),
                        )
                    }
                )
                TextField(
                    modifier = Modifier.width(textFieldWidth.dp).height(textFieldHeight.dp),
                    value = passwords[currentPasswordID.value - 1].getDescription(),
                    onValueChange = {},
                    shape = RoundedCornerShape(0.dp, 0.dp, 5.dp, 5.dp),
                    readOnly = true,
                    singleLine = true,
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 20.sp
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedLabelColor = Color.Black,
                        unfocusedLabelColor = Color.Black,
                        backgroundColor = Color.Transparent
                    ),
                    label = {
                        Text(
                            text = "Description",
                            style = TextStyle(
                                color = azuldoido,
                                fontSize = 16.sp,
                            ),
                        )
                    }
                )
            }
        }
    }
}
