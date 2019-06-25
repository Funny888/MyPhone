package com.example.myphone

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.myphone.Models.ModelCard
import java.util.ArrayList

class ContactAdapter (list:HashSet<ModelCard>,context:Context): RecyclerView.Adapter<ViewHolder>() {
    var ctx:Context = context
    var list:HashSet<ModelCard> = list
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        var view:View = LayoutInflater.from(ctx).inflate(R.layout.item_list,p0,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
            p0.image.setBackgroundColor(list.elementAt(p1).photo)
            p0.name.setText(list.elementAt(p1).name)
            p0.phone.setText(list.elementAt(p1).phone)
    }





}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var image = itemView.findViewById<ImageView>(R.id.photo)
    var name = itemView.findViewById<TextView>(R.id.display_name)
    var phone = itemView.findViewById<TextView>(R.id.phone)
}
