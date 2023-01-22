package com.example.sherapy.utilities

import com.example.sherapy.R

object Dummy {

    fun getProfileAccountAddOns() = listOf(
        Pair(R.drawable.ic_baseline_person_24, "Edit Profile"),
        Pair(R.drawable.ic_baseline_history_24, "Payment History"),
        Pair(R.drawable.ic_baseline_language_24, "Change Language"),
        Pair(R.drawable.ic_baseline_exit_to_app_24, "Log Out")
    )

    fun getProfileHelpdeskCenterAddOns() = listOf(
        Pair(R.drawable.ic_baseline_privacy_tip_24, "Privacy Policy"),
        Pair(R.drawable.ic_baseline_home_repair_service_24, "Customer Service"),
        Pair(R.drawable.ic_baseline_help_outline_24, "Frequently Asked Question")
    )

    fun getEditProfile() = listOf(
        Pair(R.drawable.ic_baseline_person_24, "Nama"),
        Pair(R.drawable.ic_baseline_email_24, "Email"),
        Pair(R.drawable.ic_baseline_password_24, "Password"),
        Pair(R.drawable.ic_baseline_phone_24, "Nomor Handphone"),
        Pair(R.drawable.calendar, "Tanggal Lahir"),
        Pair(R.drawable.ic_baseline_work_24, "Pekerjaan")
    )

}