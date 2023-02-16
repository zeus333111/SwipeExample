package com.example.swipeexample.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.swipeexample.databinding.ImageCartItemBinding


class ImagePagerAdapter(val data: List<Int>): RecyclerView.Adapter<ImagePagerAdapter.ImageHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        val binding = ImageCartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        holder.onBind(data[position])
    }

    class ImageHolder(val binding: ImageCartItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun onBind(resId: Int) = with(binding) {
            binding.ivPhoto.setImageResource(resId)
        }
    }
}