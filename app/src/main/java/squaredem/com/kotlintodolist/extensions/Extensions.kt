package squaredem.com.kotlintodolist.extensions

import java.text.DateFormat
import java.util.*

/**
 * Created by Matteo Miceli on 04/02/2018.
 */

fun Long.formatToString(format: Int = DateFormat.SHORT): String {

    // val is not mutable
    val formatter = DateFormat.getDateInstance(format, Locale.getDefault())
    // 'this' is the Long object on which we're calling the function
    return formatter.format(this)

}
