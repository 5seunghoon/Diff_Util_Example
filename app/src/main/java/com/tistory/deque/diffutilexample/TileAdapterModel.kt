package com.tistory.deque.diffutilexample

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class TileAdapterModel(val adpater: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
    companion object {
        const val TILE_SIZE = 25
    }

    private val tiles = mutableListOf<Tile>()

    init {
        tiles.clear()
        (1..TILE_SIZE).forEach {
            tiles.add(Tile(it))
        }
    }

    fun size(): Int = tiles.size

    fun get(position: Int) = tiles[position]

    private fun calcDiff(newTiles: MutableList<Tile>) {
        val tileDiffUtilCallback = TileDiffUtilCallback(tiles, newTiles)
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(tileDiffUtilCallback)
        diffResult.dispatchUpdatesTo(adpater)
    }

    private fun setNewTiles(newTiles: MutableList<Tile>) {
        tiles.clear()
        tiles.addAll(newTiles)
    }

    fun shuffle() {
        val newTiles = mutableListOf<Tile>()
        newTiles.addAll(tiles)
        newTiles.shuffle()

        calcDiff(newTiles)
        setNewTiles(newTiles)
    }

    fun eraseOneTile() {
        val newTiles = mutableListOf<Tile>()
        if (tiles.size >= 1) {
            val erasedRandomIndex = (Random.nextDouble() * tiles.size).toInt()
            tiles.forEachIndexed { index, tile ->
                if (index != erasedRandomIndex) newTiles.add(tile)
            }
        }

        calcDiff(newTiles)
        setNewTiles(newTiles)
    }

    fun addOneTile() {
        val newTiles = mutableListOf<Tile>()
        newTiles.addAll(tiles)
        val insertRandomIndex = (Random.nextDouble() * tiles.size).toInt()
        newTiles.add(insertRandomIndex, Tile(tiles.size + 1))

        calcDiff(newTiles)
        setNewTiles(newTiles)
    }

    fun eraseThreeTile() {
        val newTiles = mutableListOf<Tile>()
        newTiles.addAll(tiles)
        if (tiles.size >= 3) {
            repeat(3) {
                val erasedRandomIndex = (Random.nextDouble() * newTiles.size).toInt()
                newTiles.removeAt(erasedRandomIndex)
            }
        }

        calcDiff(newTiles)
        setNewTiles(newTiles)
    }

    fun addThreeTile() {
        val newTiles = mutableListOf<Tile>()
        newTiles.addAll(tiles)
        repeat(3) {
            val insertRandomIndex = (Random.nextDouble() * newTiles.size).toInt()
            newTiles.add(insertRandomIndex, Tile(newTiles.size + 1))
        }

        calcDiff(newTiles)
        setNewTiles(newTiles)
    }

    inner class TileDiffUtilCallback(
        private var oldTiles: MutableList<Tile>,
        private var newTiles: MutableList<Tile>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldTiles.size
        override fun getNewListSize(): Int = newTiles.size
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldTiles[oldItemPosition] == newTiles[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return areItemsTheSame(oldItemPosition, newItemPosition)
        }
    }
}