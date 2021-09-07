package world.cepi.laboratory

import net.kyori.adventure.sound.Sound
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.title.Title
import net.minestom.server.command.builder.Command
import net.minestom.server.coordinate.Pos
import net.minestom.server.entity.Player
import net.minestom.server.sound.SoundEvent
import net.minestom.server.tag.Tag
import world.cepi.kstom.Manager
import world.cepi.kstom.command.addSyntax
import world.cepi.kstom.command.arguments.ArgumentPlayer
import world.cepi.kstom.command.arguments.literal
import world.cepi.laboratory.generator.LaboratoryGenerator

object LaboratoryCommand : Command("laboratory", "lab") {

    init {

        val invite = "invite".literal()
        val playerArgument = ArgumentPlayer("player")

        val accept = "accept".literal()

        setDefaultExecutor { sender, _ ->
            Laboratory.create(sender as? Player ?: return@setDefaultExecutor)
        }

        addSyntax(invite, playerArgument) {
            Laboratory.invite(context[playerArgument], sender as? Player ?: return@addSyntax)
        }

        addSyntax(accept) {
            Laboratory.accept(sender as? Player ?: return@addSyntax)
        }
    }

}