package world.cepi.laboratory.generator.populator

import de.articdive.jnoise.JNoise
import de.articdive.jnoise.interpolation.InterpolationType
import net.minestom.server.coordinate.Pos
import net.minestom.server.instance.Chunk
import net.minestom.server.instance.ChunkPopulator
import net.minestom.server.instance.batch.ChunkBatch
import net.minestom.server.instance.block.Block
import world.cepi.laboratory.generator.LaboratoryGenerator
import world.cepi.laboratory.generator.Structure

internal class TreePopulator(val generator: LaboratoryGenerator) : ChunkPopulator {

    private val jNoise =
        JNoise.newBuilder().perlin().setInterpolation(InterpolationType.LINEAR).setSeed(generator.random.nextInt().toLong())
            .setFrequency(0.6).build()

    private val tree: Structure = Structure()

    //todo improve
    override fun populateChunk(batch: ChunkBatch, chunk: Chunk) {
        for (i in -2..17) {
            for (j in -2..17) {
                if (jNoise.getNoise(
                        (i + chunk.chunkX * 16).toDouble(),
                        (j + chunk.chunkZ * 16).toDouble()
                    ) > 0.75
                ) {
                    val y = generator.getHeight(i + chunk.chunkX * 16, j + chunk.chunkZ * 16)

                    val position = Pos(i.toDouble(), y.toDouble(), j.toDouble())

                    if (chunk.getBlock(position) != Block.WATER)
                        continue

                    tree.build(batch, Pos(i.toDouble(), y.toDouble(), j.toDouble()))
                }
            }
        }
    }

    companion object {
        val log = Block.QUARTZ_PILLAR
        val leaves = Block.BLUE_STAINED_GLASS
    }
    
    init {
        tree.addBlock(log, 0, 0, 0)
        tree.addBlock(log, 0, 1, 0)
        tree.addBlock(log, 0, 2, 0)
        tree.addBlock(log, 0, 3, 0)
        tree.addBlock(leaves, 1, 1, 0)
        tree.addBlock(leaves, 2, 1, 0)
        tree.addBlock(leaves, -1, 1, 0)
        tree.addBlock(leaves, -2, 1, 0)
        tree.addBlock(leaves, 1, 1, 1)
        tree.addBlock(leaves, 2, 1, 1)
        tree.addBlock(leaves, 0, 1, 1)
        tree.addBlock(leaves, -1, 1, 1)
        tree.addBlock(leaves, -2, 1, 1)
        tree.addBlock(leaves, 1, 1, 2)
        tree.addBlock(leaves, 2, 1, 2)
        tree.addBlock(leaves, 0, 1, 2)
        tree.addBlock(leaves, -1, 1, 2)
        tree.addBlock(leaves, -2, 1, 2)
        tree.addBlock(leaves, 1, 1, -1)
        tree.addBlock(leaves, 2, 1, -1)
        tree.addBlock(leaves, 0, 1, -1)
        tree.addBlock(leaves, -1, 1, -1)
        tree.addBlock(leaves, -2, 1, -1)
        tree.addBlock(leaves, 1, 1, -2)
        tree.addBlock(leaves, 2, 1, -2)
        tree.addBlock(leaves, 0, 1, -2)
        tree.addBlock(leaves, -1, 1, -2)
        tree.addBlock(leaves, -2, 1, -2)
        tree.addBlock(leaves, 1, 2, 0)
        tree.addBlock(leaves, 2, 2, 0)
        tree.addBlock(leaves, -1, 2, 0)
        tree.addBlock(leaves, -2, 2, 0)
        tree.addBlock(leaves, 1, 2, 1)
        tree.addBlock(leaves, 2, 2, 1)
        tree.addBlock(leaves, 0, 2, 1)
        tree.addBlock(leaves, -1, 2, 1)
        tree.addBlock(leaves, -2, 2, 1)
        tree.addBlock(leaves, 1, 2, 2)
        tree.addBlock(leaves, 2, 2, 2)
        tree.addBlock(leaves, 0, 2, 2)
        tree.addBlock(leaves, -1, 2, 2)
        tree.addBlock(leaves, -2, 2, 2)
        tree.addBlock(leaves, 1, 2, -1)
        tree.addBlock(leaves, 2, 2, -1)
        tree.addBlock(leaves, 0, 2, -1)
        tree.addBlock(leaves, -1, 2, -1)
        tree.addBlock(leaves, -2, 2, -1)
        tree.addBlock(leaves, 1, 2, -2)
        tree.addBlock(leaves, 2, 2, -2)
        tree.addBlock(leaves, 0, 2, -2)
        tree.addBlock(leaves, -1, 2, -2)
        tree.addBlock(leaves, -2, 2, -2)
        tree.addBlock(leaves, 1, 3, 0)
        tree.addBlock(leaves, -1, 3, 0)
        tree.addBlock(leaves, 1, 3, 1)
        tree.addBlock(leaves, 0, 3, 1)
        tree.addBlock(leaves, -1, 3, 1)
        tree.addBlock(leaves, 1, 3, -1)
        tree.addBlock(leaves, 0, 3, -1)
        tree.addBlock(leaves, -1, 3, -1)
        tree.addBlock(leaves, 1, 4, 0)
        tree.addBlock(leaves, 0, 4, 0)
        tree.addBlock(leaves, -1, 4, 0)
        tree.addBlock(leaves, 0, 4, 1)
        tree.addBlock(leaves, 0, 4, -1)
        tree.addBlock(leaves, -1, 4, -1)
    }
}