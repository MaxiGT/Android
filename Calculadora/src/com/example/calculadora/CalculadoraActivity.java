package com.example.calculadora;

import org.w3c.dom.Text;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CalculadoraActivity extends Activity {

	private int flag = 0;
	private double ultimoValor = 0;
	private int ultimaOperacion = 0;
	//  0 - nada
	//  1 - suma
	//  2 - resta
	//  3 - mul
	//  4 - div
	//	5 - pot
	//	6 - raiz cuadrada
	private double pos1;
	private double pos2;
	private double pos3;
	private double pos4;
	private double acumuladorTotal;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calc);
		
		final TextView acumulador = (TextView)findViewById(R.id.numero);
		Button cmdMas = (Button)findViewById(R.id.mas);
		Button cmdMenos= (Button)findViewById(R.id.menos);
		Button cmdPor = (Button)findViewById(R.id.por);
		Button cmdDiv = (Button)findViewById(R.id.dividido);
		Button cmdIgual = (Button)findViewById(R.id.igual);
		Button cmdLimpiar = (Button)findViewById(R.id.limpiar);
		Button cmdVolver = (Button)findViewById(R.id.volver);
		//POS = Muestra Data
		Button cmdPos1 = (Button)findViewById(R.id.pos1);
		Button cmdPos2 = (Button)findViewById(R.id.pos2);
		Button cmdPos3 = (Button)findViewById(R.id.pos3);
		Button cmdPos4 = (Button)findViewById(R.id.pos4);
		//MEM = Guardan Data
		Button cmdMem1 = (Button)findViewById(R.id.mem1);
		Button cmdMem2 = (Button)findViewById(R.id.mem2);
		Button cmdMem3 = (Button)findViewById(R.id.mem3);
		Button cmdMem4 = (Button)findViewById(R.id.mem4);
		//NUMEROS = Escriben en TextView
		Button num1 = (Button)findViewById(R.id.n1);
		Button num2 = (Button)findViewById(R.id.n2);
		Button num3 = (Button)findViewById(R.id.n3);
		Button num4 = (Button)findViewById(R.id.n4);
		Button num5 = (Button)findViewById(R.id.n5);
		Button num6 = (Button)findViewById(R.id.n6);
		Button num7 = (Button)findViewById(R.id.n7);
		Button num8 = (Button)findViewById(R.id.n8);
		Button num9 = (Button)findViewById(R.id.n9);
		Button num0 = (Button)findViewById(R.id.n0);
		Button cmdPunto = (Button)findViewById(R.id.pto);
		Button cmdBorrar = (Button)findViewById(R.id.borrar);
		//CONTEXT MENU = Menu de mas funciones
		Button masFunciones = (Button)findViewById(R.id.masfunciones);
		registerForContextMenu(masFunciones);
		
		cmdLimpiar.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				acumulador.setText("");
				ultimaOperacion=0;
				ultimoValor=0;
				setValorResultado(0.0f);
				pos1=0;
				pos2=0;
				pos3=0;
				pos4=0;
				findViewById(R.id.pos1).setEnabled(false);
				findViewById(R.id.pos2).setEnabled(false);
				findViewById(R.id.pos3).setEnabled(false);
				findViewById(R.id.pos4).setEnabled(false);
				flag = 0;
			}
		});
		
		
		cmdBorrar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (acumulador.getText().toString() != null) {
					String nuevoValor = acumulador.getText().toString();
					nuevoValor = nuevoValor.substring(0, nuevoValor.length() - 1);
					acumulador.setText(nuevoValor);
				}
			}
		});

		cmdMas.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				botonPresionado(1, flag);
			}
		});
		
		cmdMenos.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				botonPresionado(2, flag);
			}
		});
		
		cmdPor.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				botonPresionado(3, flag);
			}
		});
		
		cmdDiv.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				botonPresionado(4, flag);
			}
		});
		
		cmdIgual.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				double resultado = 0;
				if(ultimaOperacion != 0){
						switch(ultimaOperacion){
						case 0:
							setValorResultado(getNumeroIngresado());
							acumulador.setText("");
							break;
						case 1: 
							resultado = ultimoValor + getNumeroIngresado(); 
							setValorResultado(resultado);
							break;
						case 2:
							resultado = ultimoValor - getNumeroIngresado(); 
							setValorResultado(resultado);
							break;
						case 3:
							resultado = ultimoValor * getNumeroIngresado(); 
							setValorResultado(resultado);
							break;
						case 4:
							if (getNumeroIngresado() != 0) {
								resultado = ultimoValor / getNumeroIngresado(); 
								setValorResultado(resultado);
							} else {
								Intent intent = new Intent(CalculadoraActivity.this, ErrorActivity.class);
								Bundle b = new Bundle();
								b.putString("MENSAJE", "Imposible dividir por 0");
								intent.putExtras(b);
								startActivity(intent);
							}
							break;
						case 5:
							Double.valueOf(String.valueOf(ultimoValor));
							resultado = Math.pow(ultimoValor, getNumeroIngresado());
							setValorResultado(resultado);
							break;
						/*case 6:
							resultado = Math.sqrt(ultimoValor);
							setValorResultado(resultado);
							break;*/
						}
					ultimaOperacion=0;
					acumulador.setText("");
					flag = 1;
				}
			}
		});
		
		cmdMem1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (getNumeroIngresado() != 0.0f) {
					pos1 = guardarValorEnMemoria(pos1);
					findViewById(R.id.pos1).setEnabled(true);
				}
			}
		});
		
		cmdMem2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (getNumeroIngresado() != 0.0f) {
					pos2 = guardarValorEnMemoria(pos2);
					findViewById(R.id.pos2).setEnabled(true);
				}
			}
		});
		
		cmdMem3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (getNumeroIngresado() != 0.0f) {
					pos3 = guardarValorEnMemoria(pos3);
					findViewById(R.id.pos3).setEnabled(true);
				}
			}
		});
		
		cmdMem4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (getNumeroIngresado() != 0.0f) {
					pos4 = guardarValorEnMemoria(pos4);
					findViewById(R.id.pos4).setEnabled(true);
				}
			}
		});
		
		cmdPos1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mostrarMemoria(pos1);
			}
		});
		
		cmdPos2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mostrarMemoria(pos2);
			}
		});
		
		cmdPos3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mostrarMemoria(pos3);
			}
		});
		
		cmdPos4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mostrarMemoria(pos4);
			}
		});
		
