package view

/* Jetpack compose imports */

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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular

/* Model imports */

import model.Password

/* Assets imports */

import compose.icons.fontawesomeicons.regular.Eye
import compose.icons.fontawesomeicons.regular.EyeSlash
import compose.icons.fontawesomeicons.regular.QuestionCircle

/* Misc imports */

import misc.textSizeCalc


public class MainScreen {

    private lateinit var createNewPasswordClick: MutableState<Boolean>


    private lateinit var name: MutableState<String>
    private lateinit var username: MutableState<String>
    private lateinit var password: MutableState<String>
    private lateinit var description: MutableState<String>


    private val screenSelector: MutableState<Int> = mutableStateOf(0)

    private var passwords: MutableList<Password> = mutableListOf()
    private val currentPasswordID: MutableState<Int> = mutableStateOf(0)
    private val controlFlag: MutableState<Boolean> = mutableStateOf(false)
    private val showTopBarActionButton: MutableState<Boolean> = mutableStateOf(false)

    constructor(passwords: MutableList<Password>){
        this.passwords = passwords
    }


    /* State managment functions */

    public fun getNewPassword(): Password {
        val newPassword: Password = Password(
            name = this.name.value,
            username = this.username.value,
            password = this.password.value,
            description = this.description.value
        )
        return newPassword
    }

    public fun setNewPassword(value: String){
        this.name.value = value
        this.username.value = value
        this.password.value = value
        this.description.value = value
    }

    public fun getCreateNewPasswordClickState(): Boolean {
        return this.createNewPasswordClick.value
    }

    public fun setCreateNewPasswordClickState(value: Boolean) {
        this.createNewPasswordClick.value = value

        if (value == false) {
            setNewPassword("")
        }
    }

    private fun handleSelectPasswordOnList(id: Int) {
        this.controlFlag.value = true
        this.currentPasswordID.value = id
    }

    /* Composable Functions */

