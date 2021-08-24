package world.cepi.laboratory

import net.minestom.server.extensions.Extension;

class Laboratory : Extension() {

    override fun initialize() {
        logger.info("[Laboratory] has been enabled!")
    }

    override fun terminate() {
        logger.info("[Laboratory] has been disabled!")
    }

}