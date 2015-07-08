package com.example.clase5;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.clase3.R;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterNoticias extends BaseAdapter{

	private Activity activity;
	private List<JSONObject> noticias;

	public  AdapterNoticias(Activity activity, List<JSONObject> noticias) {
		this.activity = activity;
		this.noticias = noticias;
	}
	
	
	public void addItem(JSONObject obj){
		this.noticias.add(obj);
	}
	
	public JSONObject removeItem(int position){
		return this.noticias.remove(position);
	}
	
	@Override
	public int getCount() {
		return noticias.size();
	}

	@Override
	public JSONObject getItem(int position) {
		// TODO Auto-generated method stub
		return noticias.get(position);
	}

	@Override
	public long getItemId(int position) {

		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if(convertView == null){
			LayoutInflater inflator = activity.getLayoutInflater();
			convertView = inflator.inflate(R.layout.item_de_lista, parent, false);
		}

		TextView titulo = (TextView)convertView.findViewById(R.id.titulo);
		TextView copete = (TextView)convertView.findViewById(R.id.copete);
		ImageView imagen = (ImageView)convertView.findViewById(R.id.imagen);
		
		JSONObject noticia = getItem(position);
		
		try {
			titulo.setText(noticia.getString("titulo"));
			copete.setText(noticia.getString("copete"));
//		imagen.setImageResource(noticia.idImagen);
		} catch (JSONException e) {
			Log.d("JSONError:", e.getMessage());
		}
		
		return convertView;
	}
	
	
	

}
