package com.example.clase3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Spinner spinner = (Spinner)findViewById(R.id.spinner);
		
		final Noticia[] datosLista = new Noticia[] {new Noticia("Titulo1", "subTitulo1", 1), new Noticia("Titulo2", "subTitulo2", 2), new Noticia("Titulo3", "subTitulo3", 3)};
		ArrayAdapter<Noticia> adapterLista = new ArrayAdapter<Noticia>(this,  R.layout.lista_layout, datosLista){
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				if (convertView == null) {
					convertView = getLayoutInflater().inflate(R.layout.lista_layout, parent, false);
					Log.d("Clase3 - Ejercicio2", "Creando un nuevo layout");
				} else {
					TextView textTitulo = (TextView)convertView;
					textTitulo.setText(datosLista[position].getTitulo());
					TextView textsubTitulo = (TextView)convertView;
					textsubTitulo.setText(datosLista[position].getsubTitulo());
					Log.d("Clase3 - Ejercicio2", "Utilizando un Layout ya creado");
				}
				return super.getView(position, convertView, parent);
			}
		};
		
		/*String[] datos = new String[] {"Bs. As", "Mar del Plata", "La Boca", "La Plata"};
		ArrayAdapter<String> ciudades = new ArrayAdapter<String>(this, R.layout.spinner_cool, datos){		
					public View getView (int position, View convertView, ViewGroup parent) {
						if (convertView == null) {
							convertView = getLayoutInflater().inflate(R.layout.spinner_cool, parent, false);
							Log.d("Clase3", "Creando una vista nueva");
						} else {
						TextView text = (TextView)convertView;
						text.setText(getItem(position));
						Log.d("Clase3", "Reutilizando una vista ya creada");
						}
						return convertView;
					}
		};
		spinner.setAdapter(ciudades);
		
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				
				switch (position) {
				case 0:
					break;
				case 1:
					break;
				case 2:
					break;
				default:
					break;
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
			}
		});*/
		
	}

}
