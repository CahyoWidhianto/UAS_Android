package com.example.uas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.json.JSONArray

class FoodListAdapter(private val foodList : JSONArray) : RecyclerView.Adapter<FoodListAdapter.ViewHolder>()
{
    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val foodName : TextView = view.findViewById(R.id.tv_text)
        val foodImgUrl : ImageView = view.findViewById(R.id.iv_image)


        fun bind(foodNa : String, foodUrl : String){
            foodName.text = foodNa
            Picasso.get().load(foodUrl).into(foodImgUrl);
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.test, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodListAdapter.ViewHolder, position: Int) {
        val foodObject = foodList.getJSONObject(position)
        val nameOfFood = foodObject.getString("strMeal")
        val urlOfFood = foodObject.getString("strMealThumb")

        holder.bind(nameOfFood, urlOfFood)
    }

    override fun getItemCount(): Int {
        return foodList.length()
    }


}