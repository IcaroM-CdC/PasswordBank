package misc

import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

fun textSizeCalc(screenWidth: Double): TextUnit {

    val baseScreenWidth = 1920
    val baseFontSize = 50

    return ((baseScreenWidth * baseFontSize) / screenWidth).sp

}