package com.example.proyectofinal;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

public class DetalleActivity extends Activity {
	
	private RestauranteDB dbHelper;
	private Intent intent = new Intent();
	private JSONObject detalle = new JSONObject();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalle);
		//Input JSON - Contiene informacion de detalle
		detalle = (JSONObject) intent.getExtras().get("detalle");
		//CONTROLES OBLIGATORIOS
		final EditText edtNombreDetalle = (EditText)findViewById(R.id.nombreDetalle);
		final EditText edtDireccionDetalle = (EditText)findViewById(R.id.direccionDetalle);
		final EditText edtObservacionesDetalle = (EditText)findViewById(R.id.observacionesDetalle);
		final RatingBar rtRateDetalle = (RatingBar)findViewById(R.id.rateDetalle);
		//CONTROLES NO OBLIGATORIOS
		final EditText edtTelefonoDetalle = (EditText)findViewById(R.id.telefonoDetalle);
		final ImageView imgDetalle = (ImageView)findViewById(R.id.imgDetalle);
		//BOTONES
		Button btnEditarDetalle = (Button)findViewById(R.id.btnEditarDetalle);
		Button btnIrHastaDetalle = (Button)findViewById(R.id.btnIrHastaRestauranteDetalle);
		final Button btnGuardarDetalle = (Button)findViewById(R.id.btnGuardarDetalle);
		
		//Se ocultan aquellos controles NO Obligatorios que no contengan informacion
		//Debido a que se valida al momento de guardar, NOMBRE, DIRECCION, OBSERVACIONES y RATE no deberian estar vacios
		try {
			if (detalle.getString("nombre") == null) {
				edtNombreDetalle.setVisibility(1);
			} else {
				edtNombreDetalle.setText(detalle.getString("nombre"));
			}
			
			if (detalle.getString("direccion") == null) {
				edtDireccionDetalle.setVisibility(1);
			} else {
				edtDireccionDetalle.setText(detalle.getString("direccion"));
			}
			
			if (detalle.getString("observaciones") == null) {
				edtObservacionesDetalle.setVisibility(1);
			} else {
				edtObservacionesDetalle.setText(detalle.getString("observaciones"));
			}
			
			if (detalle.get("rate").toString() == null) {
				rtRateDetalle.setRating(0);
			} else {
				rtRateDetalle.setRating(Float.valueOf(detalle.get("rate").toString()));
			}
			
			if (detalle.getString("telefono") == null) {
				edtTelefonoDetalle.setText("");
			} else {
				edtTelefonoDetalle.setText(detalle.getString("telefono"));
			}
			
			if (detalle.getString("foto") == null) {
				imgDetalle.setVisibility(1);
			} else {
			}
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), "JSON ERROR", Toast.LENGTH_SHORT).show();
		}
		
		//Al seleccionar EDITAR, se modifica el InpuType de los EditText para poder ser modificados
		btnEditarDetalle.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				btnGuardarDetalle.setEnabled(true);
				edtNombreDetalle.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
				edtDireccionDetalle.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
				edtTelefonoDetalle.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
				edtObservacionesDetalle.setInputType(InputType.TYPE_TEXT_FLAG_IME_MULTI_LINE);
			}
		});
		
		//Al seleccionar GUARDAR, se recupera la informacion de todos los controles y se realiza un UPDATE sobre la tabla.
		btnGuardarDetalle.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					//Se recuperan los valores del JSONObject de Input
					ContentValues values = new ContentValues();
					values.put("nombre", edtNombreDetalle.getText().toString());
					values.put("direccion", edtDireccionDetalle.getText().toString());
					values.put("observaciones", edtObservacionesDetalle.getText().toString());
					//values.put("foto", edtNombre.getText().toString());
					values.put("telefono", edtTelefonoDetalle.getText().toString());
					values.put("rate", rtRateDetalle.getRating());
					//Se realiza el Update
					SQLiteDatabase db = dbHelper.getWritableDatabase();
					int rows = db.update("restaurante", values, "id = " + detalle.getInt("id"), null);
					
				} catch (JSONException e) {
					Toast.makeText(getApplicationContext(), "ERROR al guardar", Toast.LENGTH_SHORT).show();
				}
				//Se deshabilita el boton Guardar y los EditText se configuran para no ser editables nuevamente
				btnGuardarDetalle.setEnabled(false);
				edtNombreDetalle.setInputType(InputType.TYPE_NULL);
				edtDireccionDetalle.setInputType(InputType.TYPE_NULL);
				edtTelefonoDetalle.setInputType(InputType.TYPE_NULL);
				edtObservacionesDetalle.setInputType(InputType.TYPE_NULL);
			}
		});
		
		//Al seleccionar IR HASTA, se dispara una Actividad de google que te lleva al Restaurante.
		btnIrHastaDetalle.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
			}
		});
	}
}
