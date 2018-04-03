package com.example.eduardo.lab1.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView

import com.example.eduardo.lab1.R
import org.jetbrains.anko.find
import java.util.ArrayList


/**
 * A simple [Fragment] subclass.
 */
class AllFormsFragment : Fragment() {
    private lateinit var listForms: ListView
    private lateinit var listAdapter: ArrayAdapter<String>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_forms, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        listForms = view.find(R.id.listForms)
        val planets = arrayOf("Nombre: Nombre 1 - Fecha: 01/01/2000 - Comentario: blabla")
        val planetList = ArrayList<String>()
        planetList.addAll(planets.toList())

        // Create ArrayAdapter using the planet list.
        listAdapter = ArrayAdapter(view.context, R.layout.simplerow, planetList)

        // Add more planets. If you passed a String[] instead of a List<String>
        // into the ArrayAdapter constructor, you must not add more items.
        // Otherwise an exception will occur.
        listAdapter.add("Nombre: Nombre 2 - Fecha: 01/01/2010 - Comentario: asdf")
        listAdapter.add("Nombre: Nombre 30 - Fecha: 07/06/2017 - Comentario: trololo")

        // Set the ArrayAdapter as the ListView's adapter.
        listForms.adapter = listAdapter
    }

}// Required empty public constructor
