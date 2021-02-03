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
    private lateinit var locationManager : LocationManager
    private lateinit var locationListener : LocationListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setOnMapLongClickListener(listenerObj)
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        locationListener = object : LocationListener {
            override fun onLocationChanged(p0: Location) {
                mMap.clear()
                val myCurrentLocation = LatLng(p0.latitude,p0.longitude)
                mMap.addMarker(MarkerOptions().position(myCurrentLocation).title("Current Location"))
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myCurrentLocation,15f))

                val geocoder = Geocoder(this@MapsActivity, Locale.getDefault())

                try {
                    val addressList = geocoder.getFromLocation(p0.latitude,p0.longitude,1)
                    if(addressList.size > 0) {
                        println(addressList.get(0).toString())
                    }
                } catch (e: Exception){
                    e.printStackTrace()
                }
            }
        }

        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),1)
        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1,1f,locationListener)
            val myLastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            if (myLastLocation != null) {
                val myLastLatLng = LatLng(myLastLocation.latitude,myLastLocation.longitude)
                mMap.addMarker(MarkerOptions().position(myLastLatLng).title("Last Location"))
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLastLatLng,15f))
            }
        }
    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {

        if (requestCode == 1){
            if (grantResults.size > 0){
                if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1,1f,locationListener)
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    val listenerObj = object : GoogleMap.OnMapLongClickListener {
        override fun onMapLongClick(p0: LatLng?) {
            mMap.clear()
            val geocoder = Geocoder(this@MapsActivity,Locale.getDefault())
            if(p0 != null) {
                var address = ""
                try {
                    val adresListesi = geocoder.getFromLocation(p0.latitude,p0.longitude,1)
                    if (adresListesi.size > 0 ) {
                        if (adresListesi.get(0).thoroughfare != null) {
                            address += adresListesi.get(0).thoroughfare
                            if(adresListesi.get(0).subThoroughfare != null) {
                                address += adresListesi.get(0).subThoroughfare
                            }
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                mMap.addMarker(MarkerOptions().position(p0).title(address))
            }
        }
    }
}