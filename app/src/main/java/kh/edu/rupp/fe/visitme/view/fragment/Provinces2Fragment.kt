package kh.edu.rupp.fe.visitme.view.fragment

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kh.edu.rupp.fe.visitme.databinding.FragmentProvincesBinding
import kh.edu.rupp.fe.visitme.model.api.model.Province
import kh.edu.rupp.fe.visitme.model.api.model.Status
import kh.edu.rupp.fe.visitme.model.api.service.ApiService
import kh.edu.rupp.fe.visitme.presenter.ProvincesPresenter
import kh.edu.rupp.fe.visitme.ui.adapter.ProvincesAdapter2
import kh.edu.rupp.fe.visitme.view.ProvincesView
import kh.edu.rupp.fe.visitme.viewmodel.ProvincesViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Provinces2Fragment: Fragment() {

    private lateinit var binding: FragmentProvincesBinding

    private val viewModel = ProvincesViewModel()

    //private lateinit var adapter: ProvincesAdapter2
    private val adapter = ProvincesAdapter2()
    //private var adapter: ProvincesAdapter2? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentProvincesBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup recycler view
        //// Create layout manager
        val layoutManager = LinearLayoutManager(context)
        binding.recyclerView.layoutManager = layoutManager

        //// setup recyclerview
        adapter.onProvinceClickListener = {_: Int, province: Province ->

        }
        binding.recyclerView.adapter = adapter

        viewModel.loadProvinces()

        // Setup observer
        viewModel.provinceData.observe(viewLifecycleOwner) {
            when(it.status) {
                Status.PROCESSING -> Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                Status.SUCCESS -> adapter.submitList(it.data)
                Status.ERROR -> Toast.makeText(requireContext(), "Error while loading data from server", Toast.LENGTH_LONG).show()
            }
        }
    }

}