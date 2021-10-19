package com.example.newsapiclient

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapiclient.databinding.FragmentSavedBinding
import com.example.newsapiclient.presentation.adapter.NewsAdapter
import com.example.newsapiclient.presentation.viewmodel.NewsViewModel

class SavedFragment : Fragment() {
    private lateinit var binding: FragmentSavedBinding
    private lateinit var viewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_saved, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSavedBinding.bind(view)
        viewModel = (activity as MainActivity).viewModel
        newsAdapter = (activity as MainActivity).newsAdapter
        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("selected_article", it)
            }
            findNavController().navigate(
                R.id.action_newsFragment_to_infoFragment,
                bundle
            )
        }
        initRecyclerView()
        viewModel.getSavedNews().observe(viewLifecycleOwner, {
            newsAdapter.differ.submitList(it)
        })

        val itemTouchHelperCallback = object: ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.START or ItemTouchHelper.END
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val article = newsAdapter.differ.currentList[position]
                viewModel.deleteArticle(article)
                Toast.makeText(activity, "Deleted Successfully!", Toast.LENGTH_LONG).show()
            }

        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.rvSaved)
        }
    }

    private fun initRecyclerView() {
        binding.rvSaved.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}