package world.cepi.laboratory.generator

import de.articdive.jnoise.JNoise
import de.articdive.jnoise.interpolation.InterpolationType
import net.minestom.server.world.biomes.Biome
import net.minestom.server.instance.Chunk
import net.minestom.server.instance.batch.ChunkBatch
import net.minestom.server.instance.ChunkGenerator
import net.minestom.server.instance.block.Block
import world.cepi.kstom.Manager
import world.cepi.laboratory.generator.populator.TreePopulator
import java.util.*

internal class LaboratoryGenerator : ChunkGenerator {

    internal val random = Random()

    private val jNoiseMainMap =
        JNoise.newBuilder().perlin().setInterpolation(InterpolationType.LINEAR).setSeed(random.nextLong())
            .setFrequency(0.6).build()
    private val treeGen: TreePopulator = TreePopulator(this)

    fun getHeight(x: Int, z: Int): Int {
        val preHeight = jNoiseMainMap.getNoise(x / 16.0, z / 16.0)
        return ((if (preHeight > 0) preHeight * 20 else preHeight * 10) + 60).toInt()
    }

    override fun generateChunkData(batch: ChunkBatch, chunkX: Int, chunkZ: Int) {
        for (x in 0 until Chunk.CHUNK_SIZE_X) {
            for (z in 0 until Chunk.CHUNK_SIZE_Z) {
                val height = getHeight(x + chunkX * 16, z + chunkZ * 16)
                for (y in 0 until height.coerceAtMost(256)) {
                    when {
                        y == 0 -> batch.setBlock(x, y, z, Block.BEDROCK)
                        y > 65 -> batch.setBlock(x, y, z, Block.WHITE_CONCRETE)
                        else -> batch.setBlock(x, y, z, Block.PINK_CONCRETE)
                    }
                }
                if (height < 61) {
                    batch.setBlock(x, height - 1, z, Block.LIGHT_BLUE_CONCRETE)
                    for (y in 0 until 61 - height) {
                        batch.setBlock(x, y + height, z, Block.LIGHT_BLUE_STAINED_GLASS)
                    }
                }
            }
        }
    }

    override fun fillBiomes(biomes: Array<Biome>, chunkX: Int, chunkZ: Int) =
        Arrays.fill(biomes, Manager.biome.getById(0))

    override fun getPopulators() = listOf(treeGen)

}