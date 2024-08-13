package com.example.news.tabLayoutFRAG

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news.R
import com.example.news.adapter.CategoryAdapter
import com.example.news.api.RetrofitInstance
import com.example.news.apiModels.Article
import com.example.news.apiModels.NewsResponse
import com.example.news.databinding.FragmentBreakingBinding
import com.example.news.databinding.FragmentTechnologyBinding
import retrofit2.Call
import retrofit2.Response
import java.util.ArrayList

class TechnologyFragment : Fragment() {

    private lateinit var binding: FragmentTechnologyBinding
    private lateinit var adapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTechnologyBinding.inflate(inflater, container, false)

        //  RecyclerView :->
        binding.technologyRyc.layoutManager = LinearLayoutManager(requireContext())
        binding.technologyRyc.setHasFixedSize(true)

        // Shimmer effect :->
        binding.shimmerLayout.startShimmer()

        getNews()
        return binding.root
    }

    private fun getNews(){
        RetrofitInstance.apiInterface.getData("in", "technology",1, "dda699352c8648c0a75a1b4222db7cae")
            .enqueue(object : retrofit2.Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    val news = response.body()
                    if (news != null){
                        adapter = CategoryAdapter(requireContext(), news.articles as ArrayList<Article>)
                        binding.technologyRyc.adapter = adapter
                        adapter.notifyDataSetChanged()
                        binding.shimmerLayout.stopShimmer()
                        binding.shimmerLayout.visibility = View.GONE
                    }
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    Log.d("news", "Error")
                }

            })
    }

    override fun onPause() {
        binding.shimmerLayout.stopShimmer()
        super.onPause()
    }

    override fun onResume() {
        binding.shimmerLayout.startShimmer()
        super.onResume()
    }
}