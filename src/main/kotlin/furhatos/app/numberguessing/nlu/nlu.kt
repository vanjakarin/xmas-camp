package furhatos.app.numberguessing.nlu

import furhatos.nlu.Intent
import furhatos.util.Language

class NumberGuess : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
                "one",
                "two",
                "three",
                "four",
                "five",
                "1",
                "2",
                "3",
                "4",
                "5"
        )
    }
}