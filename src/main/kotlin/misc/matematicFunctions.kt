package misc

import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

fun textSizeCalc(screenHeight: Double, baseFontSize: Double): TextUnit {

    val baseScreenWidth = 1080

    return ((screenHeight * baseFontSize) / baseScreenWidth).sp

}