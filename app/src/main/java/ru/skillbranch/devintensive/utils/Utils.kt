package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName:String?):Pair<String?,String?>{
        val parts:List<String>?=fullName?.split( " ")
        val firstName=parts?.getOrNull(0)
        val lastName=parts?.getOrNull(1)
        return Pair(firstName,lastName)
    }

    fun transliteration(payload: String): String {
        return payload.replace(Regex("[абвгдеёжзийклмнопрстуфхцчшщъыьэюя ]")) {
            when (it.value) {
                "а" -> "a"
                "б" -> "b"
                "в" -> "v"
                "г" -> "g"
                "д" -> "d"
                "е" -> "e"
                "ё" -> "e"
                "ж" -> "g"
                "з" -> "z"
                "и" -> "i"
                "й" -> "i"
                "к" -> "k"
                "л" -> "l"
                "м" -> "m"
                "н" -> "n"
                "о" -> "o"
                "п" -> "p"
                "р" -> "r"
                "с" -> "s"
                "т" -> "t"
                "у" -> "u"
                "ф" -> "f"
                "х" -> "kh"
                "ц" -> "c"
                "ч" -> "ch"
                "ш" -> "sch"
                "щ" -> "sch"
                "ъ" -> ""
                "ы" -> "i"
                "ь" -> ""
                "э" -> "e"
                "ю" -> "u"
                "я" -> "ya"
                " " -> "_"
                else -> it.value
            }
        }
    }

    fun toInitials(firstName: String?, lastName: String?    ): String {
        val f = firstName?.get(0)
        val i = lastName?.get(0)
        return "$f$i"
    }
}