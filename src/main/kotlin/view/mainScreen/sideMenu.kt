package view.mainScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.regular.QuestionCircle

import view.SideMenuGreen


class SideMenu {

    @Composable
    fun renderMenu(width: Double, height: Double){

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
}


