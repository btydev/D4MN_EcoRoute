package com.example.ecologyroute.mainfragments

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import com.example.ecologyroute.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_map.*

class MapFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    companion object{
        const val reqCode = 1
    }

    private lateinit var googleMap : GoogleMap
    private lateinit var fusedLocation: FusedLocationProviderClient
    private lateinit var lastLocation: Location

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        map_view.onCreate(savedInstanceState)
        map_view.onResume()
        map_view.getMapAsync(this)
        fusedLocation = LocationServices.getFusedLocationProviderClient(context)
    }
    override fun onMapReady(map: GoogleMap) {
        map?.let {
            googleMap = it

            googleMap.uiSettings.isZoomControlsEnabled = true
            googleMap.setOnMarkerClickListener (this)
            if(ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), reqCode)
                    return
            }
            googleMap.isMyLocationEnabled = true
            fusedLocation.lastLocation.addOnSuccessListener(requireActivity()){location ->
                if(location != null){
                    lastLocation = location
                    val currentLatLong = LatLng(location.latitude, location.longitude).apply {
                        placeMarkerOnMAP(this)
                    }
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLong, 12f))
                }
            }
        }
    }

    private fun placeMarkerOnMAP(currentLatLong: LatLng) {
        val markerOptions = MarkerOptions().position(currentLatLong)
        markerOptions.title("$currentLatLong")
        googleMap.addMarker(markerOptions)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_map, container, false)
        return view
    }

    override fun onMarkerClick(p0: Marker?) = false


}