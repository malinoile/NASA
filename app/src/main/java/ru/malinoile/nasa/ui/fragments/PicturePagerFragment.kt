package ru.malinoile.nasa.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import ru.malinoile.nasa.MyApplication
import ru.malinoile.nasa.databinding.FragmentPagerBinding
import ru.malinoile.nasa.model.contracts.PictureOfDayContract
import ru.malinoile.nasa.model.entities.PictureOfDayEntity
import ru.malinoile.nasa.presenter.PictureOfDayPresenter
import ru.malinoile.nasa.ui.adapters.PicturesPagerAdapter

class PicturePagerFragment : Fragment(), PictureOfDayContract.View {
    private var _binding: FragmentPagerBinding? = null
    private val binding get() = _binding!!
    private val app: MyApplication by lazy { activity?.application as MyApplication }
    private val presenter: PictureOfDayContract.Presenter by lazy {
        PictureOfDayPresenter(app.pictureRepository)
    }
    private var listPictures: List<PictureOfDayEntity>? = null
    private val pagerAdapter: PicturesPagerAdapter by lazy {
        PicturesPagerAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPagerBinding.inflate(inflater)
        presenter.attach(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.adapter = pagerAdapter
        listPictures?.let {
            renderPictures(it)
        } ?: presenter.getPicturesOfDay(requireContext())
    }

    override fun onDestroyView() {
        _binding = null
        presenter.detach()
        super.onDestroyView()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun renderPictures(list: List<PictureOfDayEntity>) {
        listPictures = list
        pagerAdapter.setList(list)
        pagerAdapter.notifyDataSetChanged()
    }

    override fun renderErrorMessage(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }

}