    @Composable
    public fun render() {

        name = remember { mutableStateOf("") }
        username = remember { mutableStateOf("") }
        password = remember { mutableStateOf("") }
        description = remember { mutableStateOf("") }

        createNewPasswordClick = remember { mutableStateOf(false) }

        BoxWithConstraints {

            val mainScope = this
            val mainScreenHeightStr = mainScope.maxHeight.toString()
            val aux: List<String> = mainScreenHeightStr.split(".")
            val mainScreenHeight: Double = aux[0].toDouble()

            Row (){

                    /* Left Pannel */
                    BoxWithConstraints {

                        val boxWithConstraintsScope = this

                        val parentMaxWidthStr: String = boxWithConstraintsScope.maxWidth.toString()
                        val parentMaxHeightStr: String = boxWithConstraintsScope.maxHeight.toString()

                        val aux1: List<String> = parentMaxWidthStr.split(".")
                        val aux2: List<String> = parentMaxHeightStr.split(".")

                        val parentMaxWidth: Double = aux1[0].toDouble() * 0.17
                        val parentMaxHeight: Double = aux2[0].toDouble()

                        sideMenu(width = parentMaxWidth, height = parentMaxHeight)

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
                                            passwordList(
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

                                    // This conditional statement controls the state of the right pannel, switching from the placeholder image to the password informations

                                    if (controlFlag.value == false){
                                        val image: Painter = painterResource("drawable/keylock.png")
                                        Image(painter = image,contentDescription = "", modifier = Modifier.scale(0.5.toFloat()).alpha(0.5.toFloat()))
                                    }
                                    else {
                                        PasswordDetailsElement().render(parentMaxWidth, parentMaxHeight, passwords[currentPasswordID.value - 1])
                                    }


//                                    newPasswordElement(parentMaxWidth, parentMaxHeight, mainScreenHeight)
                                }
                            }
                        }
                    }
            }
        }
    }

    @Composable
    private fun topBar() {
        BoxWithConstraints(contentAlignment = Alignment.TopStart){

            var topBarHeight = this.maxHeight.value
            var topBarWidth = this.maxWidth.value

            TopAppBar(backgroundColor = Color.White, modifier = Modifier.height(58.dp)) {
                Row {
//                    Button(
//                        onClick = {setCreateNewPasswordClickState(true)},
//                        enabled = true,
//                        content = {
//                            Text(text = "Create")
//                        }
//                    )
                    Button(
                        modifier = Modifier.height(topBarHeight.dp).width((topBarWidth * 0.10).dp),
                        shape = RoundedCornerShape(0.dp),
                        elevation = ButtonDefaults.elevation(0.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                        onClick = {},
                        content = {
                            Text(
                                    text = "New Password"
                            )
                        }
                    )
                }
            }
        }
    }

    @Composable
    fun sideMenu(width: Double, height: Double){

        Column (
            modifier = Modifier.width(width.dp).height(height.dp).background(color = SideMenuGreen),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Button(
                modifier = Modifier.width((width * 0.70).dp).height((height * 0.04).dp).wrapContentWidth(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(0.dp),
                colors =  ButtonDefaults.buttonColors(backgroundColor = SideMenuGreen),
                elevation = ButtonDefaults.elevation(0.dp, 0.dp, 0.dp),
                onClick = { /* openAboutPopUp.value = true */}
            ){
                Row() {
                    Icon(
                        modifier = Modifier.size((height * 0.06).dp),
                        imageVector = FontAwesomeIcons.Regular.QuestionCircle,
                        contentDescription = "about",
                        tint = Color.White
                    )
                    Text(
                        text = "About",
                        modifier = Modifier.align(Alignment.CenterVertically).padding(end = (width * 0.07).dp),
                        fontSize = 18.sp,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height((height * 0.01).dp))

        }
    }

    @Composable
    private fun passwordList(
        dinamicWidth: Double,
        dinamicHeight: Double,
        id: Int,
        name: String,
        description: String
    ) {

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

                            handleSelectPasswordOnList(id)
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
    public fun newPasswordElement(
        parentMaxWidth: Double,
        parentMaxHeight: Double,
        mainScreenHeight: Double
    ) {

        val emptyName        = remember { mutableStateOf(false) }
        val emptyUsername    = remember { mutableStateOf(false) }
        val emptyPassword    = remember { mutableStateOf(false) }
        val emptyDescription = remember { mutableStateOf(false) }

        val passwordVisibility = remember { mutableStateOf(false) }

        var textFieldWidth = parentMaxWidth * 0.85
        var textFieldHeight = parentMaxHeight * 0.07

        Column(
            modifier = Modifier.background(color = BackgroundGray),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column() {

                val image: Painter = painterResource("drawable/passwordIcon.png")

                Spacer(modifier = Modifier.height((parentMaxHeight * 0.10).dp))
                Image(
                    painter = image,
                    contentDescription = "",
                    modifier = Modifier.scale(1.toFloat()).alpha(1.toFloat()).width(textFieldWidth.dp),
                    alignment = Alignment.Center
                )
                Spacer(modifier = Modifier.height((parentMaxHeight * 0.075).dp))

                println(mainScreenHeight)
                println(textSizeCalc(mainScreenHeight, 50.toDouble()))

                TextField(
                    modifier = Modifier.width(textFieldWidth.dp).height((textFieldHeight * 1.5).dp),
                    value = name.value,
                    onValueChange = { newName -> name.value = newName },
                    singleLine = true,
                    shape = RoundedCornerShape(0.dp, 0.dp, 0.dp, 0.dp),
                    placeholder = {
                        if (emptyUsername.value == false){
                            Text(
                                text = "Name",
                                modifier = Modifier.padding(start = (textFieldWidth * 0.38).dp),
//                                fontSize = 50.sp,
                                fontSize = textSizeCalc(mainScreenHeight, 50.toDouble()),
                                textAlign = TextAlign.Center
                            )
                        }
                        else {
                            Text(
                                text = "Invalid Name",
                                fontSize = textSizeCalc(mainScreenHeight, 50.toDouble()),
                                textAlign = TextAlign.Center
                            )
                        }
                    },
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = textSizeCalc(mainScreenHeight, 50.toDouble()),
                        textAlign = TextAlign.Center
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedLabelColor = Color.Black,
                        backgroundColor = Color.Transparent,
                        unfocusedLabelColor = Color.Black
                    ),
                    isError = emptyName.value,
                )

                Spacer(modifier = Modifier.height((parentMaxHeight * 0.055).dp))

                /* Password and username fields */

                Card(
                    shape = RoundedCornerShape(5.dp, 5.dp, 0.dp, 0.dp),
                    elevation = 0.dp
                ){
                    Text(
                        modifier = Modifier.width(textFieldWidth.dp)
                            .height((textFieldHeight * 0.45).dp)
                            .background(color = aaaa)
                            .padding(start = (textFieldWidth * 0.02).dp)
                            .wrapContentHeight(Alignment.Bottom),
                        text = "Username",
                        style = TextStyle(
                            fontSize = textSizeCalc(mainScreenHeight, 17.toDouble())
                        ),
                    )
                }

                TextField(
                    modifier = Modifier.width(textFieldWidth.dp).height(textFieldHeight.dp).wrapContentHeight(Alignment.Top),
                    value = username.value,
                    onValueChange = { newUsername -> username.value = newUsername },
                    singleLine = true,
                    shape = RoundedCornerShape(0.dp, 0.dp, 0.dp, 0.dp),
                    placeholder = {
                        if (emptyUsername.value == true){
                            Text("Invalid username")
                        }
                    },
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = textSizeCalc(mainScreenHeight, 25.toDouble())
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedLabelColor = Color.Black,
                        backgroundColor = aaaa,
                        unfocusedLabelColor = Color.Black
                    ),
                    isError = emptyUsername.value,
                )


                Row(){

                    var passwordElementWidth = textFieldWidth * 0.87
                    var passwordElementButtonWidth = textFieldWidth * 0.13

                    Column (
                        modifier = Modifier.height((textFieldHeight + (textFieldHeight * 0.36)).dp)
                    ){
                        Card(
                            shape = RoundedCornerShape(0.dp, 0.dp, 0.dp, 0.dp),
                            elevation = 0.dp
                        ){
                            Text(
                                modifier = Modifier.width(passwordElementWidth.dp)
                                    .height((textFieldHeight * 0.45).dp)
                                    .background(color = aaaa)
                                    .padding(start = (passwordElementWidth * 0.02).dp)
                                    .wrapContentHeight(Alignment.Bottom),
                                text = "Password",
                                style = TextStyle(
                                    fontSize = textSizeCalc(mainScreenHeight, 17.toDouble())
                                ),
                            )
                        }

                        TextField(
                            modifier = Modifier.width(passwordElementWidth.dp)
                                .height(textFieldHeight.dp)
                                .wrapContentHeight(Alignment.Top),
                            value = password.value,
                            visualTransformation = if(passwordVisibility.value == false) PasswordVisualTransformation('*') else VisualTransformation.None,
                            onValueChange = { newPassword -> password.value = newPassword },
                            singleLine = true,
                            shape = RoundedCornerShape(0.dp, 0.dp, 0.dp, 0.dp),
                            placeholder = {
                                if (emptyPassword.value == true){
                                    Text("Invalid password")
                                }
                            },
                            textStyle = TextStyle(
                                color = Color.Black,
                                fontSize = textSizeCalc(mainScreenHeight, 25.toDouble())
                            ),
                            colors = TextFieldDefaults.textFieldColors(
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                focusedLabelColor = Color.Black,
                                backgroundColor = aaaa,
                                unfocusedLabelColor = Color.Black
                            ),
                            isError = emptyPassword.value,
                        )
                    }

                    Card (
                        modifier = Modifier.width(passwordElementButtonWidth.dp).height(((textFieldHeight * 0.45) + (textFieldHeight * 0.90)).dp),
                        elevation = 0.dp,
                        backgroundColor = SideMenuGreen,
                        shape = RoundedCornerShape(0.dp, 0.dp, 5.dp, 0.dp),

                        ) {
                        IconButton( onClick = { passwordVisibility.value = !passwordVisibility.value}) {

//                            TODO("deixar o tamanho do icone dinâmico")

                            Icon(
                                modifier = Modifier.size((textFieldHeight * 0.50).dp),
                                imageVector = if (passwordVisibility.value == true) FontAwesomeIcons.Regular.Eye else FontAwesomeIcons.Regular.EyeSlash,
                                contentDescription = "visibility",
                                tint = Color.White
                            )
                        }
                    }
                }

                /* :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */

                Spacer(modifier = Modifier.height(50.dp))

                TextField(
                    modifier = Modifier.width(textFieldWidth.dp).height((textFieldHeight * 3).dp),
                    value = description.value,
                    onValueChange = { newDescription -> description.value = newDescription },
                    singleLine = false,
                    shape = RoundedCornerShape(0.dp, 0.dp, 5.dp, 5.dp),
                    maxLines = 7,
                    placeholder = {
                        if (emptyDescription.value == true){
                            Text("Invalid description")
                        }
                    },
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = textSizeCalc(mainScreenHeight, 20.toDouble())
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
                                fontSize = textSizeCalc(mainScreenHeight, 16.toDouble())
                            ),
                        )
                    },
                    isError = emptyDescription.value,
                )
            }
        }
    }

}


