package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName:String?):Pair<String?,String?>{
        val parts:List<String>?=fullName?.trim()?.split( " ")
        var firstName=parts?.getOrNull(0)
        if(firstName.isNullOrEmpty() || firstName.isNullOrBlank()) {
            firstName = null
        }
        var lastName=parts?.getOrNull(1)
        if(lastName.isNullOrEmpty() || lastName.isNullOrBlank()) {
            lastName = null
        }
        return Pair(firstName,lastName)
    }

    fun transliteration(payload: String,divider : String=" "): String {
        return payload.replace(Regex("[абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ ]")) {
            when (it.value) {
            "а"-> "a"
            "б"-> "b"
            "в"-> "v"
            "г"->"g"
            "д"->"d"
            "е","ё","э"->"e"
            "ж"->"zh"
            "з"->"z"
            "и","й","ы"->"i"
            "к"->"k"
            "л"->"l"
            "м"->"m"
            "н"->"n"
            "о"->"o"
            "п"->"p"
            "р"->"r"
            "с"->"s"
            "т"-> "t"
            "у"-> "u"
            "ф"-> "f"
            "х"-> "h"
            "ц"-> "c"
            "ч"-> "ch"
            "ш"-> "sh"
            "щ"-> "sh'"
            "ъ","ь"-> ""
            "ю"-> "yu"
            "я"-> "ya"
            "A"-> "A"
            "Б"-> "B"
            "В"-> "V"
            "Г"-> "G"
            "Д"-> "D"
            "Е","Ё","Э"-> "E"
            "Ж"-> "Zh"
            "З"-> "Z"
            "И","Й","Ы"-> "I"
            "К"-> "K"
            "Л"-> "L"
            "М"-> "M"
            "Н"-> "N"
            "О"-> "O"
            "П"-> "P"
            "Р"-> "R"
            "С"-> "S"
            "Т"-> "T"
            "У"-> "U"
            "Ф"-> "F"
            "Х"-> "H"
            "Ц"-> "C"
            "Ч"-> "Ch"
            "Ш"-> "Sh"
            "Щ"-> "Sh'"
            "Ъ","Ь"-> ""
            "Э"-> "E"
            "Ю"-> "Yu"
            "Я"-> "Ya"
            " " -> divider
            else -> it.value
            }
        }
    }

    fun toInitials(firstName: String?, lastName: String?    ): String? {
        if (firstName?.trim() == "" && lastName?.trim() == "" ) return null
        if (firstName == null && lastName == null) return null

        val f = firstName?.getOrNull(0)?.toUpperCase()
        val i = lastName?.getOrNull(0)?.toUpperCase()
            val inif = if (f == null) "" else f
            val inii = if (i == null) "" else i

            if (f != null || i != null) return "${inif}${inii}"
            return "$f$i"

    }
}