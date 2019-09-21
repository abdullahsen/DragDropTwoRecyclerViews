package com.example.julia.dragdroptworecyclerviews

import android.content.ClipData
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView

internal class ListAdapter(list: List<String>, val listener: Listener?) : RecyclerView.Adapter<ListAdapter.ListViewHolder>(), View.OnTouchListener {

    var list: List<String>? = null

    val dragInstance: DragListener?
        get() {
            if (listener != null) {
                return DragListener(listener)
            } else {
                Log.e("ListAdapter", "Listener wasn't initialized!")
                return null
            }
        }

    init {
        this.list = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(
                parent.context).inflate(R.layout.list_row, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.text?.text = list?.get(position)
        holder.frameLayout?.tag = position
        holder.frameLayout?.setOnTouchListener(this)
        holder.frameLayout?.setOnDragListener(listener?.let { DragListener(it) })
    }


    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                val data = ClipData.newPlainText("", "")
                val shadowBuilder = View.DragShadowBuilder(v)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    v.startDragAndDrop(data, shadowBuilder, v, 0)
                } else {
                    v.startDrag(data, shadowBuilder, v, 0)
                }
                return true
            }
        }
        return false
    }

    fun updateList(list: List<String>?) {
        this.list = list
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var text: TextView? = itemView.findViewById(R.id.text)
        var frameLayout: FrameLayout? = itemView.findViewById(R.id.frame_layout_item)
    }
}
