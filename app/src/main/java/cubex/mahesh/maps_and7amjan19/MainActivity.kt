package cubex.mahesh.maps_and7amjan19

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var sFrag :SupportMapFragment =
         supportFragmentManager.findFragmentById(R.id.frag1) as
                 SupportMapFragment

        sFrag.getMapAsync(object : OnMapReadyCallback {
            @SuppressLint("MissingPermission")
            override fun onMapReady(gMap: GoogleMap?) {
           // gMap?.mapType = GoogleMap.MAP_TYPE_HYBRID

                var lManager = getSystemService(
                    Context.LOCATION_SERVICE) as LocationManager

                lManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,
                    1000L,
                    1F,object:LocationListener{
                        override fun onLocationChanged(loc: Location?) {
                                var lati = loc?.latitude
                                var longi = loc?.longitude

                            var options = MarkerOptions()
                            options.position(LatLng(lati!!,longi!!))
                            options.icon(BitmapDescriptorFactory.
                                fromResource(R.drawable.motorcycle))
                            gMap?.addMarker(options)

                            gMap?.animateCamera(CameraUpdateFactory.
                                newLatLngZoom(LatLng(lati!!,longi!!),15f))

                            lManager.removeUpdates(this)

                        }

                        override fun onProviderEnabled(p0: String?) {
                            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                        }

                        override fun onProviderDisabled(p0: String?) {
                            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                        }

                        override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
                            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                        }
                    }
                )


            }
        })
    }
}
