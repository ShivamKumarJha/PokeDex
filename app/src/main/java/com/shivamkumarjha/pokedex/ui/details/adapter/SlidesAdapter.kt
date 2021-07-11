package com.shivamkumarjha.pokedex.ui.details.adapter

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.palette.graphics.Palette
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.shivamkumarjha.pokedex.R
import com.shivamkumarjha.pokedex.databinding.ItemSlideBinding

class SlidesAdapter(private val images: ArrayList<String>) : PagerAdapter() {

    override fun isViewFromObject(view: View, `object`: Any): Boolean =
        view == `object` as LinearLayout

    override fun getCount(): Int = images.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding =
            ItemSlideBinding.inflate(LayoutInflater.from(container.context), container, false)
        val image = binding.image

        //Loader
        val circularProgressDrawable = CircularProgressDrawable(image.context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        //Image
        Glide.with(image.context)
            .asBitmap()
            .load(images[position])
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(circularProgressDrawable)

            .listener(object : RequestListener<Bitmap?> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Bitmap?>?,
                    isFirstResource: Boolean
                ): Boolean {
                    circularProgressDrawable.stop()
                    return false
                }

                override fun onResourceReady(
                    resource: Bitmap?,
                    model: Any?,
                    target: Target<Bitmap?>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    if (resource != null) {
                        val palette: Palette = Palette.from(resource).generate()
                        binding.container.setBackgroundColor(
                            palette.getDominantColor(image.context.getColor(R.color.white))
                        )
                    }
                    return false
                }
            })
            .into(image)

        container.addView(binding.root)
        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }
}