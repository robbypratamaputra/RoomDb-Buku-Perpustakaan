package com.robby1.daftarbukuperpustakaan

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.robby1.daftarbukuperpustakaan.adapter.BukuAdapter
import com.robby1.daftarbukuperpustakaan.data.AppDatabase
import com.robby1.daftarbukuperpustakaan.data.entity.Buku

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var fab: FloatingActionButton
    private var list = mutableListOf<Buku>()
    private lateinit var adapter: BukuAdapter
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recycler_view)
        fab = findViewById(R.id.fab)

        database = AppDatabase.getInstance(applicationContext)
        adapter = BukuAdapter(list)

        adapter.setDIalog(object : BukuAdapter.Dialog{
            override fun onClik(position: Int) {
                //buat dialog view
                val dialog = AlertDialog.Builder(this@MainActivity)
                dialog.setTitle(list[position].judul)
                dialog.setItems(R.array.items_options, DialogInterface.OnClickListener{ dialog, which ->
                    if (which==0){
                        // coding ubah
                        val intent = Intent(this@MainActivity, EditorActivity::class.java)
                        intent.putExtra("id", list[position].uid)
                        startActivity(intent)
                    }else if(which==1){
                        // coding Hapus
                        database.bukudao().delete(list[position])
                        getData()
                    }else {
                        //coding batal
                        dialog.dismiss()
                    }
                })
                // menampilkan dialog view
                val dialogview = dialog.create()
                dialogview.show()

            }

        })

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        recyclerView.addItemDecoration(DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL))

        fab.setOnClickListener{
            startActivity(Intent(this, EditorActivity::class.java))
        }

    }

    override fun onResume() {
        super.onResume()
        getData()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun getData(){
        list.clear()
        list.addAll(database.bukudao().getAll())
        adapter.notifyDataSetChanged()
    }
}