package com.example.proyectofinal;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.proyectofinal.DialogHelperMain.NoticeDialogListener;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public class MainActivity extends FragmentActivity implements NoticeDialogListener, ConnectionCallbacks, OnConnectionFailedListener,
LocationListener {

	private Context context;
	private GoogleConnectionHelper GPSHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button btnMisRestaurates = (Button)findViewById(R.id.btnMisRestaurantes);
		Button btnTopTen = (Button)findViewById(R.id.btnTopTen);
		Button btnAgregar = (Button)findViewById(R.id.btnAgregar);
		Button btnVerMapa = (Button)findViewById(R.id.btnVerMapa);
		
		context = getApplicationContext();
		
		try {
			RestauranteDB dbHelper = new RestauranteDB(getApplicationContext());
			Toast.makeText(getApplicationContext(), "DB creada exitosamente", Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), "ERROR durante la creacion de la DB", Toast.LENGTH_SHORT).show();
		}
		
		GPSHelper = new GoogleConnectionHelper(context);
		
		btnMisRestaurates.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), MisRestaurantesActivity.class);
				startActivity(intent);
			}
		});
		
		btnTopTen.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), VerTopTenActivity.class);
				startActivity(intent);
			}
		});
		
		btnAgregar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), AgregarRestauranteActivity.class);
				startActivity(intent);
			}
		});
		
		btnVerMapa.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//Intent intent = new Intent(getApplicationContext(), VerMapaActivity.class);
				//startActivity(intent);
			}
		});
	}
	
	@Override
	public void onBackPressed() {
		DialogFragment newDialog = new DialogHelperMain();
		newDialog.show(getSupportFragmentManager(), "ExitDialog");
	}

	@Override
	public void onDialogPositiveClick(DialogFragment dialog) {
		super.onBackPressed();
	}

	@Override
	public void onDialogNegativeClick(DialogFragment dialog) {
		
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
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

	@Override
	public void onConnected(Bundle connectionHint) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onConnectionSuspended(int cause) {
		// TODO Auto-generated method stub
		
	}

}
