package com.robby1.daftarbukuperpustakaan.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.robby1.daftarbukuperpustakaan.R
import com.robby1.daftarbukuperpustakaan.data.entity.Buku

class BukuAdapter(var list: List<Buku>) : RecyclerView.Adapter<BukuAdapter.ViewHolder>() {

    private lateinit var dialog: Dialog
    fun setDIalog(dialog: Dialog) {
        this.dialog = dialog
    }
    interface Dialog{
        fun onClik(position: Int)
    }

    inner class ViewHolder(var view: View) : RecyclerView.ViewHolder(view){

        var judul : TextView
        var penulis : TextView
        var penerbit : TextView
        var thn_terbit : TextView
        init {
            judul = view.findViewById(R.id.judul)
            penulis = view.findViewById(R.id.penulis)
            penerbit = view.findViewById(R.id.penerbit)
            thn_terbit = view.findViewById(R.id.thn_terbit)
            view.setOnClickListener{
                dialog.onClik(layoutPosition)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.baris_buku,parent, false)
        return  ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.judul.text = list[position].judul
        holder.penulis.text = list[position].penulis
        holder.penerbit.text = list[position].penerbit
        holder.thn_terbit.text= list[position].thn_terbit.toString()
    }
}