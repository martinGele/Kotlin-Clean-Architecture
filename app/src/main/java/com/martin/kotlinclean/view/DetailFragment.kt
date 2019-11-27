package com.martin.kotlinclean.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.martin.kotlinclean.R
import com.martin.kotlinclean.model.Animal
import com.martin.kotlinclean.util.getProgressDrawable
import com.martin.kotlinclean.util.loadImage
import kotlinx.android.synthetic.main.fragment_detail.*

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {


    var animal: Animal?  = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        arguments?.let {
            animal= DetailFragmentArgs.fromBundle(it).animal
        }

        context?.let {
            animalImage.loadImage(animal?.imageUrl, getProgressDrawable(it))

        }

        animalName.text= animal?.name
        animalLocation.text= animal?.location
        animalLifespan.text= animal?.lifeSpan
        animalDiet.text= animal?.diet



    }


}
