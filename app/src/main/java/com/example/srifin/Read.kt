package com.example.srifin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class Read : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var categoryList: ArrayList<Catogeries>
    private lateinit var filteredList: ArrayList<Catogeries>
    private lateinit var dbRef: DatabaseReference
    private lateinit var searchView: SearchView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read)

        searchView = findViewById(R.id.searchbtn)

        dbRef = FirebaseDatabase.getInstance().reference.child("categories")

        recyclerView = findViewById(R.id.categoryRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        categoryList = arrayListOf()
        filteredList = arrayListOf()

        dbRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (data in snapshot.children){
                        val catogeries: Catogeries? = data.getValue(Catogeries::class.java)
                        if (catogeries != null) {
                            categoryList.add(catogeries)
                        }
                    }
                    filteredList.addAll(categoryList)
                    recyclerView.adapter = MyAdapter(filteredList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@Read, error.toString(), Toast.LENGTH_SHORT).show()
            }
        })

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    searchCategory(newText)
                }
                return true
            }
        })

    }

    private fun searchCategory(query: String) {
        filteredList.clear()
        categoryList?.forEach { category ->
            if (category.categoryname?.toLowerCase()?.contains(query.toLowerCase()) == true) {
                filteredList.add(category)
            }
        }
        recyclerView.adapter?.notifyDataSetChanged()
    }


}

