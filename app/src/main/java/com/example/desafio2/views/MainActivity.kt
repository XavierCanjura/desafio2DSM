package com.example.desafio2.views

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.desafio2.R
import com.example.desafio2.activities.RegisterActivity
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var productFragment: ProductsFragment = ProductsFragment()
    private var cartFragment: CartFragment = CartFragment()
    private var historyFragment: HistoryFragment = HistoryFragment()

    private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var userLogin: TextView
    private lateinit var user: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.getCredentials()

        val toolbar: Toolbar = findViewById(R.id.toolbar_main)
        this.setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawerContainer)

        toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)


        var header: View = navigationView.getHeaderView(0) // Obtener la vista del header del drawer
        userLogin = header.findViewById(R.id.nav_header_textView)

        userLogin.text = user

        productFragment.newInstance(user)
        loadFragment(productFragment)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_item_one -> {
                productFragment.newInstance(user)
                loadFragment(productFragment)
                drawer.closeDrawer(GravityCompat.START)
                return true
            }
            R.id.nav_item_two -> {
                cartFragment.newInstance(user)
                loadFragment(cartFragment)
                drawer.closeDrawer(GravityCompat.START)
                return true
            }
            R.id.nav_item_three -> {
                historyFragment.newInstance(user)
                loadFragment(historyFragment)
                drawer.closeDrawer(GravityCompat.START)
                return true
            }
            R.id.nav_item_four -> {
                this.logout()
                return true
            }
        }
        return false
    }

    override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onPostCreate(savedInstanceState, persistentState)
        toggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item))
        {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun loadFragment(fragment: Fragment)
    {
        var transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_container, fragment)
        transaction.commit()
    }

    private fun logout(){
        FirebaseAuth.getInstance().signOut().also {
            val credentials = getSharedPreferences("credentials", Context.MODE_PRIVATE)
            var editor = credentials.edit()
            editor.clear()
            editor.commit()
            Toast.makeText(this, "Session Cerrada", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun getCredentials(){
        val credentials = getSharedPreferences("credentials", Context.MODE_PRIVATE)
        val email = credentials.getString("correo", "")
        user = email.toString()
    }
}