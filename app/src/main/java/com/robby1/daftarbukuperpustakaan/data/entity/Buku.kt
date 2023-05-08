package com.robby1.daftarbukuperpustakaan.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Buku(
    @PrimaryKey(autoGenerate = true) var uid: Int? = null,
    @ColumnInfo(name = "judul_buku") var judul: String?,
    @ColumnInfo(name = "penulis") var penulis: String?,
    @ColumnInfo(name = "penerbit") var penerbit: String?,
    @ColumnInfo(name = "tahun_terbit") var thn_terbit: String?
)