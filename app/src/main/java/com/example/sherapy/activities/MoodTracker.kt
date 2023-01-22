package com.example.sherapy.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sherapy.R
import com.example.sherapy.databinding.ActivityMoodTrackerBinding
import com.example.sherapy.databinding.Fragment4Binding
import com.example.sherapy.utilities.PreferenceManager

class MoodTracker : Fragment() {

    private lateinit var binding: ActivityMoodTrackerBinding
    private lateinit var preferenceManager: PreferenceManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityMoodTrackerBinding.inflate(layoutInflater, container, false)
        binding.imageButton.setOnClickListener {
            findNavController().navigate(MoodTrackerDirections.actionMoodTrackerToMoodFragment())
        }
        return binding.root
    }
}