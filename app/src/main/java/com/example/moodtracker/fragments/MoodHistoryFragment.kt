package com.example.moodtracker.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moodtracker.adapter.MoodAdapter
import com.example.moodtracker.data.FakeMoodRepository
import com.example.moodtracker.data.MoodEntry
import com.example.moodtracker.R

    class MoodHistoryFragment : Fragment(), MoodAdapter.OnItemClickListener {
        private lateinit var recyclerView: RecyclerView
        private lateinit var adapter: MoodAdapter

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_mood_history, container, false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            recyclerView = view.findViewById(R.id.recycler_view_moods)
            recyclerView.layoutManager = LinearLayoutManager(requireContext())

            //val moodList = FakeMoodRepositorry.moodList
            adapter = MoodAdapter(mutableListOf(), this)
            recyclerView.adapter = adapter
        }

        override fun onResume() {
            super.onResume()

            val currentItemList = FakeMoodRepository.moodList
            adapter.itemList.clear()
            adapter.itemList.addAll(currentItemList)
            adapter.notifyDataSetChanged()
        }

        override fun onItemClick(moodEntry: MoodEntry) {
            val direction = MoodHistoryFragmentDirections.moodHistoryFragmentToMoodDetailsFragment(moodEntry)
            findNavController().navigate(direction)
        }

    }



