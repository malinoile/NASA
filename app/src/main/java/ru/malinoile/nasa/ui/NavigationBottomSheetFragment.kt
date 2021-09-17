package ru.malinoile.nasa.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.malinoile.nasa.PICTURE_OF_DAY_TAG
import ru.malinoile.nasa.R
import ru.malinoile.nasa.databinding.FragmentBottomSheetBinding
import ru.malinoile.nasa.model.FragmentContract
import ru.malinoile.nasa.model.PictureOfDayContract

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
        binding.bottomSheetNavigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.picture_of_the_day_nav -> {
                    getContract().setFragment(
                        requireActivity()
                            .supportFragmentManager
                            .findFragmentByTag(PICTURE_OF_DAY_TAG)
                            ?: PictureOfDayFragment(),
                        PICTURE_OF_DAY_TAG
                    )
                }
            }
            dismiss()
            true
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun getContract(): FragmentContract {
        return activity as FragmentContract
    }

}