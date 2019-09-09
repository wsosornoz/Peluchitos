package com.example.peluchitosv3.ui.tools

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
import com.example.peluchitosv3.User
import kotlinx.android.synthetic.main.fragment_tools.view.*

class ToolsFragment : Fragment() {

    private lateinit var user: User
    //private lateinit var toolsViewModel: ToolsViewModel

    var listUser: ArrayList<User> = ArrayList()
    private  var data = ""


    override fun onCreateView(
        inflater: LayoutInflater,container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tools, container, false)

        Log.d("exception","Inventario/tool")

        if (arguments!= null){
            listUser = arguments!!.getParcelableArrayList(ARG_PARAM)!!

            for (user in listUser){

                data = data + user.id + ": " + user.nombre + ": " + user.cantidad +": " + user.precio +"\n"
            }

            view.text_tools.text = data
            data=""

            //view.text_tools.text = id+" "+nombre+" "+cantidad+" "+precio

        }

        else {view.text_tools.text = "VACIO" }

        return view
    }

    companion object{
        private val ARG_PARAM = "MyObject"

        fun newInstance(userList: ArrayList<User>): ToolsFragment{
            val datosFragment= ToolsFragment()
            val args = Bundle()
            args.putParcelableArrayList(ARG_PARAM,userList)
            datosFragment.arguments=args
            return  datosFragment

        }

    }
}