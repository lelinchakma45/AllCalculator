package com.example.calculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.calculator.databinding.FragmentSimpleBinding
import org.mariuszgromada.math.mxparser.Expression
import java.text.DecimalFormat

class SimpleFragment : Fragment() {
    private lateinit var binding: FragmentSimpleBinding
    private lateinit var input: TextView
    private lateinit var output: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSimpleBinding.inflate(inflater, container, false)
        input = binding.input
        output = binding.output

        binding.cardView4.setOnClickListener {
            clearInputAndOutput()
        }

        binding.cardView5.setOnClickListener {
            removeLastCharacter()
        }
        binding.cardView21.setOnClickListener { addToInputText("0") }
        binding.cardView16.setOnClickListener { addToInputText("1") }
        binding.cardView17.setOnClickListener { addToInputText("2") }
        binding.cardView18.setOnClickListener { addToInputText("3") }
        binding.cardView12.setOnClickListener { addToInputText("4") }
        binding.cardView13.setOnClickListener { addToInputText("5") }
        binding.cardView14.setOnClickListener { addToInputText("6") }
        binding.cardView8.setOnClickListener { addToInputText("7") }
        binding.cardView9.setOnClickListener { addToInputText("8") }
        binding.cardView10.setOnClickListener { addToInputText("9") }
        binding.cardView22.setOnClickListener { addToInputText(".") }
        binding.cardView7.setOnClickListener { addToInputText("÷") }
        binding.cardView11.setOnClickListener { addToInputText("×") }
        binding.cardView15.setOnClickListener { addToInputText("-") }
        binding.cardView19.setOnClickListener { addToInputText("+") }
        binding.cardView20.setOnClickListener {
            val removedLast = input.text.toString().dropLast(1)
            input.text = removedLast
        }

        binding.cardView23.setOnClickListener {
            showResult()
        }

        return binding.root
    }

    private fun addToInputText(buttonValue: String) {
        input.text = input.text.toString() + buttonValue
    }

    private fun clearInputAndOutput() {
        input.text = ""
        output.text = ""
    }

    private fun removeLastCharacter() {
        if (input.text.isNotEmpty()) {
            input.text = input.text.subSequence(0, input.text.length - 1)
        }
    }

    private fun getInputExpression(): String {
        var expression = input.text.replace(Regex("÷"), "/")
        expression = expression.replace(Regex("×"), "*")
        return expression
    }

    private fun showResult() {
        val expression = getInputExpression()
        val result: Double

        if (isValidExpression(expression)) {
            result = Expression(expression).calculate()
            if (result.isNaN()) {
                output.text = ""
                output.setTextColor(ContextCompat.getColor(requireContext(), R.color.over))
            } else {
                output.text = DecimalFormat("0.######").format(result).toString()
                output.setTextColor(ContextCompat.getColor(requireContext(), R.color.normal))
            }
        } else {
            output.text = ""
            output.setTextColor(ContextCompat.getColor(requireContext(), R.color.over))
        }
    }

    private fun isValidExpression(expression: String): Boolean {
        return expression.isNotBlank() && expression.matches(Regex("[0-9+\\-*/.() ]+"))
    }

}
