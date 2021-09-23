package ru.malinoile.nasa

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.util.TypedValue
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import ru.malinoile.nasa.databinding.ActivityMainBinding
import ru.malinoile.nasa.model.FragmentContract
import ru.malinoile.nasa.ui.NavigationBottomSheetFragment
import ru.malinoile.nasa.ui.PictureOfDayFragment

private const val THEME_PREFERENCES_KEY = "shared_theme"
const val PICTURE_OF_DAY_TAG = "picture_of_day"

class MainActivity : AppCompatActivity(), FragmentContract {
    companion object {
        private const val THEME_EARTH = 1
        private const val THEME_EARTH_DARK = 2
    }

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        setCustomTheme()
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.bottomAppBar)

        setFragment(PictureOfDayFragment(), PICTURE_OF_DAY_TAG)

        binding.fab.setOnClickListener {
            changeDayNightMode()
        }
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

    private fun changeDayNightMode() {
        val prefs = getPreferences(Context.MODE_PRIVATE)
        val theme = when (prefs.getInt(THEME_PREFERENCES_KEY, THEME_EARTH)) {
            THEME_EARTH_DARK -> THEME_EARTH
            else -> THEME_EARTH_DARK
        }

        with(prefs.edit()) {
            putInt(THEME_PREFERENCES_KEY, theme)
            apply()
        }
        finish()
        startActivity(intent)
    }

    private fun setCustomTheme() {
        when (getPreferences(Context.MODE_PRIVATE)?.getInt(THEME_PREFERENCES_KEY, THEME_EARTH)) {
            THEME_EARTH_DARK -> {
                setTheme(R.style.Theme_NASA_Dark)
                changeIconColor(R.drawable.ic_burger, getColorForAttr(R.attr.colorOnPrimary))
                changeIconColor(R.drawable.ic_day_night, getColorForAttr(R.attr.colorOnPrimary))
            }
            else -> {
                setTheme(R.style.Theme_NASA)
                changeIconColor(R.drawable.ic_burger, getColorForAttr(R.attr.colorOnPrimary))
                changeIconColor(R.drawable.ic_day_night, getColorForAttr(R.attr.colorOnPrimary))
            }
        }
    }

    private fun getColorForAttr(attrId: Int): Int {
        val typedValue = TypedValue()
        theme.resolveAttribute(attrId, typedValue, true)
        return typedValue.data
    }

    private fun changeIconColor(resId: Int, color: Int) {
        val icon = AppCompatResources
            .getDrawable(this, resId)
            ?.let { DrawableCompat.wrap(it) }
        icon?.let {
            DrawableCompat.setTint(it, color)
        }
    }
}