class PasswordDetailsElement {

    @Composable
    public fun render(
        parentMaxWidth: Double,
        parentMaxHeight: Double,
        password: Password
    ) {

        val passwordVisibility = remember { mutableStateOf(false) }

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

                Spacer(modifier = Modifier.height((parentMaxHeight * 0.10).dp))
                Image(
                    painter = image,
                    contentDescription = "",
                    modifier = Modifier.scale(1.toFloat()).alpha(1.toFloat()).width(textFieldWidth.dp),
                    alignment = Alignment.Center
                )
                Spacer(modifier = Modifier.height((parentMaxHeight * 0.075).dp))

                TextField(
                    modifier = Modifier.width(textFieldWidth.dp).height((textFieldHeight * 1.5).dp),
                    value = password.getName(),
                    onValueChange = {},
                    shape = RoundedCornerShape(0.dp, 0.dp, 0.dp, 0.dp),
                    readOnly = true,
                    singleLine = true,
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 50.sp,
                        textAlign = TextAlign.Center
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedLabelColor = Color.Black,
                        backgroundColor = Color.Transparent,
                        unfocusedLabelColor = Color.Black
                    )
                )

                Spacer(modifier = Modifier.height((parentMaxHeight * 0.055).dp))

                /* PASSWORD AND USERNAME FIELDS */

