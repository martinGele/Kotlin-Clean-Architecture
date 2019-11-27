package com.martin.kotlinclean.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.martin.kotlinclean.R
import com.martin.kotlinclean.databinding.FragmentDetailBinding
import com.martin.kotlinclean.model.Animal

class DetailFragment : Fragment() {
    var animal: Animal? = null

    private lateinit var dataBinding: FragmentDetailBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        return dataBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let { animal = DetailFragmentArgs.fromBundle(it).animal }
        dataBinding.animal = animal
    }


}
