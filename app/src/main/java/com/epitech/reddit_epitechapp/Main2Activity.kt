package com.epitech.reddit_epitechapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.epitech.reddit_epitechapp.repository.Repository
import com.epitech.reddit_epitechapp.ui.home.DefaultList.DefaultListFragment
import com.epitech.reddit_epitechapp.ui.home.news.NewsFragment
import com.epitech.reddit_epitechapp.ui.home.popular.PopularFragment
import com.epitech.reddit_epitechapp.ui.profile.MyProfileFragment
import com.epitech.reddit_epitechapp.ui.setting.SettingFragment
import com.epitech.reddit_epitechapp.utils.Constants.Companion.ACCESS_TOKEN
import com.epitech.reddit_epitechapp.viewModel.ViewModels.MainViewModel
import com.epitech.reddit_epitechapp.viewModel.ViewModelsFactory.MainViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.app_bar_main2.*


var TOKEN : String = ""
var THIS: Main2Activity? = null

class Main2Activity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var bottomNavigationView: BottomNavigationView
    private val myProfileFragment = MyProfileFragment()
    private val settingFragment = SettingFragment()
    private lateinit var appBarConfiguration: AppBarConfiguration
    private var popularAfter: String = "t5_2yrq6"
    private var defaultListAfter: String = "t5_2qh7a"
    private var newAfter: String = "t5_5awblv"

    // setup fragment
    private val newsFragment = NewsFragment()
    private val defaultListFragment = DefaultListFragment()
    private val popularFragment = PopularFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val message = intent.getStringExtra(ACCESS_TOKEN)
        bottomNavigationView = findViewById(R.id.bottomNavigationBar)
        TOKEN = message.toString()
        Log.e("Token", TOKEN)

        setupConfiguration()
        THIS = this
        SetupActionBar()

        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.ic_home -> replaceFragments(defaultListFragment)
                R.id.ic_news -> replaceFragments(newsFragment)
                R.id.ic_popular -> replaceFragments(popularFragment)
            }
            true
        }

        nav_view.setNavigationItemSelectedListener {dest ->
            when(dest.itemId) {
                R.id.nav_profile -> replaceFragments(myProfileFragment)
                R.id.nav_setting -> replaceFragments(settingFragment)
            }
            true
        }
    }

    private fun SetupActionBar() {
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        val toggle = ActionBarDrawerToggle(
            this,
            drawer_layout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun setupConfiguration() {

        var navigationView = findViewById<View>(R.id.nav_view) as NavigationView
        var headerView = navigationView.getHeaderView(0)

        var navUsername = headerView.findViewById<View>(R.id._username) as TextView
        var navUserDescription = headerView.findViewById<View>(R.id._userDescription) as TextView
        var navProfilePicture = headerView.findViewById<View>( R.id._profilePicture) as ImageView

        val repository = Repository()
        val HomeViewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, HomeViewModelFactory).get(MainViewModel::class.java)
        viewModel.getMainData()
        viewModel.rep.observe(this, Observer { rep ->
            if (rep.isSuccessful) {
                Log.d("Response", rep.body()?.subreddit?.display_name.toString())
                Log.d("Response", rep.body()?.subreddit?.icon_img.toString())
                Log.d("Response", rep.body()?.subreddit?.public_description.toString())

                // Setup UI
                navUsername.text = rep.body()?.subreddit?.display_name.toString()
                Picasso.get().load(rep.body()?.subreddit?.icon_img.toString().replaceAfter(".png","")).into(navProfilePicture)
                navUserDescription.text = rep.body()?.subreddit?.public_description.toString()
            } else {
                Log.e("Error response", rep.code().toString())
                Log.e("Error response", rep.message().toString())
            }
        })


    }

    internal fun getAbstract():  AppCompatActivity {
        return this
    }

    private fun replaceFragments(fragment: Fragment) {
        if (fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_view)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    fun getAfterPopular(): String {
        return popularAfter
    }

    fun setAfterPopular(string: String) {
        popularAfter = string
    }

    fun getAfterDefaultList(): String {
        return defaultListAfter
    }

    fun setAfterDefaultList(string: String) {
        defaultListAfter = string
    }

    fun getAfterNew(): String {
        return newAfter
    }

    fun setAfterNew(string: String) {
        newAfter = string
    }
}