package com.robby1.daftarbukuperpustakaan.data.dao

import androidx.room.*
import com.robby1.daftarbukuperpustakaan.data.entity.Buku

@Dao
interface BukuDao {
    @Query("SELECT * FROM buku")
    fun getAll(): List<Buku>

    @Query("SELECT * FROM buku WHERE uid IN (:bukuIds)")
    fun loadAllByIds(bukuIds: IntArray): List<Buku>

    @Insert
    fun insertAll(vararg buku: Buku)

    @Delete
    fun delete(buku: Buku)

    @Query("SELECT * FROM buku WHERE uid = :uid")
    fun get(uid: Int) : Buku

    @Update
    fun update(buku: Buku)
}