package ru.malinoile.nasa.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import ru.malinoile.nasa.MyApplication
import ru.malinoile.nasa.databinding.FragmentPictureOfDayBinding
import ru.malinoile.nasa.model.PictureOfDayContract
import ru.malinoile.nasa.model.entities.MediaType
import ru.malinoile.nasa.model.entities.PictureOfDayEntity
import ru.malinoile.nasa.presenter.PictureOfDayPresenter

class PictureOfDayFragment : Fragment(), PictureOfDayContract.View {
    private var _binding: FragmentPictureOfDayBinding? = null
    private val binding get() = _binding!!
    private val app: MyApplication by lazy { activity?.application as MyApplication }
    private lateinit var presenter: PictureOfDayContract.Presenter
    private var pictureOfDay: PictureOfDayEntity? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPictureOfDayBinding.inflate(inflater)
        presenter = PictureOfDayPresenter.invoke(app.retrofit)
        presenter.attach(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickableImageView(false)
        presenter.getPictureOfDay()


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
        presenter.detach()
        super.onDestroyView()
    }

    override fun renderPicture(picture: PictureOfDayEntity) {
        pictureOfDay = picture
        if (picture.getMediaType() == MediaType.PHOTO) {
            Glide.with(this)
                .load(picture.url)
                .into(binding.pictureOfDayImageView)
        }
        setClickableImageView(true)
    }

    override fun renderErrorToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }

    private fun setClickableImageView(clickable: Boolean) {
        binding.pictureOfDayImageView.isClickable = clickable
    }
}