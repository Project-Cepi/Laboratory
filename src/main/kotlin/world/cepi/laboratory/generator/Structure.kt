package world.cepi.laboratory.generator

import net.minestom.server.coordinate.Point
import net.minestom.server.coordinate.Pos
import net.minestom.server.instance.Chunk
import net.minestom.server.instance.batch.ChunkBatch
import net.minestom.server.instance.block.Block
import java.util.HashMap

class Structure {
    private val blocks: MutableMap<Pos, Block> = HashMap<Pos, Block>()
    fun build(batch: ChunkBatch, pos: Pos) {
        blocks.forEach { (bPos: Point, block: Block?) ->
            if (bPos.x() + pos.x() >= Chunk.CHUNK_SIZE_X || bPos.x() + pos.x() < 0) return@forEach
            if (bPos.z() + pos.z() >= Chunk.CHUNK_SIZE_Z || bPos.z() + pos.z() < 0) return@forEach
            batch.setBlock(bPos.add(pos), block)
        }
    }

    fun addBlock(block: Block, localX: Int, localY: Int, localZ: Int) {
        blocks[Pos(localX.toDouble(), localY.toDouble(), localZ.toDouble())] = block
    }
}