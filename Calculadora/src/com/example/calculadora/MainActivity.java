package com.example.calculadora;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final String[] datos = new String[] {"", "Calculadora", "App.01", "App. 02"};
		ArrayAdapter<String> adapter = new ArrayAdapter<String> (this, android.R.layout.simple_spinner_item, datos);
		
		final String[] datosTabla = new String[] {"Calculadora", "App. 02", "App. 03"};
		ArrayAdapter<String> adapterTabla = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datosTabla);
		
		final RadioGroup rg = (RadioGroup)findViewById(R.id.rgApps);
		final Spinner comboBox = (Spinner)findViewById(R.id.comboApps);
		final GridView tabla = (GridView)findViewById(R.id.gridOpciones);
		
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
		comboBox.setAdapter(adapter);
		tabla.setAdapter(adapterTabla);				
		
		tabla.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String seleccion = parent.getItemAtPosition(position).toString();
				if (seleccion == "Calculadora") {
					Intent intentTabla = new Intent(MainActivity.this, CalculadoraActivity.class);
					startActivity(intentTabla);
				}
			}
		});
		
		
		comboBox.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				
				Integer posSeleccionada = comboBox.getSelectedItemPosition();
				Integer cantMaxima = comboBox.getCount();
				
				for (Integer i = 0; i < cantMaxima; i++) {
					switch (posSeleccionada) {
					case 1:
						Intent intentcombo = new Intent(MainActivity.this, CalculadoraActivity.class);
						comboBox.setSelection(0);
						startActivity(intentcombo);
						break;
					default:
						break;
					}
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
			}
			
		});
		
		rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				
				//rg.clearCheck();
				switch (checkedId) {
				case R.id.Calculadora:
					Intent intentSpinner= new Intent(MainActivity.this, CalculadoraActivity.class);
					startActivity(intentSpinner);
					break;
				case R.id.App01:
					break;
				case R.id.App02:
					break;
				}
			}
		});
	}
	
	@Override
	public void onBackPressed() {
		//super.onBackPressed();
	}
}
