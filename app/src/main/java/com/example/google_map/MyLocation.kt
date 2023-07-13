package com.example.google_map

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_my_location.txt_lat
import kotlinx.android.synthetic.main.activity_my_location.txt_long

class MyLocation : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_location)

        val my_database_ref = FirebaseDatabase.getInstance().reference


        // Read Data from Firebase....
        my_database_ref.child("location").get()
            .addOnSuccessListener{

                val latitude = it.child("latitude").value.toString()
                val longitude = it.child("longitude").value.toString()

                txt_lat.text = "Latitude: $latitude"
                txt_long.text = "Longitude: $longitude"

            }.addOnFailureListener{

                Toast.makeText(this, "Failed to Fetch Data", Toast.LENGTH_LONG).show()

            }

    }

}