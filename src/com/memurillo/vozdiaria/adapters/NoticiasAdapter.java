package com.memurillo.vozdiaria.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.memurillo.vozdiaria.R;
import com.memurillo.vozdiaria.entidades.Noticia;

public class NoticiasAdapter extends ArrayAdapter<Noticia> {
	
	public NoticiasAdapter(Context context, int resource, List<Noticia> items) {
	    super(context, resource, items);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	    View v = convertView;
	    if (v == null) {
	        LayoutInflater vi = LayoutInflater.from(getContext());
	        v = vi.inflate(R.layout.lista_noticias_item, null);
	    }
	    
	    Noticia noti = getItem(position);
	    if (noti != null) {
	    	TextView tt = (TextView) v.findViewById(R.id.titulo);
	        tt.setText(noti.getTitulo());
	        
	        TextView tt2 = (TextView) v.findViewById(R.id.descripcion);
	        tt2.setText(noti.getDescripcion()); //Html.fromHtml(noti.getDescripcion())
	    }
	    return v;
	}
}