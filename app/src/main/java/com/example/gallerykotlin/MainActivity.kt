package com.example.gallerykotlin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gallerykotlin.databinding.ActivityMainBinding
import com.example.gallerykotlin.model.ApiInterface
import com.example.gallerykotlin.model.ResponseDataItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), ItemOnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView


    //API code
    private val baseURL = "https://api.unsplash.com"
    private var apiInterface: ApiInterface? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding= ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        recyclerView=findViewById(R.id.recyclerView)
        recyclerView.layoutManager= LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        val gridLayoutManager = GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = gridLayoutManager

        //API methods
        networkLibraryInitializer()
        getMyData()


    }

    //Api code
    private fun networkLibraryInitializer() {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiInterface = retrofit.create(ApiInterface::class.java)
    }

    private fun getMyData() {

        val clintID = "9qQ2b3hOLsdJ8MFtaHl-iGXB-6GzyC1E872EAXHDwQ4"

        val responseDetails: Call<ArrayList<ResponseDataItem>> = apiInterface!!.getData(clintID, 1, 60)

        // responseDetails.enqueue(//inside this press ctrl+shift+space) , then all codes will come
        responseDetails.enqueue(object : Callback<ArrayList<ResponseDataItem>?> {
            override fun onResponse(
                call: Call<ArrayList<ResponseDataItem>?>,
                response: Response<ArrayList<ResponseDataItem>?>
            ) {
                val responseDetailsFromAPI= response.body()

                if (response.isSuccessful){
                    //recyclerview code
                    val programAdapter= responseDetailsFromAPI?.let { MainActRCVAdapter(it, this@MainActivity) }
                    recyclerView.adapter = programAdapter
                    Toast.makeText(this@MainActivity,"Successful", Toast.LENGTH_SHORT).show()

                }else{
                    Toast.makeText(this@MainActivity, response.message(), Toast.LENGTH_SHORT).show()
                }

            }

            override fun onFailure(call: Call<ArrayList<ResponseDataItem>?>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Failed to get user", Toast.LENGTH_SHORT).show()
            }
        })

    }

    override fun onImageClicked(imagePath: String?) {
        Log.e("image", "imagepath "+imagePath)
        //sending that image to another Activity
        val intent = Intent(this, FullScreenActivity::class.java)
        intent.putExtra("imageNumber", imagePath)
        startActivity(intent)
    }
}