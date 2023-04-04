package com.example.desafio2.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio2.R
import com.example.desafio2.adapters.ProductAdapter
import com.example.desafio2.databinding.ActivityProductBinding
import com.example.desafio2.models.ProductModel
import com.example.desafio2.viewmodels.ProductViewModel
import com.google.firebase.database.*

class ProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductBinding
    private lateinit var viewModel: ProductViewModel
    private var productAdapter: ProductAdapter? = null
    var products: MutableList<ProductModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()

        viewModel = ViewModelProvider(this)[ProductViewModel::class.java]
        viewModel.getProducts()
        viewModel.observerProductLiveData().observe(this, Observer { productList ->
            productAdapter?.setProductList(productList)
        })
    }

    private fun initRecyclerView(){
        productAdapter = ProductAdapter(products)
        binding.rvProductsList2.layoutManager = LinearLayoutManager(this)
        binding.rvProductsList2.adapter = productAdapter
    }

//    private fun init(){
//        productList = findViewById(R.id.rvProductsList2)
//
//        products = ArrayList<ProductModel>()
//
//        consultaOrdenada.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                products!!.removeAll(products!!)
//                for(data in snapshot.children){
//                    val product = data.getValue(ProductModel::class.java)
//                    if(product != null){
//                        (products as ArrayList<ProductModel>).add(product)
//                    }
//                }
//
//                val adapter = ProductAdapter(products as ArrayList<ProductModel>)
//                productList?.layoutManager = LinearLayoutManager(this)
//                productList?.adapter = adapter
//                adapter.notifyDataSetChanged()
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//
//        })
//    }


//    companion object {
//        var database: FirebaseDatabase = FirebaseDatabase.getInstance()
//        var refProducts: DatabaseReference = database.getReference("products")
//    }
}