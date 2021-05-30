package com.rsschool.android2021

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class SecondFragment : Fragment() , OnBackPressedListener {

    private var backButton: Button? = null
    private var result: TextView? = null
    private lateinit var backTransfer: BackTransfer
    var rez: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        backTransfer = activity as BackTransfer
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        result = view.findViewById(R.id.result)
        backButton = view.findViewById(R.id.back)

        val min = arguments?.getInt(MIN_VALUE_KEY) ?: 0
        val max = arguments?.getInt(MAX_VALUE_KEY) ?: 0
        rez = generate(min, max)

        result?.text = rez.toString()

        backButton?.setOnClickListener {
            // TODO: implement back
            backTransfer.transf(rez)
        }
    }

    private fun generate(min: Int, max: Int): Int {
        // TODO: generate random number
        return (min..max).random()
    }

    companion object {

        @JvmStatic
        fun newInstance(min: Int, max: Int): SecondFragment {
            val fragment = SecondFragment()
            val args = Bundle()
            args.putInt(MIN_VALUE_KEY, min)
            args.putInt(MAX_VALUE_KEY, max)
            fragment.arguments = args

            // TODO: implement adding arguments

            return fragment
        }

        private const val MIN_VALUE_KEY = "MIN_VALUE"
        private const val MAX_VALUE_KEY = "MAX_VALUE"
    }

    override fun onBackPressed() {
        backTransfer.transf(rez)
    }
}