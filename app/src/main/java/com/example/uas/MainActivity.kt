package com.example.uas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView : RecyclerView

    private lateinit var foodAdapter : FoodListAdapter
    private var LayoutManager : RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.rv_food_list)
        LayoutManager = LinearLayoutManager(this)

        recyclerView.layoutManager = LayoutManager
        getListFood()

    }

    fun getListFood(){
        val client = AsyncHttpClient()
        val url = "https://www.themealdb.com/api/json/v1/1/filter.php?c=Seafood"
        client.get(url, object : AsyncHttpResponseHandler(){

            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?) {
                val result = String(responseBody!!)
                val data = JSONObject(result)
                val dataArray = data.getJSONArray("meals")
                foodAdapter = FoodListAdapter(dataArray)
                recyclerView.adapter = foodAdapter

            }
            override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?, error: Throwable?) {
                Toast.makeText(this@MainActivity, "errorMessage", Toast.LENGTH_LONG).show()
            }

        })
    }

}