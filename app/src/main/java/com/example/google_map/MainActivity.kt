package com.example.google_map

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.btn_get



class MainActivity : ComponentActivity() {

    // Declaring Location Provider Variable....
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    // Firebase Database Reference....
    val my_database_ref = FirebaseDatabase.getInstance().reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        btn_get.setOnClickListener{

            Toast.makeText(this, "Getting Location", Toast.LENGTH_LONG).show()
            get_location()

        }

    }



    fun get_location(){

        // Check Permission.....
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this,
            arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 100)

        }

        //////////////////////////////////////

        // Get Location....
        val location = fusedLocationProviderClient.lastLocation
        location.addOnSuccessListener {

            if(it != null){

                val latitude = it.latitude.toString()
                val longitude = it.longitude.toString()


                // Adding Value to the Firebase Realtime Database....
                // Setting Latitude Value...
                my_database_ref.child("location").child("latitude").setValue(latitude)

                // Setting Longitude Value....
                my_database_ref.child("location").child("longitude").setValue(longitude)

            }

        }

        ///////////////////////////////////////

        // Open Next Activity....
        val intent = Intent(this , MyLocation :: class.java)
        startActivity(intent)

    }



}