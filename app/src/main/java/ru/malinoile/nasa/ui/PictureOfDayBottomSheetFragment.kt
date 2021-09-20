package ru.malinoile.nasa.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.malinoile.nasa.databinding.FragmentPictureInfoBinding
import ru.malinoile.nasa.model.entities.PictureOfDayEntity

class PictureOfDayBottomSheetFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentPictureInfoBinding? = null
    private val binding get() = _binding!!
    private var picture: PictureOfDayEntity? = null

    companion object {
        private const val PICTURE_EXTRA_KEY = "picture_extra_key"
        fun getInstance(picture: PictureOfDayEntity): PictureOfDayBottomSheetFragment {
            val fragment = PictureOfDayBottomSheetFragment()
            val bundle = Bundle()
            bundle.putParcelable(PICTURE_EXTRA_KEY, picture)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.let {
            picture = it.getParcelable(PICTURE_EXTRA_KEY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPictureInfoBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        picture?.let {
            binding.pictureTitleTextView.text = it.title
            binding.pictureDescTextView.text = it.description
            binding.pictureAuthorTextView.text = "© ${it.author}"
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }


}