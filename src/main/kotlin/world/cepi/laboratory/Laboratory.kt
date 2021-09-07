package world.cepi.laboratory

import net.kyori.adventure.sound.Sound
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.title.Title
import net.minestom.server.coordinate.Pos
import net.minestom.server.entity.Player
import net.minestom.server.instance.Instance
import net.minestom.server.sound.SoundEvent
import net.minestom.server.tag.Tag
import net.minestom.server.utils.time.TimeUnit
import world.cepi.kstom.Manager
import world.cepi.laboratory.generator.LaboratoryGenerator
import java.lang.ref.WeakReference
import java.time.Duration
import java.util.*

object Laboratory {

    val inviteDuration = Duration.of(1, TimeUnit.MINUTE)

    val invites = WeakHashMap<Player, Pair<WeakReference<Player>, Long>>()

    fun isLab(instance: Instance) = instance.getTag(Tag.Byte("lab")) == 1.toByte()

    fun check(instance: Instance) {
        if (instance.players.isEmpty() && isLab(instance)) {
            Manager.instance.unregisterInstance(instance)
        }
    }

    fun highestPointAt(instance: Instance, x: Double = 0.0, z: Double = 0.0) =
        (256 downTo 0).map { Pos(x, it.toDouble(), z) }.first { !instance.getBlock(it).isAir }

    fun create(player: Player) {
        val oldInstance = player.instance!!

        // Create the instance
        val instance = Manager.instance.createInstanceContainer()

        instance.chunkGenerator = LaboratoryGenerator()

        instance.timeRate = 0

        instance.setTag(Tag.Byte("lab"), 1)

        player.setInstance(instance).thenAccept {
            player.teleport(highestPointAt(instance))

            player.showTitle(
                Title.title(
                    Component.text("Welcome to your lab!", NamedTextColor.BLUE),
                    Component.text("Invite friends using /lab invite <friend>", NamedTextColor.GRAY)
                )
            )

            player.playSound(
                Sound.sound(SoundEvent.ENTITY_PLAYER_LEVELUP, Sound.Source.MASTER, .5f, 1f)
            )
        }


        // Remove the old instance if its the lab
        check(oldInstance)
    }

    fun send(player: Player, owner: Player) {
        if (!isLab(owner.instance!!)) return

        player.setInstance(owner.instance!!).thenAccept {
            player.teleport(highestPointAt(owner.instance!!))

            player.showTitle(
                Title.title(
                    Component.text("Welcome to ${owner.username}'s lab!", NamedTextColor.BLUE),
                    Component.empty()
                )
            )

            player.playSound(
                Sound.sound(SoundEvent.ENTITY_PLAYER_LEVELUP, Sound.Source.MASTER, .5f, 1f)
            )
        }
    }

    fun accept(player: Player) {
        if (System.currentTimeMillis() - (invites[player]?.second ?: return) < inviteDuration.toMillis()) {
            send(player, invites[player]?.first?.get() ?: return)
        }
    }

    fun invite(player: Player, sender: Player) {
        sender.sendMessage("Invited ${player.username}!")
        player.sendMessage("${sender.username} invited you!")

        invites[player] = WeakReference(sender) to System.currentTimeMillis()
    }

}