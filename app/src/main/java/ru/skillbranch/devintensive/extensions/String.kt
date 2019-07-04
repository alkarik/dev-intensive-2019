package ru.skillbranch.devintensive.extensions

fun String.truncate(cnt:Int=16):String{
    var str:String =this
    var str1:String
    if(str.length>cnt) {
        str1=str.substring(0,cnt)
        if(str1.substring(cnt-1,cnt)==" ") str1=str1.trimEnd()
        if(str.substring(cnt).trimMargin().length!=0) str1 = str1.plus("...")
        return str1
    }else {
        return str.trimEnd()
    }
}
fun String.stripHtml():String{
    var str1=this.replace(Regex("<.*?>|&#\\d+?;|&\\w+?;"),"")
    return str1.replace(Regex("[\\s]+")," ")

}