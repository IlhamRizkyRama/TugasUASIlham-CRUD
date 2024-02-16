package com.example.tugasuasilham_crud.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "notes")
@Parcelize
data class Note (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "title")
    var title: String = "",

    @ColumnInfo(name = "body")
    var body: String = "",

    @ColumnInfo(name = "nilai")
    var nilai: String = "",

    @ColumnInfo(name = "keterangan")
    var keterangan: String = "",

    @ColumnInfo(name = "jumlahsks")
    var jumlahsks: String = "",


    ):Parcelable