package com.example.googlemapsapp

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.lang.Exception
import java.util.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var locationManager: LocationManager
    private lateinit var locationListener: LocationListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                mMap.clear()
                val currentLocation = LatLng(location.latitude, location.longitude) //Latidude,Longitude
                mMap.addMarker(MarkerOptions().position(currentLocation).title("Marker in Current Location"))
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation,15f))

                val geocoder=Geocoder(this@MapsActivity, Locale.getDefault())
                try {
                    val addressList=geocoder.getFromLocation(location.latitude,location.longitude,1)
                    if(addressList.size>0){
                        println(addressList.get(0).toString())
                    }
                }catch (e:Exception){
                    e.printStackTrace()
                }
            }

        }

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) { //if not allowed
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1
            )

        } else { //if allowed
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1,1f,locationListener)
            var lastLocation=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            if(lastLocation!=null){
                val lastKnwnLatlang=LatLng(lastLocation.latitude,lastLocation.longitude)
                mMap.addMarker(MarkerOptions().position(lastKnwnLatlang).title("Marker in Current Location"))
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastKnwnLatlang,15f))
            }
        }

        fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
        ) {
            if (requestCode == 1) {
                if (grantResults.size > 0) {
                    locationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        1,
                        1f,
                        locationListener
                    )
                }
            }
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }
}