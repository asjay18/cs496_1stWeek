package com.example.cs496_1stweek.game

import android.content.pm.LauncherActivityInfo
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cs496_1stweek.R

class GameHistoryAdapter (
    private var dataset: List<GameHistoryItem>
) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val item : GameHistoryItem = dataset[position]
        var view = convertView
        if(view == null) view = LayoutInflater.from(parent.context)
            .inflate(R.layout.game_history_items, parent, false)

        val input : TextView = view!!.findViewById(R.id.input_number)
        input.text = item.input
        val strike : TextView = view!!.findViewById(R.id.strike_text)
        strike.text = item.strike
        val ball : TextView = view!!.findViewById(R.id.ball_text)
        ball.text = item.ball
        val out : TextView = view!!.findViewById(R.id.out_text)
        out.text = item.out
        return view

    }

    override fun getCount(): Int {
        return dataset.size
    }

    override fun getItem(position: Int): Any {
        return dataset[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
}