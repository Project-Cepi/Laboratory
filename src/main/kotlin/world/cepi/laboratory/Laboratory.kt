package world.cepi.laboratory

import net.minestom.server.extensions.Extension;
import world.cepi.kstom.command.register
import world.cepi.kstom.command.unregister

class Laboratory : Extension() {

    override fun initialize() {

        LaboratoryCommand.register()

        logger.info("[Laboratory] has been enabled!")
    }

    override fun terminate() {

        LaboratoryCommand.unregister()

        logger.info("[Laboratory] has been disabled!")
    }

}