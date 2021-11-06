package view.mainScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import view.SideMenuGreen


@Composable
fun sideMenu(width: Double, height: Double){
    Column (
        modifier = Modifier.width(width.dp).height(height.dp).background(color = SideMenuGreen)
    ) {
        Button(
            modifier = Modifier.width(100.dp).height(100.dp),
            onClick = { println("clique")}
        ){

        }
//        ClickableText (
//            text = AnnotatedString("About"),
//            modifier = Modifier.width(100.dp).height(50.dp),
//            onClick = { println("a") }
//        )
    }
}