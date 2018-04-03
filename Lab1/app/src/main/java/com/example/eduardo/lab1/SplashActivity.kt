package com.example.eduardo.lab1

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBar
import android.view.View
import android.support.v7.widget.Toolbar
import org.jetbrains.anko.find
import android.support.v4.view.GravityCompat
import android.view.MenuItem
import android.support.annotation.NonNull
import android.support.design.widget.NavigationView
import android.widget.*
import com.example.eduardo.lab1.Fragments.AddFormFragment
import com.example.eduardo.lab1.Fragments.AllFormsFragment
import com.example.eduardo.lab1.Fragments.ResumenFormsFragment
import android.widget.ArrayAdapter
import org.jetbrains.anko.support.v4.find
import java.util.*
import java.util.Arrays.asList




class SplashActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val DEFAULT_VALUE = ""
    private lateinit var credentialsManager: CredentialsManager
    private lateinit var resultsTextView: TextView
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var signOutButton: Button
    private lateinit var navHeaderText: TextView
    private lateinit var toolbar: Toolbar
    private lateinit var actionbar: ActionBar
    private lateinit var mDrawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView

    private lateinit var nameFormContent : EditText
    private lateinit var dateFormContent : EditText
    private lateinit var categoryFormContent : Spinner
    private lateinit var commentaryFormContent : EditText

    private val ctx = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        getUIReferences()
        setupListeners()
        credentialsManager = CredentialsManager(this)

        email = credentialsManager.getEmail()
        password = credentialsManager.getPassword()

        if(email == DEFAULT_VALUE && password == DEFAULT_VALUE){
            startActivityForResult(Intent(this,MainActivity::class.java), 1)
        }
        else{
            showInfo(email, password)
            setupNavHeaders(email)
            setupBarElements()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        val extras = data.extras
        val email = extras.get("userEmail").toString()
        val password = extras.get("userPassword").toString()
        saveInfo(email, password)
        showInfo(email, password)
        setupNavHeaders(email)
        setupBarElements()
    }

    private fun getUIReferences(){
        resultsTextView = find(R.id.resultsTextView)
        signOutButton = find(R.id.signOutButton)
        toolbar = find(R.id.toolbar)
        mDrawerLayout = find(R.id.drawer_layout)
        navigationView = find(R.id.nav_view)
    }

    private fun setupListeners(){
        signOutButton.setOnClickListener{
            credentialsManager.setEmail(DEFAULT_VALUE)
            credentialsManager.setPassword(DEFAULT_VALUE)
            showMessage("Sesion finalizada")
            startActivityForResult(Intent(this,MainActivity::class.java), 1)
        }

        mDrawerLayout.addDrawerListener(
                object : DrawerLayout.DrawerListener {
                    override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                        //showMessage("onDrawerSlide")
                    }

                    override fun onDrawerOpened(drawerView: View) {
                        //showMessage("onDrawerOpened")
                    }

                    override fun onDrawerClosed(drawerView: View) {
                        //showMessage("onDrawerClosed")
                    }

                    override fun onDrawerStateChanged(newState: Int) {
                        //showMessage("onDrawerStateChanged")
                    }
                }
        )

        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                mDrawerLayout.openDrawer(GravityCompat.START)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.nav_add_form -> {
                val fragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.frameLayoutContent, AddFormFragment())
                fragmentTransaction.commit()
                Handler().postDelayed({
                    getUIReferencesAddForm()
                }, 500L)
            }

            R.id.nav_all_forms -> {
                val fragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.frameLayoutContent, AllFormsFragment())
                fragmentTransaction.commit()
            }

            R.id.nav_resumen -> {
                val fragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.frameLayoutContent, ResumenFormsFragment())
                fragmentTransaction.commit()
            }
        }
        return true
    }

    private fun getUIReferencesAddForm(){
        nameFormContent = find(R.id.nameFormContent)
        dateFormContent = find(R.id.dateFormContent)
        categoryFormContent = find(R.id.categoryFormContent)
        commentaryFormContent = find(R.id.commentaryFormContent)
    }

    private fun showMessage(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun setupBarElements(){
        setSupportActionBar(toolbar)
        actionbar = supportActionBar as ActionBar
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu)
    }

    private fun setupNavHeaders(email: String){
        val delay = 500L
        Handler().postDelayed({
            navHeaderText = find(R.id.navHeaderText)
            setNavHeaderText(email)
        }, delay)
    }

    private fun setNavHeaderText(title: String){
        navHeaderText.text = title
    }

    private fun saveInfo(userEmail: String, userPassword: String){
        credentialsManager.setEmail(userEmail)
        credentialsManager.setPassword(userPassword)
    }

    private fun showInfo(userEmail: String, userPassword: String){
        resultsTextView.text = "Usuario: " + userEmail + " Password: " + userPassword
        resultsTextView.visibility = View.VISIBLE
    }
}
