package com.example.newsfeed.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsfeed.Adapters.UserNewsAdapter
import com.example.newsfeed.Model.NewsModal
import com.example.newsfeed.R
import com.google.firebase.database.*

class FetchingNewsUser : AppCompatActivity() {

    private lateinit var newsRecyclerView : RecyclerView
    private lateinit var tvLoadingDat : TextView
    private lateinit var nwsList:ArrayList<NewsModal>
    private lateinit var dbRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetching_news_user)

        newsRecyclerView = findViewById(R.id.rvEmp)
        newsRecyclerView.layoutManager = LinearLayoutManager(this)
        newsRecyclerView.setHasFixedSize(true)

        tvLoadingDat = findViewById(R.id.tvLoadingData)

        nwsList= arrayListOf<NewsModal>()
        getNewsData()


    }

    private fun getNewsData(){
        newsRecyclerView.visibility=View.GONE
        tvLoadingDat.visibility=View.VISIBLE

        dbRef=FirebaseDatabase.getInstance().getReference("News")

        dbRef.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                nwsList.clear()
                if(snapshot.exists()){
                    for (nwsSnap in snapshot.children){
                        val nwsData = nwsSnap.getValue(NewsModal::class.java)
                        nwsList.add(nwsData!!)
                    }
                    val mAdapter=UserNewsAdapter(nwsList)
                    newsRecyclerView.adapter=mAdapter

                    mAdapter.setOnItemClickListener(object : UserNewsAdapter.onItemClickListner{
                        override fun onItemClick(position: Int) {
                            val intent= Intent(this@FetchingNewsUser,NewsDetailActivityUser::class.java)

                            intent.putExtra("nwsID",nwsList[position].titleId)
                            intent.putExtra("nwsTitle",nwsList[position].newstitle)
                            intent.putExtra("nwsDescription",nwsList[position].newsdescript)
                            startActivity(intent)

                        }

                    })

                    newsRecyclerView.visibility=View.VISIBLE
                    tvLoadingDat.visibility=View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })
    }


}