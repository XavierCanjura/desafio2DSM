package com.example.desafio2.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desafio2.R
import com.example.desafio2.adapters.CartAdapter
import com.example.desafio2.adapters.HistoryAdapter
import com.example.desafio2.databinding.FragmentHistoryBinding
import com.example.desafio2.models.CartModel
import com.example.desafio2.viewmodels.CartViewModel
import com.example.desafio2.viewmodels.HistoryViewModel

class HistoryFragment : Fragment() {

    private lateinit var user: String
    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HistoryViewModel
    private var historyAdapter: HistoryAdapter? = null
    var history: MutableList<CartModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val view = binding.root

        initRecyclerView()

        viewModel = ViewModelProvider(this)[HistoryViewModel::class.java]
        viewModel.getHistoryByUser(user)
        viewModel.observerHistoryLiveData().observe(viewLifecycleOwner, Observer { historyList ->
            historyAdapter?.setCartList(historyList)
        })


        return  view
    }

    private fun initRecyclerView() {
        historyAdapter = HistoryAdapter(history)
        binding.rvHistoryList.layoutManager = LinearLayoutManager(context)
        binding.rvHistoryList.adapter = historyAdapter
    }

    fun newInstance(user: String) {
        this.user = user
    }

}