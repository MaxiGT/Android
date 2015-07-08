package com.example.proyectofinal;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public class GoogleConnectionHelper implements ConnectionCallbacks, OnConnectionFailedListener, LocationListener {

	public Location LastLocation;
	public Location ActualLocation;
	public LocationRequest LocationRequest;
	public GoogleApiClient GoogleApiClient;
	
	public GoogleConnectionHelper(Context context) {
		
		GoogleApiClient = new GoogleApiClient.Builder(context)
	    .addConnectionCallbacks(this)
	    .addOnConnectionFailedListener(this)
	    .addApi(LocationServices.API).build();
		
		try {
			GoogleApiClient.connect();
			Toast.makeText(context, "Conexion establecida", Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			Toast.makeText(context, "ERROR al establecer conexion", Toast.LENGTH_SHORT).show();
		}
		
		try {
			LastLocation = LocationServices.FusedLocationApi.getLastLocation(GoogleApiClient);
			ActualLocation = LocationServices.FusedLocationApi.getLastLocation(GoogleApiClient);
			
			LocationServices.FusedLocationApi.requestLocationUpdates(
					GoogleApiClient, LocationRequest, (com.google.android.gms.location.LocationListener) this);
			Toast.makeText(context, "Correctamente configurado", Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			Toast.makeText(context, "ERROR durante la configuracion", Toast.LENGTH_SHORT).show();
		}
	}
	
	@Override
	public void onLocationChanged(Location location) {
		LastLocation = ActualLocation;
		ActualLocation = location;
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onConnectionFailed(ConnectionResult result) {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("static-access")
	@Override
	public void onConnected(Bundle connectionHint) {
		LocationRequest = new LocationRequest();
        LocationRequest.setInterval(1000);
        LocationRequest.setFastestInterval(10);
        LocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
	}

	@Override
	public void onConnectionSuspended(int cause) {
		// TODO Auto-generated method stub
		
	}

	public Location getLastLocation() {
		return LastLocation;
	}
	
	public Location getActualLocation() {
		return ActualLocation;
	}
	
}
