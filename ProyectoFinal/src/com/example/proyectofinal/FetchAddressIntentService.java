package com.example.proyectofinal;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import org.json.JSONObject;

import android.app.IntentService;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.widget.Toast;

public class FetchAddressIntentService extends IntentService {
	
	String errorMessage;
	Location location;
	List<Address> address = null;
	JSONObject b = new JSONObject();

	public FetchAddressIntentService(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
		
		try {
			b = (JSONObject) intent.getExtras().get("location");
			location.setLatitude(b.getDouble("latitud"));
			location.setLongitude(b.getDouble("longitud"));
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), "JSON ERROR", Toast.LENGTH_SHORT).show();
		}
		
		try {
			address = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
		} catch (IOException e) {
			errorMessage = "IO Exception";
		} catch (IllegalArgumentException e) {
			errorMessage = "IllegalArgument Exception";
		}
		
	}
	
	public final class Constants {
	    public static final int SUCCESS_RESULT = 0;
	    public static final int FAILURE_RESULT = 1;
	    public static final String PACKAGE_NAME = "com.google.android.gms.location.sample.locationaddress";
	    public static final String RECEIVER = PACKAGE_NAME + ".RECEIVER";
	    public static final String RESULT_DATA_KEY = PACKAGE_NAME + ".RESULT_DATA_KEY";
	    public static final String LOCATION_DATA_EXTRA = PACKAGE_NAME + ".LOCATION_DATA_EXTRA";
	}

}
