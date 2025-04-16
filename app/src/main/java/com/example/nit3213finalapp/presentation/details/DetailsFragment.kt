package com.example.nit3213finalapp.presentation.details

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.nit3213finalapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {

    // getting the args passed from previous screen
    private val args: DetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val item = args.entityItem

        // setting values to views...
        view.findViewById<TextView>(R.id.tvItemName).text = item.itemName
        view.findViewById<TextView>(R.id.tvDesigner).text = item.designer
        view.findViewById<TextView>(R.id.tvCategory).text = item.category
        view.findViewById<TextView>(R.id.tvMaterial).text = item.material
        view.findViewById<TextView>(R.id.tvYearIntroduced).text = item.yearIntroduced.toString()
        view.findViewById<TextView>(R.id.tvDescription).text = item.description
    }
}
