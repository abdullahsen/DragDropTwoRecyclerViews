package com.example.julia.dragdroptworecyclerviews

import android.os.Bundle
import android.view.View
import android.widget.TextView

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import java.util.ArrayList

import butterknife.BindView
import butterknife.ButterKnife

class MainActivity : AppCompatActivity(), Listener {

    @BindView(R.id.rvTop)
    internal var rvTop: RecyclerView? = null
    @BindView(R.id.rvBottom)
    internal var rvBottom: RecyclerView? = null
    @BindView(R.id.tvEmptyListTop)
    internal var tvEmptyListTop: TextView? = null
    @BindView(R.id.tvEmptyListBottom)
    internal var tvEmptyListBottom: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)

        initTopRecyclerView()
        initBottomRecyclerView()

        tvEmptyListTop!!.visibility = View.GONE
        tvEmptyListBottom!!.visibility = View.GONE
    }

    private fun initTopRecyclerView() {
        rvTop!!.layoutManager = LinearLayoutManager(
                this, LinearLayoutManager.HORIZONTAL, false)

        val topList = ArrayList<String>()
        topList.add("A")
        topList.add("B")

        val topListAdapter = ListAdapter(topList, this)
        rvTop!!.adapter = topListAdapter
        tvEmptyListTop!!.setOnDragListener(topListAdapter.dragInstance)
        rvTop!!.setOnDragListener(topListAdapter.dragInstance)
    }

    private fun initBottomRecyclerView() {
        rvBottom!!.layoutManager = LinearLayoutManager(
                this, LinearLayoutManager.HORIZONTAL, false)

        val bottomList = ArrayList<String>()
        bottomList.add("C")
        bottomList.add("D")

        val bottomListAdapter = ListAdapter(bottomList, this)
        rvBottom!!.adapter = bottomListAdapter
        tvEmptyListBottom!!.setOnDragListener(bottomListAdapter.dragInstance)
        rvBottom!!.setOnDragListener(bottomListAdapter.dragInstance)
    }

    override fun setEmptyListTop(visibility: Boolean) {
        tvEmptyListTop!!.visibility = if (visibility) View.VISIBLE else View.GONE
        rvTop!!.visibility = if (visibility) View.GONE else View.VISIBLE
    }

    override fun setEmptyListBottom(visibility: Boolean) {
        tvEmptyListBottom!!.visibility = if (visibility) View.VISIBLE else View.GONE
        rvBottom!!.visibility = if (visibility) View.GONE else View.VISIBLE
    }
}
