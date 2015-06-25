package com.example.proyectofinal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		Thread background = new Thread() {
            public void run() {
                 
                try {
                	Toast.makeText(getApplicationContext(), "Inicializando", Toast.LENGTH_SHORT).show();
                    sleep(2*1000);
                    Intent i=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                    finish();
                     
                } catch (Exception e) {
                	Toast.makeText(getApplicationContext(), "Error en la inicializacion", Toast.LENGTH_SHORT).show();
                }
            }
        };
         
        // start thread
        background.start();
	}
	
	@Override
	public void onBackPressed() {
		//super.onBackPressed();
	}
}
