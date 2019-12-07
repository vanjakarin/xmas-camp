package furhatos.app.numberguessing.flow

import furhatos.app.numberguessing.NumberguessingSkill
import furhatos.nlu.common.*
import furhatos.flow.kotlin.*
import furhatos.flow.kotlin.Audio
import furhatos.app.numberguessing.nlu.*
import furhatos.flow.kotlin.voice.Voice
import furhatos.gestures.Gestures
import furhatos.gestures.Gestures.ExpressDisgust
import furhatos.nlu.common.Number
import furhatos.util.Gender
import furhatos.util.Language
import kotlin.random.Random

val correctNumber = Random.nextInt(1,5)

val filePath = NumberguessingSkill::class.java.getResource("resources/breathing.wav")

val Start : State = state(Interaction) {
    onEntry {
        furhat.say("Hi there. Welcome to Pink Programming Christmas Camp")
        goto(Chicken)
    }
}

val Chicken : State = state(Interaction) {
    onEntry {

        furhat.say({
            +"Are you a chicken? "
            +Audio("https://s3-eu-west-1.amazonaws.com/furhat-users/cad0050f-a55a-4773-9a58-4d34a57cf7b1/audio/chicken_bock_x.wav", "chicken ", speech = false)})
        furhat.say("Mm. That's right")

        goto(LussebullarKitchen)

    }
}

val AllergicReaction : State = state(Interaction) {
    onEntry {
        delay(500)
        furhat.say("Shit. I think I will get an allergic reaction.")
        furhat.gesture(Gestures.Blink(strength=2.0, duration = 3.0))
        furhat.gesture(Gestures.ExpressFear(strength=3.0, duration=2.0))
        furhat.gesture(Gestures.CloseEyes(duration=3.0))
        furhat.ledStrip.solid(java.awt.Color.RED)
        furhat.ask("Please help me!!!! do something")
    }

    onResponse{
        furhat.say("Noooo that won't help me")
        furhat.setVoice(Voice(language= Language.ENGLISH_US, gender= Gender.MALE))
        furhat.setTexture("Geremy")
        delay(100)
        furhat.gesture(Gestures.ExpressFear(duration = 4.0))
        furhat.say("What! You made me change gender..??? ")
        furhat.gesture(Gestures.Shake(strength=3.0, duration=3.0))

        goto(EndState)
    }
}

val EndState : State = state(Interaction) {
    onEntry {
        furhat.gesture(Gestures.Shake(strength=3.0, duration=3.0))
        furhat.say({
            +"Alright, enough! Let's party! "
            +Audio("https://s3-eu-west-1.amazonaws.com/furhat-users/cad0050f-a55a-4773-9a58-4d34a57cf7b1/audio/this_is_the_end_x.wav", "chicken ", speech = false)})
        furhat.say("See ya!")
    }
}

val LussebullarKitchen : State =state(Interaction) {
    onEntry{
        furhat.setVoice(Language.ENGLISH_AU, Gender.FEMALE)
        furhat.ask{
            +"Before you get a lusse-bulle. You have to solve my question. Repeat this following sequence of numbers after I am done saying it:"
            // Within the utterance, the voice can be accessed directly
            +voice.sayAs("0708441514", Voice.SayAsType.TELEPHONE)
        }
    }
    onResponse{
        furhat.say {
            +"Okay. I thought you were smarter than that."
            +"You have one more chance."
            +"Are you ready?"
            +"Give me your ugliest face"
        }
        delay(1500)
        furhat.say {
            +"That was okay. Now it's my turn"
        }
        furhat.gesture(Gestures.ExpressAnger(duration = 5.0))
        delay(1500)
        furhat.say("I am so good at this. Your turn again. Give me another ugly face")
        delay(1500)
        furhat.say("watch this")
        furhat.gesture(ExpressDisgust(duration=2.0))
        furhat.say("Enough fun! Now I deserve a lusse-bulle. you don't get one")
        delay(500)
        goto(AllergicReaction)
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
