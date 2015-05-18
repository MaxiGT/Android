package com.example.calculadora;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioGroup;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final RadioGroup rg = (RadioGroup)findViewById(R.id.rgApps);
		
		rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				
				//rg.clearCheck();
				switch (checkedId) {
				case R.id.Calculadora:
					Intent intent = new Intent(MainActivity.this, CalculadoraActivity.class);
					startActivity(intent);
					break;
				case R.id.App01:
					break;
				case R.id.App02:
					break;
				}
			}
		});
		
		
	}
}
