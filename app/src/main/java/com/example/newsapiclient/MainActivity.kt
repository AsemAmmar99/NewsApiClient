package com.example.newsapiclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.newsapiclient.databinding.ActivityMainBinding
import com.example.newsapiclient.presentation.adapter.NewsAdapter
import com.example.newsapiclient.presentation.viewmodel.NewsViewModel
import com.example.newsapiclient.presentation.viewmodel.NewsViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: NewsViewModelFactory
    @Inject
    lateinit var newsAdapter: NewsAdapter
    lateinit var viewModel: NewsViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bnvNews.setupWithNavController(
            navController
        )

        viewModel = ViewModelProvider(this, factory)[NewsViewModel::class.java]
    }
}