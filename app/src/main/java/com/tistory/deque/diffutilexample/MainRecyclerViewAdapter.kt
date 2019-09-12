package com.tistory.deque.diffutilexample

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.tile_item.view.*

class MainRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val tileAdapterModel = TileAdapterModel(this)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = TileViewHolder(parent)

    override fun getItemCount(): Int = tileAdapterModel.size()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? TileViewHolder)?.onBind(tileAdapterModel.get(position))
    }

    fun shuffle() {
        tileAdapterModel.shuffle()
    }

    fun eraseOneTile() {
        tileAdapterModel.eraseOneTile()
    }

    fun addOneTile() {
        tileAdapterModel.addOneTile()
    }

    fun eraseThreeTile() {
        tileAdapterModel.eraseThreeTile()
    }

    fun addThreeTile() {
        tileAdapterModel.addThreeTile()
    }

    inner class TileViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.tile_item, parent, false)
    ) {
        fun onBind(tile: Tile) {
            itemView.run {
                number_text_view.text = tile.number.toString()
                background_view.setBackgroundColor(tile.color)
            }
        }
    }
}