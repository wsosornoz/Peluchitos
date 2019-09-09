package com.example.peluchitosv3.ui.gallery

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
import kotlinx.android.synthetic.main.fragment_gallery.view.*

class GalleryFragment : Fragment() {

    private lateinit var galleryViewModel: GalleryViewModel
    private lateinit var user: User

    var listUser: ArrayList<User> = ArrayList()
    private var data = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        galleryViewModel =
            ViewModelProviders.of(this).get(GalleryViewModel::class.java)

        val view = inflater.inflate(R.layout.fragment_gallery, container, false)

        Log.d("exception", "Buscar/Gallery")

        view.b_buscar.setOnClickListener {
            var nombreb = view.bbnombre.text.toString()

            if (arguments != null) {
                listUser = arguments!!.getParcelableArrayList(ARG_PARAM)!!

                for (user in listUser) {

                    if (nombreb == user.nombre) {
                        data = "Id: "+user.id + "\n"+ "Nombre: "+ user.nombre + "\n" +"Cantidad: "+ user.cantidad + "\n" +"Precio: "+ user.precio + "\n"
                    }
                }

                view.txbresult.text = data
                data=""

                //view.text_tools.text = id+" "+nombre+" "+cantidad+" "+precio

            } else {
                view.txbresult.text = "VACIO"
            }

        }

        return view


        return view
    }

    companion object {
        private val ARG_PARAM = "MyObject"

        fun newInstance(userList: ArrayList<User>): GalleryFragment {
            val datosFragment = GalleryFragment()
            val args = Bundle()
            args.putParcelableArrayList(ARG_PARAM, userList)
            datosFragment.arguments = args
            return datosFragment

        }

    }

}