package com.martin.kotlinclean.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.martin.kotlinclean.R
import com.martin.kotlinclean.databinding.ItemAnimalBinding
import com.martin.kotlinclean.model.Animal
import kotlinx.android.synthetic.main.item_animal.view.*

class AnimalListAdapter(private val animlList: ArrayList<Animal>) :
    RecyclerView.Adapter<AnimalListAdapter.AnimalViewHolder>() {


    fun updateAnimalList(newAnimalList: List<Animal>) {
        animlList.clear()
        animlList.addAll(newAnimalList)
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemAnimalBinding>(inflater, R.layout.item_animal, parent, false)

        return AnimalViewHolder(view)


    }

    override fun getItemCount() = animlList.size


    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {

        holder.view.animal = animlList[position]
        holder.itemView.animalLayout.setOnClickListener {
            val action = ListFragmentDirections.actionDetail(animlList[position])

            Navigation.findNavController(holder.itemView).navigate(action)
        }
    }


    class AnimalViewHolder(var view: ItemAnimalBinding) : RecyclerView.ViewHolder(view.root)


}