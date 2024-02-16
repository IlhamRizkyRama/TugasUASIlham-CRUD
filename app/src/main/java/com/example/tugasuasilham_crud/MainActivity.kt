package com.example.tugasuasilham_crud

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tugasuasilham_crud.databinding.ActivityMainBinding
import com.example.tugasuasilham_crud.db.NoteRoomDatabase
import com.example.tugasuasilham_crud.model.Note

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val isDataInserted = sharedPreferences.getBoolean("isDataInserted", false)

        if (!isDataInserted) {
            addDummyData()
            sharedPreferences.edit().putBoolean("isDataInserted", true).apply()
        }

        getNotesDate()

        binding.floatingAdd.setOnClickListener {
            startActivity(Intent(this, EditActivity::class.java))
        }
    }

    private fun addDummyData() {
        val database = NoteRoomDatabase.getDatabase(application)
        val dao = database.getNoteDao()

        val dummyDataList = listOf(
            Note(
                title = "2169700041",
                body = "Ilham Rizky Ramadhan",
                nilai = "95",
                keterangan = "Lulus",
                jumlahsks = "21",
            )
        )

        for (dummyNote in dummyDataList) {
            dao.insert(dummyNote)
        }
    }

    //getNotesdata
    private fun getNotesDate() {
        val database = NoteRoomDatabase.getDatabase(application)
        val dao = database.getNoteDao()
        val listItems = arrayListOf<Note>()
        listItems.addAll(dao.getAll())
        setupRecyclerView(listItems)
    }

    private fun setupRecyclerView(listItems: ArrayList<Note>) {
        binding.recycleViewMain.apply {
            adapter = NoteAdapter(listItems, object : NoteAdapter.NoteListener {
                override fun OnItemClicked(note: Note) {
                    val intent = Intent(this@MainActivity, EditActivity::class.java)
                    intent.putExtra(EditActivity().EDIT_NOTE_EXTRA, note)
                    startActivity(intent)
                }
            })

            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    override fun onResume() {
        super.onResume()
        getNotesDate()
    }

}