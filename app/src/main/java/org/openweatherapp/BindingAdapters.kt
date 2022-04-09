package org.openweatherapp

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("weatherIcon")
fun weatherIcon(view: ImageView, icon: String?) {
    Picasso.get()
        .load("https://openweathermap.org/img/wn/$icon@2x.png")
        .into(view)
}