////////////////////////////////BUTTON BEGIN////////////////////////////////
		num1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				acumulador.setText(acumulador.getText().toString() + "1");
			}
		});
		
		num2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				acumulador.setText(acumulador.getText().toString() + "2");
			}
		});
		
		num3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				acumulador.setText(acumulador.getText().toString() + "3");
			}
		});
		
		num4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				acumulador.setText(acumulador.getText().toString() + "4");
			}
		});
		
		num5.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				acumulador.setText(acumulador.getText().toString() + "5");
			}
		});
		
		num6.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				acumulador.setText(acumulador.getText().toString() + "6");
			}
		});
		
		num7.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				acumulador.setText(acumulador.getText().toString() + "7");
			}
		});
		
		num8.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				acumulador.setText(acumulador.getText().toString() + "8");
			}
		});
		
		num9.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				acumulador.setText(acumulador.getText().toString() + "9");
			}
		});
		
		num0.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				acumulador.setText(acumulador.getText().toString() + "0");
			}
		});
		
		cmdPunto.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				acumulador.setText(acumulador.getText().toString() + ".");
			}
		});
////////////////////////////////BUTTON END////////////////////////////////	
		
		cmdVolver.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intentVolver = new Intent(CalculadoraActivity.this, MainActivity.class);
				startActivity(intentVolver);
			}
		});
	}
	
	@Override
	public void onCreateContextMenu(android.view.ContextMenu menu, View v, android.view.ContextMenu.ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.mas_funciones_menu, menu);
	};
	
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch(item.getItemId()) {
			case R.id.potencia:
				botonPresionado(5, flag);
				return true;
			case R.id.raiz:
				botonPresionado(6, flag);
				return true;
			default:
				return super.onContextItemSelected(item);
		}
	};
	

	private double getNumeroIngresado(){
		TextView acumulador = (TextView)findViewById(R.id.numero);
		String val = acumulador.getText().toString();
		if(val.length() == 0){
			return 0.0d;
		}else{
			return Double.valueOf(val);
		}
	}
	
	private void setValorResultado(double res){
		TextView resultado = (TextView)findViewById(R.id.resultado);
		
		resultado.setText(String.valueOf(res));
		acumuladorTotal = res;
	}
	
	private double guardarValorEnMemoria (double pos12) {
		TextView acumulador = (TextView)findViewById(R.id.numero);
		pos12 = getNumeroIngresado();
		acumulador.setText("");
		return pos12;
	}
	
	private void botonPresionado (int numBoton, int flag) {
		final TextView acumulador = (TextView)findViewById(R.id.numero);
		if (flag == 0) {
			ultimoValor = getNumeroIngresado();
			ultimaOperacion = numBoton;
			acumulador.setText("");
		} else {
			ultimoValor = acumuladorTotal;
			ultimaOperacion = numBoton;
			acumulador.setText("");
		}
		if (numBoton == 6) {
			double resultado = Math.sqrt(ultimoValor);
			setValorResultado(resultado);
		}
	}
	
	private void mostrarMemoria (double pos12) {
		TextView acumulador = (TextView)findViewById(R.id.numero);
		acumulador.setText(String.valueOf(pos12));
	}
}