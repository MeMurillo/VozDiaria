package com.memurillo.vozdiaria.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.memurillo.vozdiaria.R;
import com.memurillo.vozdiaria.entidades.Diario;

public class DiariosAdapter extends ArrayAdapter<Diario> {
	
	public DiariosAdapter(Context context, int resource, List<Diario> items) {
	    super(context, resource, items);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	    View v = convertView;
	    if (v == null) {
	        LayoutInflater vi = LayoutInflater.from(getContext());
	        v = vi.inflate(R.layout.lista_diarios_item, null);
	    }
	    
	    Diario diario = getItem(position);
	    if (diario != null) {
	    	TextView tt = (TextView) v.findViewById(R.id.nombre);
	        tt.setText(diario.getNombre());
	        
	        ImageView imageLogo = (ImageView) v.findViewById(R.id.logo);
	        imageLogo.setImageDrawable(getContext().getResources().getDrawable(diario.getLogoDrawable()));
	    }
	    return v;
	}
}