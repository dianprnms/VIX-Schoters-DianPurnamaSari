package com.example.vix_schoters_dianpurnamasari.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.vix_schoters_dianpurnamasari.R
import com.example.vix_schoters_dianpurnamasari.databinding.FragmentSourceBinding
import com.example.vix_schoters_dianpurnamasari.view.adapter.SourceAdapter
import com.example.vix_schoters_dianpurnamasari.viewmodel.SourceViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Source : Fragment() {
    lateinit var binding: FragmentSourceBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSourceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getDestinasiFavorite()

        binding.btnToProfile.setOnClickListener {
            findNavController().navigate(R.id.action_source_to_profile)
        }
    }

    fun getDestinasiFavorite(){
        var viewModel = ViewModelProvider(this)[SourceViewModel::class.java]
        viewModel.callApiSource()
        viewModel.dataSource.observe(viewLifecycleOwner, androidx.lifecycle.Observer { dataSource ->
            dataSource?.let {
                if (it != null) {
                    binding.rvSource.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                    binding.rvSource.adapter = SourceAdapter(it)
                }
            }
        })
    }


}