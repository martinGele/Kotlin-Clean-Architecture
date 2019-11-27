package com.martin.kotlinclean.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.martin.kotlinclean.R
import com.martin.kotlinclean.model.Animal
import com.martin.kotlinclean.util.getProgressDrawable
import com.martin.kotlinclean.util.loadImage
import kotlinx.android.synthetic.main.item_animal.view.*

class AnimalListAdapter(private val animlList: ArrayList<Animal>) :
    RecyclerView.Adapter<AnimalListAdapter.AnimalViewHolder>() {


    fun updateAnimalList(newAnimalList: List<Animal>){
        animlList.clear()
        animlList.addAll(newAnimalList)
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_animal, parent, false)

        return AnimalViewHolder(view)


    }

    override fun getItemCount() = animlList.size


    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        holder.itemView.animalName.text = animlList[position].name
        holder.itemView.animal_image.loadImage(animlList[position].imageUrl, getProgressDrawable(holder.itemView.context))

        holder.itemView.animalLayout.setOnClickListener {
            val action= ListFragmentDirections.actionDetail(animlList[position])

            Navigation.findNavController(holder.itemView).navigate(action)
        }
    }


    class AnimalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    }


}