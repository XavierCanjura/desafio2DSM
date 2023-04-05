package com.example.desafio2.views


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desafio2.adapters.CartAdapter
import com.example.desafio2.databinding.FragmentCartBinding
import com.example.desafio2.models.CartModel
import com.example.desafio2.viewmodels.CartViewModel


class CartFragment() : Fragment() {

    private var user: String = ""

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CartViewModel
    private var cartAdapter: CartAdapter? = null
    var carts: MutableList<CartModel> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        val view = binding.root

        initRecyclerView()

        viewModel = ViewModelProvider(this)[CartViewModel::class.java]
        viewModel.getCartByUser(user)
        viewModel.observerCartLiveData().observe(viewLifecycleOwner, Observer { cartList ->
            cartAdapter?.setCartList(cartList)
        })

        binding.btnComprar.setOnClickListener {
            viewModel.observerCartLiveData().observe(viewLifecycleOwner, Observer { cartList ->
                for (cart in cartList){
                    if(cart != null){
                        viewModel.updateCart(cart)
                    }

                }
            })
            Toast.makeText(context, "Se realizo la compra correctamente", Toast.LENGTH_SHORT).show()
        }

        return view
    }

    private fun initRecyclerView() {
        cartAdapter = CartAdapter(carts) {cart ->
            onItemSeleted(
                cart
            )

        }
        binding.rvCartList.layoutManager = LinearLayoutManager(context)
        binding.rvCartList.adapter = cartAdapter
    }

    private fun onItemSeleted(cart: CartModel) {
        viewModel.deleteCart(cart.id.toString())
    }


    fun newInstance(user: String) {
        this.user = user

    }
}