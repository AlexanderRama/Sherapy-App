package com.example.sherapy.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.sherapy.databinding.Fragment3Binding
import com.example.sherapy.utilities.PreferenceManager
import java.math.RoundingMode

class Fragment3Activity : Fragment() {

    private lateinit var binding: Fragment3Binding
    private lateinit var preferenceManager: PreferenceManager
    lateinit var performance:TextView

    private val args by navArgs<Fragment3ActivityArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = Fragment3Binding.inflate(layoutInflater, container, false)
        performance=binding.performance

        binding.button2.setOnClickListener {
            findNavController().navigate(Fragment3ActivityDirections.actionFragment3ActivityToFragment4Activity())
        }
        binding.imageButton.setOnClickListener {
            findNavController().navigate(Fragment3ActivityDirections.actionFragment3ActivityToHomepageFragment())
        }

        val correctAnsNo = args.correctAnswerCount
        val totalAnsNo = args.questionListSize

        val percentage = (correctAnsNo.toDouble() / 60)*100
        when {
            (correctAnsNo > 0) && (correctAnsNo < 4) -> {
                "Healthy levels of mental health".also { binding.performance.text = it }
            }
            (correctAnsNo > 10) && (correctAnsNo < 14)-> {
                "Mild levels of mental health".also { binding.performance.text = it }
            }
            (correctAnsNo > 15) && (correctAnsNo < 19)-> {
                "Moderate levels of mental health".also { binding.performance.text = it }
            }
            (correctAnsNo > 24) && (correctAnsNo < 30)-> {
                "Moderately Severe levels of mental health".also { binding.performance.text = it }
            }
            (correctAnsNo > 39)-> {
                "Severe levels of mental health".also { binding.performance.text = it }
            }
        }
        binding.percent.text = percentage.toBigDecimal().setScale(1, RoundingMode.UP).toDouble().toString()
        binding.progressBar.progress = percentage.toInt()
            return binding.root
    }
}