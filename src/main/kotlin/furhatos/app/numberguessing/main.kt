package furhatos.app.numberguessing

import furhatos.app.numberguessing.flow.*
import furhatos.skills.Skill
import furhatos.flow.kotlin.*

class NumberguessingSkill : Skill() {
    override fun start() {
        Flow().run(Idle)
    }
}

fun main(args: Array<String>) {
    Skill.main(args)
}
