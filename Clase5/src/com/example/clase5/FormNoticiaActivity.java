package com.example.clase5;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.clase3.R;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class FormNoticiaActivity extends Activity {

	protected static final int CODIGO_NUEVA_IMAGEN = 9001;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_form_noticia);
		
		final EditText titulo = (EditText) findViewById(R.id.titulo);
		final EditText copete = (EditText) findViewById(R.id.copete);
		
		ImageView imagen = (ImageView)findViewById(R.id.imagen);
		
		imagen.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,
                        "Select Picture"), CODIGO_NUEVA_IMAGEN);
				
			}
		});
		findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				
				JSONObject nuevaNoticia = new JSONObject();
				try {
					nuevaNoticia.put("titulo", titulo.getText().toString());
					nuevaNoticia.put("copete", copete.getText().toString());
					intent.putExtra("noticia", nuevaNoticia.toString());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				setResult(RESULT_OK, intent);
				finish();
			}
		});
		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode  == CODIGO_NUEVA_IMAGEN && resultCode == RESULT_OK){
			
			Bitmap bitmap = (Bitmap) data.getExtras().get("data"); 
			
//			Uri selectedImage = data.getData();
//	        String[] filePathColumn = { MediaStore.Images.Media.DATA };
//
//	        Cursor cursor = getContentResolver().query(selectedImage,
//	                filePathColumn, null, null, null);
//	        cursor.moveToFirst();
//
//	        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//	        String picturePath = cursor.getString(columnIndex);
//	        cursor.close();
//
//	        Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
			ImageView imagen = (ImageView)findViewById(R.id.imagen);
			imagen.setImageBitmap(bitmap);

		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
}
