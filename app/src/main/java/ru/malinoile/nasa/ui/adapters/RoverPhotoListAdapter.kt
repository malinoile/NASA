package ru.malinoile.nasa.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.malinoile.nasa.R
import ru.malinoile.nasa.databinding.CardPhotoRoverBinding
import ru.malinoile.nasa.model.ImageUtils
import ru.malinoile.nasa.model.entities.RoverPhotoEntity

class RoverPhotoListAdapter(
    private val listener: (RoverPhotoEntity) -> Unit
) : RecyclerView.Adapter<RoverPhotoHolder>() {
    private var listPhotos: List<RoverPhotoEntity> = emptyList()

    fun setList(list: List<RoverPhotoEntity>) {
        listPhotos = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoverPhotoHolder {
        return RoverPhotoHolder(parent)
    }

    override fun onBindViewHolder(holder: RoverPhotoHolder, position: Int) {
        val photo = listPhotos[position]
        holder.bind(photo)
        holder.itemView.setOnClickListener { listener(photo)  }
    }

    override fun getItemCount(): Int = listPhotos.size
}

class RoverPhotoHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.card_photo_rover, parent, false)
) {
    private var binding: CardPhotoRoverBinding = CardPhotoRoverBinding.bind(itemView)
    private var photo: RoverPhotoEntity? = null

    fun bind(roverPhoto: RoverPhotoEntity) {
        photo = roverPhoto
        ImageUtils.renderImage(itemView.context, roverPhoto.url, binding.roverImageView)
    }

}