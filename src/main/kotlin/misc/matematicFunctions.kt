package misc

import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

fun textSizeCalc(screenWidth: Double): TextUnit {

    val baseScreenWidth = 1080
    val baseFontSize = 50

    return ((screenWidth * baseFontSize) / baseScreenWidth).sp

}