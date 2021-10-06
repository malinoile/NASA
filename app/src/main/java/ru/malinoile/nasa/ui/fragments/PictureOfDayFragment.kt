package ru.malinoile.nasa.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import ru.malinoile.nasa.MyApplication
import ru.malinoile.nasa.databinding.FragmentPictureOfDayBinding
import ru.malinoile.nasa.model.ImageUtils
import ru.malinoile.nasa.model.contracts.PictureOfDayContract
import ru.malinoile.nasa.model.entities.MediaType
import ru.malinoile.nasa.model.entities.PictureOfDayEntity
import ru.malinoile.nasa.presenter.PictureOfDayPresenter

class PictureOfDayFragment : Fragment() {
    private var _binding: FragmentPictureOfDayBinding? = null
    private val binding get() = _binding!!
    private var pictureOfDay: PictureOfDayEntity? = null

    companion object {
        private const val PICTURE_BUNDLE_KEY = "picture.key"
        fun newInstance(picture: PictureOfDayEntity): PictureOfDayFragment {
            val fragment = PictureOfDayFragment()
            val bundle = Bundle()
            bundle.putParcelable(PICTURE_BUNDLE_KEY, picture)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.let {
            pictureOfDay = it.getParcelable(PICTURE_BUNDLE_KEY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPictureOfDayBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pictureOfDay?.let {
            renderPicture(it)
        }
        binding.pictureOfDayImageView.setOnClickListener {
            pictureOfDay?.let { picture ->
                PictureOfDayBottomSheetFragment.getInstance(picture).show(
                    requireActivity().supportFragmentManager, null
                )
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun renderPicture(picture: PictureOfDayEntity) {
        ImageUtils.renderImage(
            requireContext(),
            getPictureURL(picture),
            binding.pictureOfDayImageView
        )
    }

    private fun getPictureURL(picture: PictureOfDayEntity): String? {
        return when (picture.getMediaType()) {
            MediaType.PHOTO -> {
                picture.url
            }
            MediaType.VIDEO -> {
                picture.thumbnailUrl
            }
        }
    }
}