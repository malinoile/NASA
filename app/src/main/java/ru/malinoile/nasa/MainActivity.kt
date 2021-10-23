package ru.malinoile.nasa

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.malinoile.nasa.databinding.ActivityMainBinding
import ru.malinoile.nasa.model.contracts.FragmentContract
import ru.malinoile.nasa.ui.fragments.PicturePagerFragment
import ru.malinoile.nasa.ui.fragments.RoverPhotoListFragment

private const val THEME_PREFERENCES_KEY = "shared_theme"
const val PICTURE_OF_DAY_TAG = "picture_of_day"
const val ROVER_PHOTOS_TAG = "rover_photos"

class MainActivity : AppCompatActivity(), FragmentContract {
    private var isMenuExpanded = false

    companion object {
        private const val THEME_EARTH = 1
        private const val THEME_EARTH_DARK = 2
        private const val NAVIGATION_DURATION = 300L
    }

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        setCustomTheme()
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setFabMenu()

        setFragment(PicturePagerFragment(), PICTURE_OF_DAY_TAG)
    }

    private fun setCustomTheme() {
        when (getPreferences(Context.MODE_PRIVATE)?.getInt(THEME_PREFERENCES_KEY, THEME_EARTH)) {
            THEME_EARTH_DARK -> {
                setTheme(R.style.Theme_NASA_Dark)
            }
            else -> {
                setTheme(R.style.Theme_NASA)
            }
        }
    }

    private fun setFabMenu() {
        initializationState()

        binding.fab.setOnClickListener {
            if (isMenuExpanded) {
                collapseMenu()
            } else {
                expandMenu()
            }
        }
    }

    private fun initializationState() {
        hideViews(
            binding.optionPhotosCuriosity,
            binding.optionSettings,
            binding.navigationBackground,
            binding.optionPictureOfDayLayout
        )
    }

    private fun hideViews(vararg views: View) {
        views.forEach {
            it.apply {
                alpha = 0f
                isClickable = false
            }
        }
    }

    private fun expandMenu() {
        Log.d("@@@", "expandMenu")
        isMenuExpanded = true

        ObjectAnimator.ofFloat(binding.optionSettings, "translationY", -150f).start()
        ObjectAnimator.ofFloat(binding.optionPictureOfDayLayout, "translationY", -240f).start()
        ObjectAnimator.ofFloat(binding.optionPhotosCuriosity, "translationY", -330f).start()

        fabAnimation(binding.navigationBackground, 0.6f) {
            collapseMenu()
        }
        fabAnimation(binding.optionPictureOfDayLayout, 1f) {
            setFragment(
                searchFragment(PICTURE_OF_DAY_TAG) ?: PicturePagerFragment(),
                PICTURE_OF_DAY_TAG
            )
            collapseMenu()
        }
        fabAnimation(binding.optionPhotosCuriosity, 1f) {
            setFragment(
                searchFragment(ROVER_PHOTOS_TAG) ?: RoverPhotoListFragment(),
                ROVER_PHOTOS_TAG
            )
            collapseMenu()
        }
        fabAnimation(binding.optionSettings, 1f) {
            Toast.makeText(this@MainActivity, "Settings", Toast.LENGTH_SHORT).show()
            collapseMenu()
        }
    }

    private fun collapseMenu() {
        isMenuExpanded = false

        ObjectAnimator.ofFloat(binding.optionPictureOfDayLayout, "translationY", 0f).start()
        ObjectAnimator.ofFloat(binding.optionPhotosCuriosity, "translationY", 0f).start()
        ObjectAnimator.ofFloat(binding.optionSettings, "translationY", 0f).start()

        fabAnimation(binding.navigationBackground, 0f, null)
        fabAnimation(binding.optionPictureOfDayLayout, 0f, null)
        fabAnimation(binding.optionPhotosCuriosity, 0f, null)
        fabAnimation(binding.optionSettings, 0f, null)
    }

    private fun fabAnimation(view: View, alpha: Float, clickListener: ((View) -> Unit)?) {
        view.animate()
            .alpha(alpha)
            .setDuration(NAVIGATION_DURATION)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    view.isClickable = isMenuExpanded
                    clickListener?.let { listener ->
                        view.setOnClickListener { listener(it) }
                    } ?: view.setOnClickListener(null)
                    if (alpha > 0) {
                        view.visibility = View.VISIBLE
                    } else {
                        view.visibility = View.INVISIBLE
                    }
                }
            })
    }

    override fun setFragment(fragment: Fragment, tag: String?, addBackStack: Boolean) {
        val transaction = supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment, tag)
        if (addBackStack) transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun searchFragment(tag: String): Fragment? {
        return supportFragmentManager.findFragmentByTag(tag)
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
        recreate()
    }

}