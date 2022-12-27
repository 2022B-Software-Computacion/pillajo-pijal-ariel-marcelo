import java.text.SimpleDateFormat
import java.util.*

class Utils {
    // static method
    companion object {
        fun stringToDate(dateString: String): Date {
            val formatter = SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH)
            val mydate: Date = formatter.parse(dateString)
            return mydate;
        }
    }
}