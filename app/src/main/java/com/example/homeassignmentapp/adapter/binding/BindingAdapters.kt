package com.example.homeassignmentapp

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("android:loadImage")
fun loadImageFromInternet(view: ImageView, imageSource: String?) {
    imageSource?.let {
        Picasso.get().load(imageSource).into(view)
    }
}