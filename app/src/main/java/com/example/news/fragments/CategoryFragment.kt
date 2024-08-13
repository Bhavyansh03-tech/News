package com.example.news.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.news.R
import com.example.news.databinding.FragmentCategoryBinding
import com.example.news.tabLayoutFRAG.Breaking
import com.example.news.tabLayoutFRAG.BusinessFragment
import com.example.news.tabLayoutFRAG.EntertainmentFragment
import com.example.news.tabLayoutFRAG.ScienceFragment
import com.example.news.tabLayoutFRAG.SportsFragment
import com.example.news.tabLayoutFRAG.TechnologyFragment
import com.google.android.material.tabs.TabLayoutMediator

class CategoryFragment : Fragment() {

    private lateinit var binding: FragmentCategoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCategoryBinding.inflate(inflater, container, false)

        binding.viewPager.adapter = PageAdapter(requireActivity().supportFragmentManager, lifecycle)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->

            when(position){
                0 -> {
                    tab.text = "All"
                }
                1 -> {
                    tab.text = "Sports"
                }

                2 -> {
                    tab.text = "Entertainment"
                }
                3 -> {
                    tab.text = "Science"
                }
                4 -> {
                    tab.text = "Business"
                }
                5 -> {
                    tab.text = "Technology"
                }
            }
        }.attach()

        for (i in 0..6){
            val textView = LayoutInflater.from(requireContext()).inflate(R.layout.tab_indicator, null) as TextView
            binding.tabLayout.getTabAt(i)?.customView = textView
        }

        return binding.root
    }

    private class PageAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle){

        private val fragmentManager = listOf(
            Breaking(),
            SportsFragment(),
            EntertainmentFragment(),
            ScienceFragment(),
            BusinessFragment(),
            TechnologyFragment()
        )

        override fun getItemCount(): Int {
            return fragmentManager.size
        }

        override fun createFragment(position: Int): Fragment {
            return fragmentManager[position]
        }

    }
}