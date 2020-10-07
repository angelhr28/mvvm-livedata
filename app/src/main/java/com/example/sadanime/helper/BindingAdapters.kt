package com.example.sadanime.helper

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.sadanime.R
import com.example.sadanime.helper.desing.CircleTransform
import com.example.sadanime.root.ctx
import com.squareup.picasso.Picasso

@BindingAdapter("visible")
fun View.bindVisible(visible:Boolean?){
    visibility = if (visible == true) View.VISIBLE else View.GONE
}
@BindingAdapter("PicassoCircle")
fun ImageView.bindPicassoCircle( img : String ){
    Picasso.get()
        .load(img)
        .placeholder(setCircularImage(ctx, R.drawable.empty_user))
        .error(setCircularImage(ctx, R.drawable.empty_user))
        .transform(CircleTransform())
        .into(this)
}

