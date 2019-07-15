package ru.skillbranch.devintensive.models

class Bender (var status: Status =Status.NORMAL, var question: Question=Question.NAME){
    var wa:Int = 1
    fun askQuestion():String=when(question){
       Question.NAME  -> Question.NAME.question
       Question.PROFESSION -> Question.PROFESSION.question
       Question.MATERIAL -> Question.MATERIAL.question
       Question.BDAY -> Question.BDAY.question
       Question.SERIAL -> Question.SERIAL.question
       Question.IDLE -> Question.IDLE.question
    }

    fun listenAnswer(answer:String) : Pair<String, Triple<Int,Int,Int>>{

        var dob = valid(answer, question)
        if(dob.isNotEmpty()) return "$dob\n${question.question}" to status.color

        return if(question.answer.contains(answer)){
            if (question!=Question.IDLE) question=question.nextQuestion()
            "Отлично - ты справился\n${question.question}" to status.color
        }else{
            wa++
            if (wa >3) {
                status = Status.NORMAL
                question = Question.NAME
                wa = 1
                "Это неправильный ответ. Давай все по новой\n${question.question}" to status.color
            } else {
            status=status.nextStatus()

           "Это неправильный ответ\n" +
                    "${question.question}" to status.color
        }
        }
    }

    private fun valid(answer: String, question: Question): String {
        return when (question) {
            Question.NAME -> {
                if (answer.isNotEmpty() && answer[0] != answer[0].toUpperCase()) {
                  "Имя должно начинаться с заглавной буквы"
                }else{""}
            }
            Question.PROFESSION -> {
                if (answer.isNotEmpty() && answer[0] == answer[0].toUpperCase()) {
                    "Профессия должна начинаться со строчной буквы"
                }else{""}
            }
            Question.MATERIAL -> {
                if ("\\d+".toRegex().containsMatchIn(answer)) {"Материал не должен содержать цифр"}else{""}
            }
            Question.BDAY -> {
                if (!answer.matches(Regex("\\d+"))) {
                    "Год моего рождения должен содержать только цифры"
                }else{""}
            }
            Question.SERIAL -> {
                if (!answer.matches(Regex("\\d{7}"))) {
                    "Серийный номер содержит только цифры, и их 7"
                }else{""}
            }
            Question.IDLE -> ""//игнорировать валидацию
        }
    }

    enum class  Status(val color: Triple<Int,Int,Int>){
        NORMAL(Triple(255,255,255)),
        WARNING(Triple(255,120,0)),
        DANGER(Triple(255,60,60)),
        CRITICAL(Triple(255,0,0));

        fun nextStatus():Status{
            return if(this.ordinal< values().lastIndex){
                values()[this.ordinal+1]
            }else{
                values()[0]
            }
        }
    }

    enum class Question( val question:String, val answer:List<String>){
        NAME ("Как меня зовут?", listOf("Бендер","Bender")){
            override fun nextQuestion(): Question=PROFESSION
        },
        PROFESSION("Назови мою профессию?", listOf("сгибальщик","bender")){
            override fun nextQuestion(): Question =MATERIAL
        },
        MATERIAL("Из чего я сделан?", listOf("металл","дерево","metal","iron","wood")){
            override fun nextQuestion(): Question =BDAY
        },
        BDAY("Когда меня создали?", listOf("2993")){
            override fun nextQuestion(): Question =SERIAL
        },
        SERIAL("Мой серийный номер?", listOf("2716057")){
            override fun nextQuestion(): Question =IDLE
        },
        IDLE ("На этом все, вопросов больше нет", listOf()){
            override fun nextQuestion(): Question =IDLE
        };

        abstract fun nextQuestion():Question
    }
}