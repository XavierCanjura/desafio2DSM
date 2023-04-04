package com.example.desafio2.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio2.R
import com.example.desafio2.adapters.ProductAdapter
import com.example.desafio2.databinding.FragmentProductsBinding
import com.example.desafio2.models.ProductModel
import com.example.desafio2.viewmodels.ProductViewModel
import com.google.firebase.database.*

class ProductsFragment : Fragment() {

    private var _binding:FragmentProductsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ProductViewModel
    private var productAdapter: ProductAdapter? = null
    var products: MutableList<ProductModel> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductsBinding.inflate(inflater,container,false)
        val view = binding.root

        initRecyclerView()

        viewModel = ViewModelProvider(this)[ProductViewModel::class.java]
        viewModel.getProducts()
        viewModel.observerProductLiveData().observe(viewLifecycleOwner, Observer { productList ->
            productAdapter?.setProductList(productList)
        })
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initRecyclerView(){
        productAdapter = ProductAdapter(products)
        binding.rvProductsList.layoutManager = LinearLayoutManager(context)
        binding.rvProductsList.adapter = productAdapter
    }

    companion object {
        var database: FirebaseDatabase = FirebaseDatabase.getInstance()
        var refProducts: DatabaseReference = database.getReference("products")
    }
}