package world.cepi.laboratory

import net.minestom.server.command.builder.Command
import net.minestom.server.coordinate.Pos
import net.minestom.server.entity.Player
import world.cepi.kstom.Manager
import world.cepi.laboratory.generator.LaboratoryGenerator

object LaboratoryCommand : Command("laboratory", "lab") {

    init {
        setDefaultExecutor { sender, _ ->

            val player = (sender as? Player) ?: return@setDefaultExecutor

            val instance = Manager.instance.createInstanceContainer()

            instance.chunkGenerator = LaboratoryGenerator

            player.setInstance(instance, Pos(0.0, 100.0, 0.0))
        }
    }

}