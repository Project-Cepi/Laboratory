package world.cepi.laboratory

import net.minestom.server.entity.Player
import world.cepi.kstom.command.arguments.ArgumentPlayer
import world.cepi.kstom.command.arguments.literal
import world.cepi.kstom.command.kommand.Kommand

object LaboratoryCommand : Kommand({

    val invite = "invite".literal()
    val playerArgument = ArgumentPlayer("player")

    val accept = "accept".literal()

    default {
        Laboratory.create(sender as? Player ?: return@default)
    }

    syntax(invite, playerArgument).onlyPlayers {
        Laboratory.invite(!playerArgument, player)
    }

    syntax(accept).onlyPlayers {
        Laboratory.accept(player)
    }

}, "laboratory", "lab")