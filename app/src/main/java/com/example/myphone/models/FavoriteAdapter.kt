package com.example.myphone.models

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myphone.R

class FavoriteAdapter(_List:ArrayList<ModelCard>,_context:Context): RecyclerView.Adapter<FavoriteAdapter.MyHolder>() {
    var mList:ArrayList<ModelCard> = _List
    var ctx:Context = _context
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyHolder {

        return MyHolder(LayoutInflater.from(ctx).inflate(R.layout.item_list,p0,false))
    }

    override fun getItemCount(): Int {
      return mList.size
    }

    override fun onBindViewHolder(p0: MyHolder, p1: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}