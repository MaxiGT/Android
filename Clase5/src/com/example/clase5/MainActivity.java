package com.example.clase5;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.clase3.R;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {

	protected static final int CODIGO_NUEVA_NOTICIA = 9000;
	private AdapterNoticias adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ListView lista = (ListView)findViewById(R.id.lista);
		
		// Set de datos:
		List<JSONObject> noticias = new ArrayList<JSONObject>();
		

		
		adapter = new AdapterNoticias(this, noticias);
		lista.setAdapter(adapter);

		findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, FormNoticiaActivity.class);
				startActivityForResult(intent, CODIGO_NUEVA_NOTICIA);
				
			}
		});
		
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == CODIGO_NUEVA_NOTICIA && resultCode == RESULT_OK){
			try {
				JSONObject noticia = new JSONObject(data.getStringExtra("noticia"));
				adapter.addItem(noticia);
				adapter.notifyDataSetChanged();
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		super.onActivityResult(requestCode, resultCode, data);
	}
}
