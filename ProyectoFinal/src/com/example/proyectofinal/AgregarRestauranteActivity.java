package com.example.proyectofinal;

import java.util.ArrayList;

import org.json.JSONObject;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;

public class AgregarRestauranteActivity extends Activity {
	
	private RestauranteDB dbHelper;
	private Location location;
	private GoogleConnectionHelper GPSHelper;
	private JSONObject intentLocation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_agregar_restaurante);
		
		final ArrayList<Long> ids = new ArrayList<Long>();
		
		final EditText edtNombre = (EditText)findViewById(R.id.nombreAgregar);
		final EditText edtDireccion = (EditText)findViewById(R.id.direccionAgregar);
		final EditText edtTelefono = (EditText)findViewById(R.id.telefonoAgregar);
		final EditText edtObservaciones = (EditText)findViewById(R.id.observacionesAgregar);
		final RatingBar rtRate = (RatingBar)findViewById(R.id.rateAgregar);
		final ImageView imgAgregar = (ImageView)findViewById(R.id.imgAgregar);
		
		final Button btnUbicarRestaurante = (Button)findViewById(R.id.btnUbicarRestaurante);
		final Button btnBuscarPosicionActual = (Button)findViewById(R.id.btnMiPosicionActualAgregar);
		final Button btnGuardarAgregar = (Button)findViewById(R.id.btnGuardarAgregar);
		
		GPSHelper = new GoogleConnectionHelper(getApplicationContext());
		
		
		btnGuardarAgregar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				if (rtRate.getRating() == 0.0f) {
					Toast.makeText(getApplicationContext(), "Rate Invalido", Toast.LENGTH_LONG).show();
				} else if (edtNombre.getText().toString() == null) {
					Toast.makeText(getApplicationContext(), "Nombre Faltante", Toast.LENGTH_LONG).show();
				} else if (edtDireccion.getText().toString() == null) {
					Toast.makeText(getApplicationContext(), "Direccion Faltante", Toast.LENGTH_LONG).show();
				} else if (edtObservaciones.getText().toString() == null) {
					Toast.makeText(getApplicationContext(), "Observaciones Faltante", Toast.LENGTH_LONG).show();
				} else {
					SQLiteDatabase db = dbHelper.getWritableDatabase();
					
					ContentValues values = new ContentValues();
					values.put("nombre", edtNombre.getText().toString());
					values.put("direccion", edtDireccion.getText().toString());
					values.put("observaciones", edtObservaciones.getText().toString());
					//values.put("foto", edtNombre.getText().toString());
					values.put("telefono", edtTelefono.getText().toString());
					values.put("rate", rtRate.getRating());
					
					try {
						long newRowInserted = db.insert("restaurante", null, values);
						ids.add(newRowInserted);
						Toast.makeText(getApplicationContext(), "Restaurante insertado", Toast.LENGTH_SHORT).show();
					} catch (Exception e) {
						Toast.makeText(getApplicationContext(), "Fallo al insertar", Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
		
		btnUbicarRestaurante.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
			}
		});
		
		btnBuscarPosicionActual.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				location = GPSHelper.getActualLocation();
				
				try {
					intentLocation.put("latitud", location.getLatitude());
					intentLocation.put("longitud", location.getLongitude());
					
					Intent intent = new Intent(getApplicationContext(), FetchAddressIntentService.class);
					intent.putExtra("location", intentLocation.toString());
					startActivity(intent);
					
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(), "JSON ERROR", Toast.LENGTH_SHORT).show();
				}
				
			}
		});
		
	}
}
