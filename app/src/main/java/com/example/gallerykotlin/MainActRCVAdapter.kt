package com.example.gallerykotlin

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gallerykotlin.model.ResponseDataItem

class MainActRCVAdapter(private val responsePojo: ArrayList<ResponseDataItem>): RecyclerView.Adapter<MainActRCVAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val image: ImageView=itemView.findViewById(R.id.ivRCV)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.photo_rcv_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val item: ResponseDataItem= responsePojo[position]
        Log.e("adapter", "pic url"+item.urls.regular)

        val context= holder.image.context
        Glide.with(context)
            .load(item.urls.regular)
            .error(R.drawable.myphoto)
            .into(holder.image)

    }

    override fun getItemCount(): Int {
        return responsePojo.size
    }
}