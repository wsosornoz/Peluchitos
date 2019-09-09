package com.example.peluchitosv3

import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.example.peluchitosv3.ui.comunicador
import com.example.peluchitosv3.ui.gallery.GalleryFragment
import com.example.peluchitosv3.ui.home.HomeFragment
import com.example.peluchitosv3.ui.slideshow.SlideshowFragment
import com.example.peluchitosv3.ui.tools.ToolsFragment

class MainActivity : AppCompatActivity(), comunicador, NavigationView.OnNavigationItemSelectedListener {

    private lateinit var user: User

    private var listUser : ArrayList<User> = ArrayList()

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        //val formFragment = HomeFragment()
        //transaction.add(R.id.nav_host_fragment,formFragment).commit()


        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)

        val manager = supportFragmentManager
        val transaction=manager.beginTransaction()

        val homeFragment = HomeFragment()
        transaction.add(R.id.nav_host_fragment, homeFragment).commit()

        //val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        /*
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)*/


    }

    override fun enviarDatos(id: String, nombre:String, cantidad: String, precio: String,tipo: String){

        user = User(id,nombre,cantidad,precio)
        if (tipo == "0"){
            listUser.add(user)}
        else if (tipo == "1"){
            listUser.clear()
        }

    }
    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        //menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.

        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()

        when (item.itemId) {
            R.id.nav_home -> {
                val homeFragment = HomeFragment()
                Log.d("exception","Fhome")
                transaction.replace(R.id.nav_host_fragment, homeFragment).commit()
            }
            R.id.nav_gallery -> {
                val galleryFragment = GalleryFragment()
                Log.d("exception","Fgalley")

                transaction.replace(R.id.nav_host_fragment, GalleryFragment.newInstance(listUser)).commit()
            }
            R.id.nav_slideshow -> {
                val slideshowFragment = SlideshowFragment()
                Log.d("exception","Fslide")
                transaction.replace(R.id.nav_host_fragment, SlideshowFragment.newInstance(listUser)).commit()
            }

            R.id.nav_tools -> {
                val toolsFragment = ToolsFragment()
                Log.d("exception","Ftool")
                transaction.replace(R.id.nav_host_fragment, ToolsFragment.newInstance(listUser)).commit()

            }
        }

        val drawerLayout: DrawerLayout=findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
/*
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

*/
}
