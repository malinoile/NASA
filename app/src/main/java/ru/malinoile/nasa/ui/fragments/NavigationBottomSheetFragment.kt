package ru.malinoile.nasa.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.malinoile.nasa.PICTURE_OF_DAY_TAG
import ru.malinoile.nasa.R
import ru.malinoile.nasa.ROVER_PHOTOS_TAG
import ru.malinoile.nasa.databinding.FragmentBottomSheetBinding
import ru.malinoile.nasa.model.contracts.FragmentContract

class NavigationBottomSheetFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentBottomSheetBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context !is FragmentContract) {
            throw ClassCastException("Activity must implements FragmentContract")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBottomSheetBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeNavigationItemListener()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun initializeNavigationItemListener() {
        binding.bottomSheetNavigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.picture_of_the_day_nav -> {
                    setFragment(
                        getContract().searchFragment(PICTURE_OF_DAY_TAG)
                            ?: PicturePagerFragment(),
                        PICTURE_OF_DAY_TAG
                    )
                }
                R.id.rover_photos_nav -> {
                    setFragment(
                        getContract().searchFragment(ROVER_PHOTOS_TAG)
                            ?: RoverPhotoListFragment(),
                        ROVER_PHOTOS_TAG
                    )
                }
            }
            dismiss()
            true
        }
    }

    private fun getContract(): FragmentContract {
        return activity as FragmentContract
    }

    private fun setFragment(fragment: Fragment, tag: String?) {
        getContract().apply {
            setFragment(fragment, tag)
        }
    }

}