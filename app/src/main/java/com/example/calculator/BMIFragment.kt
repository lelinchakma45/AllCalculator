package com.example.calculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.calculator.databinding.FragmentBMIBinding

class BMIFragment : Fragment() {
    private lateinit var binding: FragmentBMIBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBMIBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener {
            binding.cardView5.visibility = View.VISIBLE
            val weight = binding.editText2.text.toString()
            val height = binding.editText.text.toString()
            if (validWeightHeight(weight, height)) {
                val result = weight.toDouble() / ((height.toDouble() / 100) * (height.toDouble() / 100))
                val resultDigit = String.format("%.2f", result).toDouble()
                displayResult(resultDigit)
            }
        }
    }

    private fun displayResult(resultDigit: Double) {
        var color = 0
        var range = ""
        when {
            resultDigit < 18.50 -> {
                color = R.color.under
                range = "You are Under Weight"
            }
            resultDigit in 18.50..24.99 -> {
                color = R.color.normal
                range = "You are Normal Weight"
            }
            resultDigit in 25.00..29.99 -> {
                color = R.color.over
                range = "You are Over Weight"
            }
            resultDigit > 29.99 -> {
                color = R.color.obes
                range = "You are Obese"
            }
        }
        binding.textView6.setTextColor(ContextCompat.getColor(requireContext(), color))
        binding.textView6.text = resultDigit.toString()
        binding.textView7.text = range
    }

    private fun validWeightHeight(weight: String, height: String): Boolean {
        return when {
            weight.isBlank() -> {
                Toast.makeText(requireContext(), "Weight is Empty", Toast.LENGTH_SHORT).show()
                false
            }
            height.isBlank() -> {
                Toast.makeText(requireContext(), "Height Input Field is Empty", Toast.LENGTH_SHORT).show()
                false
            }
            else -> true
        }
    }
}