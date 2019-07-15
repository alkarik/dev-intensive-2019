package ru.skillbranch.devintensive.models

class Bender (var status: Status =Status.NORMAL, var question: Question=Question.NAME){
    var wa:Int = 0
    fun askQuestion():String=when(question){
       Question.NAME  -> Question.NAME.question
       Question.PROFESSION -> Question.PROFESSION.question
       Question.MATERIAL -> Question.MATERIAL.question
       Question.BDAY -> Question.BDAY.question
       Question.SERIAL -> Question.SERIAL.question
       Question.IDLE -> Question.IDLE.question
    }

    fun listenAnswer(answer:String) : Pair<String, Triple<Int,Int,Int>>{
        var dob:String=""
        when(question) {
            Question.NAME -> {
                if(answer[0]!=answer[0].toUpperCase()){dob="Имя должно начинаться с заглавной буквы\n"}
            }
            Question.PROFESSION ->{
                if(answer[0]==answer[0].toUpperCase()){dob="Профессия должна начинаться со строчной буквы\n"}
            }
            Question.MATERIAL ->{
                if("\\d+".toRegex().containsMatchIn(answer)){dob="Материал не должен содержать цифр\n"}
            }
            Question.BDAY ->
            {
                if(!answer.matches(Regex("\\d+"))){dob="Год моего рождения должен содержать только цифры\n"}
            }
            Question.SERIAL -> {
                if (!answer.matches(Regex("\\d{7}"))) {dob = "Серийный номер содержит только цифры, и их 7\n"}
            }
            Question.IDLE -> ""//игнорировать валидацию
        }
        if(dob!="") return "${dob}${question.question}" to status.color

        return if(question.answer.contains(answer)){
            if (question!=Question.IDLE) question=question.nextQuestion()
            "Отлично - ты справился\n${question.question}" to status.color
        }else{
            wa++
            if (wa == 4) {
                status = Status.NORMAL
                question = Question.NAME
                wa = 0
                "Это неправильный ответ. Давай все по новой\n${question.question}" to status.color
            } else {
            status=status.nextStatus()

           "Это неправильный ответ\n" +
                    "${question.question}" to status.color
        }
        }
    }

    enum class  Status(val color: Triple<Int,Int,Int>){
        NORMAL(Triple(255,255,255)),
        WARNING(Triple(255,120,0)),
        DANGER(Triple(255,60,60)),
        CRITICAL(Triple(255,255,0));

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
        IDLE ("На этом все, больше вопросов нет", listOf()){
            override fun nextQuestion(): Question =IDLE
        };

        abstract fun nextQuestion():Question
    }
}