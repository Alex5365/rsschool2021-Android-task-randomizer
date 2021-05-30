package com.rsschool.android2021

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment

class FirstFragment : Fragment() {

    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private lateinit var transfer: Transfer

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        transfer = activity as Transfer
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)

        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = "Previous result: ${result.toString()}"

        // TODO: val min = ...
        // TODO: val max = ...
        val min_value = view.findViewById(R.id.min_value) as EditText
        val max_value = view.findViewById(R.id.max_value) as EditText

        generateButton?.setOnClickListener {
            // TODO: send min and max to the SecondFragment
            val min = min_value.text
            val max = max_value.text
            if (min.toString() == "" || max.toString() == "")
            {
                val toast: Toast = Toast.makeText(activity, R.string.str4, Toast.LENGTH_SHORT)
                toast.show()
            }
            else {
                if (proverka(min.toString().toInt(), max.toString().toInt())) {
                    transfer.transf(min.toString().toInt(), max.toString().toInt())
                }
            }
        }
    }

    fun proverka(min: Int, max: Int): Boolean
    {
        if (min > max)
        {
            val toast: Toast = Toast.makeText(activity, R.string.str1, Toast.LENGTH_SHORT)
            toast.show()
            return false
        }
        if(max > 2147483646)
        {
            val toast: Toast = Toast.makeText(activity, R.string.str2, Toast.LENGTH_SHORT)
            toast.show()
            return false
        }
        if (min < 0)
        {
            val toast: Toast = Toast.makeText(activity, R.string.str3, Toast.LENGTH_SHORT)
            toast.show()
            return false
        }
        else
        {
            return true
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            fragment.arguments = args
            return fragment
        }



        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }

}