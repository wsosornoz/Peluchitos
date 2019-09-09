package com.example.peluchitosv3.ui.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.peluchitosv3.R
import com.example.peluchitosv3.ui.comunicador
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    var interfaz: comunicador ?= null

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //homeViewModel =
            //ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        /*
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(this, Observer {
            textView.text = it
        })*/

        view.bAgregar.setOnClickListener {
            var id = view.ehid.text.toString()
            var nombre = view.ehnombre.text.toString()
            var cantidad = view.ehcantidad.text.toString()
            var precio = view.ehprecio.text.toString()
            view.tx_hresult.text="Agregado: " + nombre
            interfaz?.enviarDatos(id,nombre, cantidad, precio,"0")

            view.ehid.text.clear()
            view.ehnombre.text.clear()
            view.ehcantidad.text.clear()
            view.ehprecio.text.clear()

        }

        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try{
            interfaz=context!! as comunicador
            Log.d("exception","entro")
        } catch (e: ClassCastException){
            Log.d("exception",e.toString())
        }
    }

}