package ru.malinoile.nasa.ui.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import ru.malinoile.nasa.MyApplication
import ru.malinoile.nasa.databinding.FragmentRoverPhotoListBinding
import ru.malinoile.nasa.model.contracts.FragmentContract
import ru.malinoile.nasa.model.contracts.RoverPhotosContract
import ru.malinoile.nasa.model.entities.RoverPhotoEntity
import ru.malinoile.nasa.presenter.RoverPhotosPresenter
import ru.malinoile.nasa.ui.RoverPhotoListAdapter
import java.lang.RuntimeException

class RoverPhotoListFragment : Fragment(), RoverPhotosContract.View {
    private var _binding: FragmentRoverPhotoListBinding? = null
    private val binding get() = _binding!!
    private lateinit var roverAdapter: RoverPhotoListAdapter
    private val app: MyApplication by lazy { activity?.application as MyApplication }
    private val presenter: RoverPhotosContract.Presenter by lazy {
        RoverPhotosPresenter(app.roverPhotosRepository)
    }
    private var listRoverPhotos: List<RoverPhotoEntity>? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(activity !is FragmentContract) {
            throw RuntimeException("Activity must implement FragmentContract")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRoverPhotoListBinding.inflate(inflater)
        roverAdapter = RoverPhotoListAdapter {
            getContract().setFragment(PhotoFragment.newInstance(it), addBackStack = true)
        }
        presenter.attach(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.photoListRecyclerView) {
            layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
            animation = null
            setHasFixedSize(false)
            adapter = roverAdapter
        }

        binding.solEditText.setOnFocusChangeListener { _, b ->
            if(!b) {
                val sol = binding.solEditText.text.toString().toInt()
                presenter.onLoadPhotos(sol)
            } else {
                setErrorTextOfEditLayout(null)
            }
        }

        listRoverPhotos?.let {
            setAdapterList(it)
        }
    }

    override fun onDestroyView() {
        _binding = null
        presenter.detach()
        super.onDestroyView()
    }

    override fun renderList(list: List<RoverPhotoEntity>) {
        listRoverPhotos = list
        setAdapterList(list)
    }

    override fun renderErrorMessage(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        setErrorTextOfEditLayout(msg)
    }

    override fun setFocusable(isFocusable: Boolean) {
        binding.solEditText.isFocusable = isFocusable
        binding.solEditText.isClickable = isFocusable
    }

    private fun getContract(): FragmentContract {
        return activity as FragmentContract
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setAdapterList(list: List<RoverPhotoEntity>) {
        roverAdapter.apply {
            setList(list)
            notifyDataSetChanged()
        }
    }

    private fun setErrorTextOfEditLayout(msg: String?) {
        binding.solEditLayout.error = msg
    }

}