package com.groupal.oneclickdrive

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.groupal.oneclickdrive.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonCalculate.setOnClickListener {
            performOnCalculate()
        }
    }

    private fun performOnCalculate() {
        val input1 = binding.input1.text.toString()
        val input2 = binding.input2.text.toString()
        val input3 = binding.input3.text.toString()

        if (input1.isNotEmpty() && input2.isNotEmpty() && input3.isNotEmpty()) {
            val list1 = convertCommaSeparatedStringToList(input1)
            val list2 = convertCommaSeparatedStringToList(input2)
            val list3 = convertCommaSeparatedStringToList(input3)

            val unionNumbers = getUnionNumbers(list1, list2, list3)
            val intersectionNumbers = getIntersectionNumbers(list1, list2, list3)
            val highestNumber = getHighestNumber(list1, list2, list3)

            binding.apply {
                layoutOutput.visibility = View.VISIBLE
                textUnion.text = getString(R.string.union_numbers, unionNumbers)
                textIntersection.text = getString(R.string.intersect_numbers, intersectionNumbers)
                textHighest.text = getString(R.string.highest_numbers, highestNumber)
            }
        } else {
            Toast.makeText(this, getString(R.string.enter_values), Toast.LENGTH_SHORT).show()

            binding.layoutOutput.visibility = View.GONE
        }
    }

    private fun getUnionNumbers(
        list1: List<Int>,
        list2: List<Int>,
        list3: List<Int>
    ): String {
        val unionSet = list1.toSet().union(list2.toSet()).union(list3.toSet())
        val unionArray = unionSet.toTypedArray()
        return unionArray.joinToString(",")
    }

    private fun getIntersectionNumbers(
        list1: List<Int>,
        list2: List<Int>,
        list3: List<Int>
    ): String {
        val set1 = list1.toSet()
        val set2 = list2.toSet()
        val set3 = list3.toSet()
        val intersectionSet = set1.intersect(set2).intersect(set3)
        val intersectionArray = intersectionSet.toTypedArray()
        return intersectionArray.joinToString(",")
    }

    private fun getHighestNumber(
        list1: List<Int>,
        list2: List<Int>,
        list3: List<Int>
    ): String {
        val combinedList = list1 + list2 + list3
        val highestNumber = combinedList.maxOrNull()
        return highestNumber.toString()
    }

    private fun convertCommaSeparatedStringToList(input: String): List<Int> {
        val strings = input.split(",")
        val formattedList = strings.toMutableList()
        if(strings.isNotEmpty()){
            strings.map{
                if(it.isEmpty()){
                    formattedList.remove(it)
                }
            }
            val ints = formattedList.map {
                it.toInt()
            }.toTypedArray()
            return ints.toList()
        } else {
            return arrayListOf()
        }
    }
}