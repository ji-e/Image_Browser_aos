package com.jieun.imagebrowser.utils

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jieun.imagebrowser.view.custom.adapter.BaseListAdapter
import java.text.SimpleDateFormat
import java.util.*


/**
 * date 2021-12-12
 * create by jieun
 */

@BindingAdapter("imgUrl", "err")
fun ImageView.loadImage(imgUrl: String?, err: Drawable) {
    Glide.with(context)
        .load(imgUrl)
        .error(err)
        .into(this)
}

@BindingAdapter("listAdapter")
fun RecyclerView.setListAdapter(list: List<Nothing>?) {
    (this.adapter as? BaseListAdapter.Adapter<*, *>)?.run {
        this.replaceAll(list)
    }
}

@BindingAdapter("onSafeClick")
fun View.setOnSafeClickListener(clickListener: View.OnClickListener?) {
    clickListener?.also {
        setOnClickListener(OnSafeClickListener(it))
    } ?: setOnClickListener(null)
}




