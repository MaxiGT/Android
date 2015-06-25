package com.example.proyectofinal;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.proyectofinal.DialogHelperMain.NoticeDialogListener;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class MisRestaurantesActivity extends FragmentActivity implements NoticeDialogListener, ConnectionCallbacks, OnConnectionFailedListener,
LocationListener {
	//CONSTANTES
	public static final String DB_COLUMN_1 = "id";
	public static final String DB_COLUMN_2 = "nombre";
	public static final String DB_COLUMN_3 = "direccion";
	public static final String DB_COLUMN_4 = "observaciones";
	public static final String DB_COLUMN_5 = "foto";
	public static final String DB_COLUMN_6 = "telefono";
	public static final String DB_COLUMN_7 = "rate";
	public final static String DB_QUERY_ALL = "SELECT * FROM restaurante ORDER BY nombre ASC";
	//VAR Adapter
	private ArrayAdapter<RegistroDB> adapterLista;
	final ArrayList<RegistroDB> datosLista = new ArrayList<RegistroDB>();
	//VAR JSON Detalle Restaurantes
	public JSONObject detalle;
	//VAR Borrado registro
	public RestauranteDB dbHelper;
	private int posicionBorrar;
	private long idBorrar;
	//VAR GPS
	private Location location;
	private GoogleConnectionHelper GPSHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mis_restaurantes);
		
		ListView lstMisRestaurantes = (ListView)findViewById(R.id.listaMisRestaurantes);
		
		try {
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			Cursor cursor = db.rawQuery(DB_QUERY_ALL, null);
			cursor.moveToFirst();
			
			//final ArrayList<RegistroDB> datosLista = new ArrayList<RegistroDB>();
			
			while (!cursor.isAfterLast()) {
				int IndexID = cursor.getColumnIndex(DB_COLUMN_1);
				int id = cursor.getInt(IndexID);
				
				int IndexNombre = cursor.getColumnIndex(DB_COLUMN_2);
				String nombre = cursor.getString(IndexNombre);
				
				int IndexDireccion = cursor.getColumnIndex(DB_COLUMN_3);
				String direccion = cursor.getString(IndexDireccion);
				
				int IndexObservaciones = cursor.getColumnIndex(DB_COLUMN_4);
				String observaciones = cursor.getString(IndexObservaciones);
				
				int IndexFoto = cursor.getColumnIndex(DB_COLUMN_5);
				String foto = cursor.getString(IndexFoto);
				
				int IndexTelefono = cursor.getColumnIndex(DB_COLUMN_6);
				String telefono = cursor.getString(IndexTelefono);
				
				int IndexRate = cursor.getColumnIndex(DB_COLUMN_7);
				Float rate = cursor.getFloat(IndexRate);
				
				RegistroDB registroNuevo = new RegistroDB(id, nombre, direccion, observaciones, foto, telefono, rate);
				datosLista.add(registroNuevo);
				
				cursor.moveToNext();
			}
			
			cursor.close();
			
			adapterLista = new ArrayAdapter<RegistroDB>(getApplicationContext(), R.layout.lista_custom, datosLista) {
				@Override
				public View getView(int position, View convertView, ViewGroup parent) {
					if (convertView == null) {
						convertView = getLayoutInflater().inflate(R.layout.lista_custom, parent, false);
					} else {
						TextView txtNombre = (TextView)findViewById(R.id.nombreLista);
						txtNombre.setText(datosLista.get(position).getNombre());
						
						TextView txtDireccion = (TextView)findViewById(R.id.direccionLista);
						txtDireccion.setText(datosLista.get(position).getDireccion());
						
						//ImageView imgLista = (ImageView)findViewById(R.id.imageLista);
						
						TextView txtTelefono = (TextView)findViewById(R.id.telefonoLista);
						txtTelefono.setText(datosLista.get(position).getTelefono());
						
						RatingBar rateLista = (RatingBar)findViewById(R.id.rateLista);
						rateLista.setRating(datosLista.get(position).getRate());
					}
					
					return convertView;
				}
			};
			
			lstMisRestaurantes.setAdapter(adapterLista);
			
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), "ERROR al asignar el Adapter", Toast.LENGTH_SHORT).show();
		}
		
		
		
		
		
		
		
		lstMisRestaurantes.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				posicionBorrar = position;
				idBorrar = id;
				
				DialogFragment newDialog = new DialogHelperMisRestaurantes();
				newDialog.show(getSupportFragmentManager(), "DeleteDialog");
				return false;
			}
		});
		
		lstMisRestaurantes.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				try {
					detalle.put(DB_COLUMN_1, datosLista.get(position).getId());
					detalle.put(DB_COLUMN_2, datosLista.get(position).getNombre());
					detalle.put(DB_COLUMN_3, datosLista.get(position).getDireccion());
					detalle.put(DB_COLUMN_4, datosLista.get(position).getObservaciones());
					detalle.put(DB_COLUMN_5, datosLista.get(position).getFoto());
					detalle.put(DB_COLUMN_6, datosLista.get(position).getTelefono());
					detalle.put(DB_COLUMN_7, datosLista.get(position).getRate());
					
					Intent intent = new Intent(getApplicationContext(), DetalleActivity.class);
					intent.putExtra("detalle", detalle.toString());
					startActivity(intent);
				} catch (JSONException e) {
					Toast.makeText(getApplicationContext(), "JSON ERROR", Toast.LENGTH_SHORT).show();
				}
			}
		});
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

	@Override
	public void onDialogPositiveClick(DialogFragment dialog) {
		
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		int registros = db.delete("restaurante", "id = " + idBorrar, null);
		
		datosLista.remove(posicionBorrar);
		adapterLista.notifyDataSetChanged();
	}

	@Override
	public void onDialogNegativeClick(DialogFragment dialog) {
		// TODO Auto-generated method stub
		
	}
}
