package ru.malinoile.nasa

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.malinoile.nasa.databinding.ActivityMainBinding
import ru.malinoile.nasa.model.FragmentContract
import ru.malinoile.nasa.ui.NavigationBottomSheetFragment
import ru.malinoile.nasa.ui.PictureOfDayFragment

const val PICTURE_OF_DAY_TAG = "picture_of_day"

class MainActivity : AppCompatActivity(), FragmentContract {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.bottomAppBar)

        setFragment(PictureOfDayFragment(), PICTURE_OF_DAY_TAG)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> NavigationBottomSheetFragment().show(supportFragmentManager, null)
        }
        return true
    }

    override fun setFragment(fragment: Fragment, tag: String?, addBackStack: Boolean) {
        val transaction = supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment, tag)
        if (addBackStack) transaction.addToBackStack(null)
        transaction.commit()
    }
}