                Card(
                    shape = RoundedCornerShape(5.dp, 5.dp, 0.dp, 0.dp),
                    elevation = 0.dp
                ){
                    Text(
                        modifier = Modifier.width(textFieldWidth.dp)
                            .height((textFieldHeight * 0.45).dp)
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
                    value = password.getUsername(),
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


                Row(){

                    var passwordElementWidth = textFieldWidth * 0.87
                    var passwordElementButtonWidth = textFieldWidth * 0.13

                    Column (
                        modifier = Modifier.height((textFieldHeight + (textFieldHeight * 0.36)).dp)
                    ){
                        Card(
                            shape = RoundedCornerShape(0.dp, 0.dp, 0.dp, 0.dp),
                            elevation = 0.dp
                        ){
                            Text(
                                modifier = Modifier.width(passwordElementWidth.dp)
                                    .height((textFieldHeight * 0.45).dp)
                                    .background(color = aaaa)
                                    .padding(start = (passwordElementWidth * 0.02).dp)
                                    .wrapContentHeight(Alignment.Bottom),
                                text = "Password",
                                style = TextStyle(
                                    fontSize = 17.sp,
                                ),
                            )
                        }

                        TextField(
                            modifier = Modifier.width(passwordElementWidth.dp)
                                .height(textFieldHeight.dp)
                                .wrapContentHeight(Alignment.Top),
                            value = password.getPassword(),
                            visualTransformation = if(passwordVisibility.value == false) PasswordVisualTransformation('*') else VisualTransformation.None,
                            onValueChange = {},
                            shape = RoundedCornerShape(0.dp, 0.dp, 0.dp, 5.dp),
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
                    }

                    Card (
                        modifier = Modifier.width(passwordElementButtonWidth.dp).height(((textFieldHeight * 0.45) + (textFieldHeight * 0.90)).dp),
                        elevation = 0.dp,
                        backgroundColor = SideMenuGreen,
                        shape = RoundedCornerShape(0.dp, 0.dp, 5.dp, 0.dp),

                        ) {
                        IconButton( onClick = { passwordVisibility.value = !passwordVisibility.value}) {

//                            TODO("deixar o tamanho do icone dinâmico")

                            Icon(
                                modifier = Modifier.size((textFieldHeight * 0.50).dp),
                                imageVector = if (passwordVisibility.value == true) FontAwesomeIcons.Regular.Eye else FontAwesomeIcons.Regular.EyeSlash,
                                contentDescription = "visibility",
                                tint = Color.White
                            )
                        }
                    }
                }


                /* :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */

                Spacer(modifier = Modifier.height(50.dp))
                TextField(
                    modifier = Modifier.width(textFieldWidth.dp).height(textFieldHeight.dp),
                    value = password.getDescription(),
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