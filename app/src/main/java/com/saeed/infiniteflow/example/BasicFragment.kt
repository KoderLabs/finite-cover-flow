package com.saeed.infiniteflow.example


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * A simple [Fragment] subclass.
 */
class BasicFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.circle_view, container, false)
    }


    companion object {
        fun newInstance(): BasicFragment {
            val fragment = BasicFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }
}
