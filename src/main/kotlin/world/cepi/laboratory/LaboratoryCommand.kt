package world.cepi.laboratory

import net.minestom.server.command.builder.Command
import net.minestom.server.coordinate.Pos
import net.minestom.server.entity.Player
import net.minestom.server.tag.Tag
import world.cepi.kstom.Manager
import world.cepi.laboratory.generator.LaboratoryGenerator

object LaboratoryCommand : Command("laboratory", "lab") {

    init {
        setDefaultExecutor { sender, _ ->

            val player = (sender as? Player) ?: return@setDefaultExecutor

            val oldInstance = player.instance!!

            // Create the instance
            run {
                val instance = Manager.instance.createInstanceContainer()

                instance.chunkGenerator = LaboratoryGenerator()

                instance.timeRate = 0

                instance.setTag(Tag.Byte("lab"), 1)

                player.setInstance(instance, Pos(0.0, 100.0, 0.0))
            }

            // Remove the old instance if its the lab
            if (oldInstance.players.isEmpty() && oldInstance.getTag(Tag.Byte("lab")) == 1.toByte()) {
                Manager.instance.unregisterInstance(oldInstance)
            }
        }
    }

}