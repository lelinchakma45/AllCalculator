package com.example.calculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.calculator.databinding.FragmentTempBinding

class TempFragment : Fragment() {
    lateinit var binding: FragmentTempBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentTempBinding.inflate(layoutInflater)
        binding.convertButton.setOnClickListener {
            val inputTemperature = binding.tempId.text.toString().toDoubleOrNull()
            if (inputTemperature != null) {
                val convertedTemperature = String.format("%.2f",celsiusToFahrenheit(inputTemperature))
                val convertedKalTemperature = String.format("%.2f",celsiusToKelvin(inputTemperature))

                binding.farResult.text = "$convertedTemperature F"
                binding.kalResult.text = "$convertedKalTemperature K"
            } else {
                binding.farResult.text = "0"
                binding.kalResult.text = "0"
            }
        }

        binding.converFartButton.setOnClickListener {
            val inputFarTemperature = binding.FarId.text.toString().toDoubleOrNull()
            if (inputFarTemperature != null) {
                val convertedFartoCel = String.format("%.2f",fahrenheitToCelsius(inputFarTemperature))
                val convertedFertoKel = String.format("%.2f",fahrenheitToKelvin(inputFarTemperature))

                binding.celresult.text = "$convertedFartoCel C"
                binding.kalResult2.text = "$convertedFertoKel K"
            } else {
                binding.celresult.text = "0"
                binding.kalResult2.text = "0"
            }
        }
        binding.converKeltButton.setOnClickListener {
            val inputKelTemperature = binding.kelId.text.toString().toDoubleOrNull()
            if (inputKelTemperature != null) {
                val convertedKeltoCel = String.format("%.2f",kelvinToCelsius(inputKelTemperature))
                val convertedKeltoFer = String.format("%.2f",kelvinToFahrenheit(inputKelTemperature))

                binding.celresult2.text = "$convertedKeltoCel C"
                binding.farResult2.text = "$convertedKeltoFer F"
            } else {
                binding.celresult2.text = "0"
                binding.farResult2.text = "0"
            }
        }
        return binding.root
    }
    private fun celsiusToFahrenheit(celsius: Double): Double {
        return celsius * 9 / 5 + 32
    }

    private fun celsiusToKelvin(celsius: Double): Double {
        return celsius + 273.15
    }

    private fun fahrenheitToCelsius(fahrenheit: Double): Double {
        return (fahrenheit - 32) * 5 / 9
    }

    private fun kelvinToCelsius(kelvin: Double): Double {
        return kelvin - 273.15
    }

    private fun fahrenheitToKelvin(fahrenheit: Double): Double {
        return (fahrenheit - 32) * 5 / 9 + 273.15
    }

    private fun kelvinToFahrenheit(kelvin: Double): Double {
        return kelvin * 9 / 5 - 459.67
    }

}