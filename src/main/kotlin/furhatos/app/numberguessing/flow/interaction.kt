package furhatos.app.numberguessing.flow

import furhatos.nlu.common.*
import furhatos.flow.kotlin.*
import furhatos.app.numberguessing.nlu.*
import furhatos.nlu.common.Number
import kotlin.random.Random

val correctNumber = Random.nextInt(1,5)

val Start : State = state(Interaction) {
    onEntry {
        furhat.say("Hi there. Welcome to Pink Programming Christmas Camp")
        goto(NumberGuessingState)
    }
}

// Normal state defined as immutable variable (val)
val NumberGuessingState = state {
    onEntry {
        furhat.say("I have a challenge for you!")
        reentry()
    }

    onReentry {
        var guess = furhat.askFor<Number>("Guess a number between one and five")
        if (guess?.text?.toIntOrNull() == correctNumber) {
            furhat.say("Congratulations, you won!")
        } else {
            furhat.say("Bad luck! Try again!")
            reentry()
        }
    }

    onResponse<RequestRepeat> {
        furhat.say("So you want me to repeat..")
        reentry()
    }

    /*
    onResponse<NumberGuess>{
        furhat.say("You said " + it.text)

        val guess = it.text.toIntOrNull()
        if (guess == correctNumber) {
            furhat.say("Congratulations, you won!")
        } else {
            furhat.say("Bad luck! Try again!")
            reentry()
        }
    }
    */
}
