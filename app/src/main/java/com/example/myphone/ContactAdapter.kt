package com.example.myphone

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import java.util.ArrayList

class ContactAdapter (list:ArrayList<String>,context:Context): RecyclerView.Adapter<ViewHolder>() {
    var ctx:Context = context
    var list:ArrayList<String> = list
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        var view:View = LayoutInflater.from(ctx).inflate(R.layout.item_list,p0,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
            p0.text.setText(list.get(p1))
    }





}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var text = itemView.findViewById<TextView>(R.id.test3)
}
