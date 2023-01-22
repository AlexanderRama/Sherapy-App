package com.example.sherapy.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sherapy.R
import com.example.sherapy.activities.SignInActivity
import com.example.sherapy.databinding.FragmentProfileBinding
import com.example.sherapy.databinding.IncludeProfileCardBinding
import com.example.sherapy.utilities.Constants
import com.example.sherapy.utilities.Dummy
import com.example.sherapy.utilities.PreferenceManager
import com.example.sherapy.utilities.ProfileAddOnsAdapter

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var preferenceManager: PreferenceManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        binding.imageButton4.setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToHomepageFragment())
        }
        preferenceManager = PreferenceManager(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val accountAddOnAdapter = ProfileAddOnsAdapter()
        val helpDeskAddOnAdapter = ProfileAddOnsAdapter()

        binding.apply {
            rvAccountAddOn.apply {
                adapter = accountAddOnAdapter
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            }
            rvHelpdeskCenterAddOn.apply {
                adapter = helpDeskAddOnAdapter
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            }

            accountAddOnAdapter.submitList(Dummy.getProfileAccountAddOns())
            helpDeskAddOnAdapter.submitList(Dummy.getProfileHelpdeskCenterAddOns())

        }
        loadUserDetails()
    }


    private fun loadUserDetails() {
        binding.includeProfileCard.tvUsername.text = preferenceManager.getString(Constants.KEY_NAME)
        binding.includeProfileCard.tvEmail.text = preferenceManager.getString(Constants.KEY_EMAIL)
    }

    private fun signOut() {
        showToast("Logout")
        preferenceManager.putBoolean(Constants.KEY_IS_SIGNED_IN, false)
        startActivity(Intent(requireActivity().applicationContext, SignInActivity::class.java))
        requireActivity().finish()
    }

    private fun showToast(message: String) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_LONG).show()
    }
}