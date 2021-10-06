package ru.malinoile.nasa.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.malinoile.nasa.databinding.FragmentPhotoBinding
import ru.malinoile.nasa.model.ImageUtils
import ru.malinoile.nasa.model.entities.RoverPhotoEntity

class PhotoFragment: Fragment() {
    private var _binding: FragmentPhotoBinding? = null
    private val binding get() = _binding!!
    private var photo: RoverPhotoEntity? = null

    companion object {
        private const val PHOTO_BUNDLE_KEY = "rover.photo.key"
        fun newInstance(photo: RoverPhotoEntity): PhotoFragment {
            val fragment = PhotoFragment()
            val bundle = Bundle()
            bundle.putParcelable(PHOTO_BUNDLE_KEY, photo)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.let {
            photo = it.getParcelable(PHOTO_BUNDLE_KEY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhotoBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        photo?.let {
            ImageUtils.renderImage(view.context, it.url, binding.roverImageView)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}