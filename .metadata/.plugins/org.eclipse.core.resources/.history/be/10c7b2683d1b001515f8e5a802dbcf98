package com.example.calculadora;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	
	private float ultimoValor = 0;
	private int ultimaOperacion = 0;
	private float pos1 = 0;
	private float pos2 = 0;
	private float pos3 = 0;
	private float pos4 = 0;
	//   0 - Nada
	//   1 - suma
	//   2 - resta
	//   3 - mul
	//   4 - div
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final EditText acumulador = (EditText)findViewById(R.id.numero);
		Button cmdMas = (Button)findViewById(R.id.mas);
		Button cmdMenos= (Button)findViewById(R.id.menos);
		Button cmdPor = (Button)findViewById(R.id.por);
		Button cmdDiv = (Button)findViewById(R.id.dividido);
		Button cmdIgual = (Button)findViewById(R.id.igual);
		Button cmdLimpiar = (Button)findViewById(R.id.limpiar);
		final TextView txtResultado = (TextView)findViewById(R.id.resultado);

		Button cmdMem1 = (Button)findViewById(R.id.mem1);
		Button cmdMem2 = (Button)findViewById(R.id.mem2);
		Button cmdMem3 = (Button)findViewById(R.id.mem3);
		Button cmdMem4 = (Button)findViewById(R.id.mem4);

		Button cmdPos1 = (Button)findViewById(R.id.pos1);
		Button cmdPos2 = (Button)findViewById(R.id.pos2);
		Button cmdPos3 = (Button)findViewById(R.id.pos3);
		Button cmdPos4 = (Button)findViewById(R.id.pos4);
		
		cmdLimpiar.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				acumulador.setText("");
				ultimaOperacion=0;
				ultimoValor=0;
				setValorResultado(0.0f);
			}
		});

		cmdMas.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(ultimaOperacion == 0){
					ultimoValor = getNumeroIngresado();
					ultimaOperacion = 1;
					acumulador.setText("");
				}
			}
		});

		cmdMenos.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (ultimaOperacion == 0) {
					ultimoValor = getNumeroIngresado();
					ultimaOperacion = 2;
					acumulador.setText("");
				}
			}
		});

		cmdDiv.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				if (ultimaOperacion == 0) {
					ultimoValor = getNumeroIngresado();
					ultimaOperacion = 3;
					acumulador.setText("");
				}
			}
		});

		cmdPor.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				if (ultimaOperacion == 0) {
					ultimoValor = getNumeroIngresado();
					ultimaOperacion = 4;
					acumulador.setText("");
				}
			}
		});
		
		cmdIgual.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				float resultado = 0;
				if(ultimaOperacion != 0){
					switch(ultimaOperacion){
					case 0:
						setValorResultado(getNumeroIngresado());
						acumulador.setText("");
					case 1: 
						resultado = ultimoValor + getNumeroIngresado(); 
						setValorResultado(resultado);
					case 2:
						resultado = ultimoValor - getNumeroIngresado();
						setValorResultado(resultado);
					case 3:
						resultado = ultimoValor * getNumeroIngresado();
						setValorResultado(resultado);
					case 4:
						if (getNumeroIngresado() != 0) {
							resultado = ultimoValor / getNumeroIngresado();
							setValorResultado(resultado);
						} else {
							txtResultado.setText("Imposible dividir por 0");
						}
					}
					ultimaOperacion=0;
				}
			}
		});

		cmdMem1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				pos1 = getNumeroIngresado();
			}
		});

		cmdMem2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				pos2 = getNumeroIngresado();
			}
		});

		cmdMem3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				pos3 = getNumeroIngresado();
			}
		});

		cmdMem4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				pos4 = getNumeroIngresado();
			}
		});

		cmdPos1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				acumulador.setText(String.format("%f", pos1));
			}
		});

		cmdPos2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				acumulador.setText(String.format("%f", pos2));
			}
		});

		cmdPos3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				acumulador.setText(String.format("%f", pos3));
			}
		});

		cmdPos4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				acumulador.setText(String.format("%f", pos4));
			}
		});

}
	
	private float getNumeroIngresado(){
		EditText acumulador = (EditText)findViewById(R.id.numero);
		String val = acumulador.getText().toString();
		if(val.length() == 0){
			return 0.0f;
		}else{
			return Float.parseFloat(acumulador.getText().toString());
		}
	}
	
	private void setValorResultado(float res){
		((TextView)findViewById(R.id.resultado)).setText(String.format("%f", res));
	}
}
