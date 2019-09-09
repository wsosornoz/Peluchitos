package com.example.peluchitosv3.ui.slideshow

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
import com.example.peluchitosv3.User
import com.example.peluchitosv3.ui.comunicador
import kotlinx.android.synthetic.main.fragment_slideshow.view.*

class SlideshowFragment : Fragment() {

    var interfaz: comunicador?= null

    private lateinit var slideshowViewModel: SlideshowViewModel

    private lateinit var user: User
    var listUser: ArrayList<User> = ArrayList()
    //var listUser2: ArrayList<User> = ArrayList()
    private  var data = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        //slideshowViewModel =ViewModelProviders.of(this).get(SlideshowViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_slideshow, container, false)
        //val textView: TextView = root.findViewById(R.id.text_slideshow)
        //slideshowViewModel.text.observe(this, Observer {            textView.text = it })


        view.b_eliminar.setOnClickListener {
            var ide = view.eeid.text.toString()

            if (arguments != null) {
                listUser = arguments!!.getParcelableArrayList(ARG_PARAM)!!
                var listUser2 = listUser
                var i = 0
                for (user in listUser) {

                    if (ide == user.id) {
                        //data = "Id: "+user.id + "\n"+ "Nombre: "+ user.nombre + "\n" +"Cantidad: "+ user.cantidad + "\n" +"Precio: "+ user.precio + "\n"
                        listUser2= listUser.filterIndexed { index, user ->  index !=i } as ArrayList<User>
                        view.text_slideshow.text ="Eliminado producto con id:" + ide
                        //data="elimino"
                        //("1")
                    }
                    i+=1
                }
                interfaz?.enviarDatos("1","1", "1","1","1")

                for (user in listUser2) {


                    data = data+user.id + user.nombre + user.cantidad + user.precio + "\n"
                    var sid=user.id.toString()
                    var sname=user.nombre.toString()
                    var scant=user.cantidad.toString()
                    var spre=user.precio.toString()
                    interfaz?.enviarDatos(sid,sname, scant,spre ,"0")

                    //listUser.drop(1)
                        //("1")

                }

                //view.text_slideshow.text =data
                //data=""

                //view.text_tools.text = id+" "+nombre+" "+cantidad+" "+precio

            } else {
                view.text_slideshow.text = "VACIO"
            }

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

    companion object{
        private val ARG_PARAM = "MyObject"

        fun newInstance(userList: ArrayList<User>): SlideshowFragment{
            val datosFragment= SlideshowFragment()
            val args = Bundle()
            args.putParcelableArrayList(ARG_PARAM,userList)
            datosFragment.arguments=args
            return  datosFragment

        }

    }
}