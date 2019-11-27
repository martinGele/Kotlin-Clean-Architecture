package com.martin.kotlinclean.util

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.martin.kotlinclean.R


fun getProgressDrawable(contex: Context): CircularProgressDrawable {

    return CircularProgressDrawable(contex).apply {
        strokeWidth = 6f
        centerRadius = 50f
        start()
    }
}

fun ImageView.loadImage(uri: String?, progressDrawable: CircularProgressDrawable) {

    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_launcher_round)


    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(uri)
        .into(this)

}

@BindingAdapter("android:imageUrl")
fun loadImage(view: ImageView, uri: String?){
    view.loadImage(uri, getProgressDrawable(view.context))

}