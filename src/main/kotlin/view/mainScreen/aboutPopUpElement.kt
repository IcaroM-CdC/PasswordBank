package view.mainScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.Popup


class AboutPopUp {

    lateinit var openPopUpState: MutableState<Boolean>

    @Composable
    fun openPopUp(){

        openPopUpState = remember { mutableStateOf(false)  }

        Popup(
            onDismissRequest = { openPopUpState.value = false },
            alignment = Alignment.Center
        ) {
            Card(
                modifier = Modifier.width(400.dp).height(300.dp).background(color = Color.White),
                elevation = 2.dp,
                shape = RoundedCornerShape(5.dp)
            ) {
                Column(



                ) {

                }
            }
        }


    }
}