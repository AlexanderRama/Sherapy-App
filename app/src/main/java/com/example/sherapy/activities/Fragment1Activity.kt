package com.example.sherapy.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.sherapy.databinding.Fragment1Binding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Fragment1Activity : Fragment() {

    private lateinit var _binding: Fragment1Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = Fragment1Binding.inflate(layoutInflater)
        viewLifecycleOwner.lifecycleScope.launch {
            delay(5000)
            findNavController().navigate(Fragment1ActivityDirections.actionFragment1FragmentToFragment2Activity())
        }
        return _binding.root
    }

}