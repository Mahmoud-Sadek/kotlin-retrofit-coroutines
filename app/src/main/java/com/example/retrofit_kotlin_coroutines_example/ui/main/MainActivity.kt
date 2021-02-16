package com.example.retrofit_kotlin_coroutines_example.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit_kotlin_coroutines_example.R
import com.example.retrofit_kotlin_coroutines_example.pojo.PostResponse
import com.example.retrofit_kotlin_coroutines_example.utils.Status
import kotlinx.android.synthetic.main.activity_main.*

import com.example.retrofit_kotlin_coroutines_example.utils.Status.ERROR
import com.example.retrofit_kotlin_coroutines_example.utils.Status.LOADING
import com.example.retrofit_kotlin_coroutines_example.utils.Status.SUCCESS
class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: PostViewModel
    private lateinit var adapter: PostsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()
        setupUI()
        setupObservers()
    }


    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
                this).get(PostViewModel::class.java)
    }

    private fun setupUI() {
        listOfBreeds.layoutManager = LinearLayoutManager(this)
        adapter = PostsAdapter(arrayListOf())
        listOfBreeds.addItemDecoration(
                DividerItemDecoration(
                        listOfBreeds.context,
                        (listOfBreeds.layoutManager as LinearLayoutManager).orientation
                )
        )
        listOfBreeds.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.getPosts().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    SUCCESS -> {
                        listOfBreeds.visibility = View.VISIBLE
                        spinnerLoading.visibility = View.GONE
                        resource.data?.let { users -> retrieveList(users) }
                    }
                    ERROR -> {
                        listOfBreeds.visibility = View.VISIBLE
                        spinnerLoading.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    LOADING -> {
                        spinnerLoading.visibility = View.VISIBLE
                        listOfBreeds.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun retrieveList(users: List<PostResponse>) {
        adapter.apply {
            addUsers(users)
            notifyDataSetChanged()
        }
    }
}