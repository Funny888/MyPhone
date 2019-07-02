package com.example.myphone

import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myphone.interfaces.Notify
import com.example.myphone.models.ModelCard

class ContactAdapter (list:HashSet<ModelCard>,context:Context): RecyclerView.Adapter<ViewHolder>() {
    var ctx:Context = context
    var list:HashSet<ModelCard> = list
    lateinit var notis:Notify
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        var view:View = LayoutInflater.from(ctx).inflate(R.layout.item_list,p0,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return list.size
    }
    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
            if (!list.elementAt(p1).photo.equals("null")){
                p0.image.setImageURI(Uri.parse(list.elementAt(p1).photo))
            }else{
                var sex:String = list.elementAt(p1).name.subSequence(list.elementAt(p1).name.length-1,list.elementAt(p1).name.length).toString()
                if (sex.equals("а") || sex.equals("А") || sex.equals("я") || sex.equals("Я") ||sex.equals("a") || sex.equals("A") ) {
                    p0.image.setImageResource(R.mipmap.profile_female)
                } else {
                    p0.image.setImageResource(R.mipmap.profile_male)
                }

            }

            p0.name.setText(list.elementAt(p1).name)
            p0.phone.setText(list.elementAt(p1).phone)
            p0.itemView.setOnClickListener { notis.getItemId(p1,true)}
    }

    fun Notufy(notify: Notify):Notify{
        notis = notify
        return notis
    }



}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var image = itemView.findViewById<ImageView>(R.id.photo)
    var name = itemView.findViewById<TextView>(R.id.display_name)
    var phone = itemView.findViewById<TextView>(R.id.phone)
}
