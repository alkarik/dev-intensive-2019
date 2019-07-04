package ru.skillbranch.devintensive.extensions
import java.lang.Math.abs
import java.text.SimpleDateFormat
import java.util.*

const val SECOND=1000L
const val MINUTE= 60* SECOND
const val HOUR = 60* MINUTE
const val DAY = 24* HOUR


fun Date.format(pattern:String="HH:mm:ss dd.MM.yy"):String{
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value:Int, units: TimeUnits = TimeUnits.SECOND) :Date{
    var time=this.time

    time +=when(units){
        TimeUnits.SECOND -> value* SECOND
        TimeUnits.MINUTE -> value* MINUTE
        TimeUnits.HOUR -> value* HOUR
        TimeUnits.DAY -> value* DAY
    }
    this.time=time
    return this
}

fun Date.humanizeDiff(date:Date = Date()):String{
    val razn=(date.time - this.time)
    val otv:String
    if(razn<0){
        otv=when(razn){
            in -1* SECOND..0* SECOND ->"только что"
            in -45* SECOND .. -1* SECOND -> "через несколько секунд"
            in -75* SECOND .. -45* SECOND ->"через минуту"
            in -45* MINUTE .. -75 ->{
                //"через N минут"
                val q:String
                val a:Int= abs((razn/ MINUTE)).toInt()
                when(a){
                    1-> q="минуту"
                    in 2..4 ->q="минуты"
                    else -> q="минут"
                }
                return "через $a $q"
            }
            in -75* MINUTE .. -45* MINUTE ->"через час"
            in -22* HOUR .. -75* MINUTE ->{
                //"через N часов "
                val q:String
                val a:Int= abs((razn/ HOUR)).toInt()
                when(a){
                    1-> q="час"
                    in 2..4 ->q="часа"
                    else -> q="часов"
                }
                return "через $a $q"
            }
            in -26* HOUR .. -22* HOUR ->"через день"
            in -360* DAY .. -26* HOUR ->{
                //"через N дней"
                val q:String
                val a:Int=abs((razn/ DAY)).toInt()
                when(a){
                    1-> q="день"
                    in 2..4 ->q="дня"
                    else -> q="дней"
                }
                return "через $a $q"
            }
            else -> "более чем через год"
        }
    }else{
        otv=when(razn){
            in 0* SECOND..1* SECOND ->"только что"
            in 1* SECOND .. 45* SECOND -> "несколько секунд назад"
            in 45 * SECOND .. 75* SECOND ->"минуту назад"
            in 75 * SECOND .. 45* MINUTE ->{
                //"N минут назад"
                val q:String
                val a:Int=(razn/ MINUTE).toInt()
                when(a){
                    1-> q="минуту"
                    in 2..4 ->q="минуты"
                    else -> q="минут"
                }
                return "$a $q назад"
            }
            in 45* MINUTE .. 75* MINUTE ->"час назад"
            in 75* MINUTE..22* HOUR ->{
                //"N часов назад"
                val q:String
                val a:Int=(razn/ HOUR).toInt()
                when(a){
                    1-> q="час"
                    in 2..4 ->q="часа"
                    else -> q="часов"
                }
                return "$a $q назад"
                }
            in 22* HOUR .. 26* HOUR ->"день назад"
            in 26* HOUR .. 360* DAY ->{
                //"N дней назад"
                val q:String
                val a:Int=(razn/ DAY).toInt()
                when(a){
                    1-> q="день"
                    in 2..4 ->q="дня"
                    else -> q="дней"
                }
                return "$a $q назад"
            }
            else -> "более года назад"
        }
    }
    return  otv
}

fun TimeUnits.plural(cnt:Int):String{
    val t=this
    val timeUnit = when (cnt%100) {
        1 -> when (t) {
            TimeUnits.SECOND -> "секунду"
            TimeUnits.MINUTE -> "минуту"
            TimeUnits.HOUR -> "час"
            TimeUnits.DAY -> "день"
        }
        in 2..4  -> when (t) {
            TimeUnits.SECOND -> "секунды"
            TimeUnits.MINUTE -> "минуты"
            TimeUnits.HOUR -> "часа"
            TimeUnits.DAY -> "дня"
        }
        in 12..14 ->when(t){
            TimeUnits.SECOND -> "секунд"
            TimeUnits.MINUTE -> "минут"
            TimeUnits.HOUR -> "часов"
            TimeUnits.DAY -> "дней"
        }
        else -> when (cnt%10) {
            in 2..4 ->when(t){
                TimeUnits.SECOND -> "секунды"
                TimeUnits.MINUTE -> "минуты"
                TimeUnits.HOUR -> "часа"
                TimeUnits.DAY -> "дня"
            }
            else -> when(t){
            TimeUnits.SECOND -> "секунд"
            TimeUnits.MINUTE -> "минут"
            TimeUnits.HOUR -> "часов"
            TimeUnits.DAY -> "дней"
            }
        }
    }
    return "${cnt} ${timeUnit}"
}

enum class TimeUnits{
    SECOND,
    MINUTE,
    HOUR,
    DAY
}