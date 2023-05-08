package com.robby1.daftarbukuperpustakaan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.robby1.daftarbukuperpustakaan.data.AppDatabase
import com.robby1.daftarbukuperpustakaan.data.entity.Buku

class EditorActivity : AppCompatActivity() {
    private lateinit var judul: EditText
    private lateinit var penulis: EditText
    private lateinit var penerbit: EditText
    private lateinit var thn_terbit: EditText
    private lateinit var btnsave: Button
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor)
        judul = findViewById(R.id.judul)
        penulis = findViewById(R.id.penulis)
        penerbit = findViewById(R.id.penerbit)
        thn_terbit = findViewById(R.id.thn_terbit)

        btnsave = findViewById(R.id.btn_save)

        database = AppDatabase.getInstance(applicationContext)

        val intent = intent.extras
        if (intent!=null){
            val id = intent.getInt("id", 0)
            var buku = database.bukudao().get(id)
            judul.setText(buku.judul)
            penulis.setText(buku.penulis)
            penerbit.setText(buku.penerbit)
            thn_terbit.setText(buku.thn_terbit)

        }

        btnsave.setOnClickListener {
            if (judul.text.isNotEmpty() && penulis.text.isNotEmpty() && penerbit.text.isNotEmpty()) {
                if (intent!=null){
                    // Coding Edit data
                    database.bukudao().update(
                        Buku(
                            intent.getInt("id", 0),
                            judul.text.toString(),
                            penulis.text.toString(),
                            penerbit.text.toString(),
                            thn_terbit.text.toString()
                        )
                    )

                }else{
                    // Coding tambah data
                    database.bukudao().insertAll(
                        Buku(
                            null,
                            judul.text.toString(),
                            penulis.text.toString(),
                            penerbit.text.toString(),
                            thn_terbit.text.toString()
                        )
                    )
                }

                finish()
            } else {
                Toast.makeText(
                    applicationContext,
                    "Silahkan Isi data dengan Valid",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
    }
}