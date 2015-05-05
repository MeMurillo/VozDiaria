package com.memurillo.vozdiaria;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import com.memurillo.vozdiaria.util.Util;

public abstract class BaseActivity extends Activity {

	protected ImageButton btnMenu, btnPlay;
	private ListView listaMenuDrawer;
	private DrawerLayout layoutMenuDrawer;
	
	//Para animar el drawer menu
	private ActionBarDrawerToggle mDrawerToggle;
	private LinearLayout contenidoLayout;
	private RelativeLayout barraLayout;
	private float lastTranslate = 0.0f;
	
	private SharedPreferences configuraciones;
	private Editor editor;
	
	private static final int CONFIGURACIONES = 0;
	private static final int COMPARTIR = 1;
	private static final int ACERCA_DE = 2;
	
	protected abstract void playButtonClick();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		configuraciones = getSharedPreferences(getString(R.string.prefs_claves), 0);
		editor = configuraciones.edit();
	}
	
	protected SharedPreferences getConfiguraciones(){
		return configuraciones;
	}
	
	protected Editor getEditor(){
		return editor;
	}

	protected void iniciarBarra(){
		btnMenu = (ImageButton) findViewById(R.id.btnMenu);
		btnPlay = (ImageButton) findViewById(R.id.btnPlay);
		
		btnPlay.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				playButtonClick();
			}
		});
		
		layoutMenuDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		contenidoLayout = (LinearLayout) findViewById(R.id.contenido_layout);
		listaMenuDrawer = (ListView) findViewById(R.id.left_drawer);
		
		AlphaAnimation blinkanimation = new AlphaAnimation(1, 0.5f);
		blinkanimation.setDuration(2000);
		blinkanimation.setInterpolator(new LinearInterpolator());
		blinkanimation.setRepeatCount(Animation.INFINITE);
		blinkanimation.setRepeatMode(Animation.REVERSE);
		btnPlay.startAnimation(blinkanimation);
		
		int[] imagenesMenu = new int[]{
				R.drawable.icono_configuracion,
				R.drawable.icono_compartir,
				R.drawable.icono_acercade
		};
		
		String[] descripcionesMenu = getResources().getStringArray(R.array.opciones_menu);
		
		List<HashMap<String,String>> mList = new ArrayList<HashMap<String,String>>();
		for(int i=0; i < descripcionesMenu.length; i++){
			HashMap<String, String> hm = new HashMap<String,String>();
			hm.put("flag", Integer.toString(imagenesMenu[i]));
			hm.put("descripcion", descripcionesMenu[i]);
			mList.add(hm);
		}
		
		String[] from = {"flag","descripcion"};
		int[] to = { R.id.flag , R.id.descripcion};
		
		SimpleAdapter sa = new SimpleAdapter(this, mList, R.layout.drawer_list_item, 
				from, to);
		listaMenuDrawer.setAdapter(sa);
		
		listaMenuDrawer.setOnItemClickListener(new DrawerItemClickListener());

		mDrawerToggle = new ActionBarDrawerToggle(this, layoutMenuDrawer,
				R.drawable.boton_menu_off,R.string.abrir_drawer,
				R.string.cerrar_drawer){
			
			public void onDrawerClosed(View view) {
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				invalidateOptionsMenu();
			}

			@Override
			public void onDrawerSlide(View drawerView, float slideOffset) {
				super.onDrawerSlide(drawerView, slideOffset);
				float moveFactor = (listaMenuDrawer.getWidth() * slideOffset);

				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
					contenidoLayout.setTranslationX(moveFactor);
				}
				else{
					TranslateAnimation anim = new TranslateAnimation(lastTranslate, moveFactor, 0.0f, 0.0f);
					anim.setDuration(0);
					anim.setFillAfter(true);
					contenidoLayout.startAnimation(anim);

					lastTranslate = moveFactor;
				}
			}
		};

		layoutMenuDrawer.setDrawerListener(mDrawerToggle);

		btnMenu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				layoutMenuDrawer.openDrawer(listaMenuDrawer);
			}
		});
		
		barraLayout = (RelativeLayout) findViewById(R.id.header);
		ViewTreeObserver vto = barraLayout.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            @SuppressWarnings("deprecation")
			@Override
            public void onGlobalLayout() {
            	if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) 
            		barraLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                else 
                	barraLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                
                MarginLayoutParams mlp = (MarginLayoutParams) listaMenuDrawer.getLayoutParams();
        		mlp.setMargins(0, barraLayout.getHeight(), 0, 0);
            }
        });
	}
	
	private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        	switch (position) {
	    		case CONFIGURACIONES:
	    			if (Util.isOnline(BaseActivity.this)){
						startActivity(new Intent(BaseActivity.this, ConfiguracionesActivity.class));
					}
					else{
						Util.showAlertDialog(BaseActivity.this, 
								getString(R.string.app_name), getString(R.string.mensaje_sin_conexion_internet), 
								getString(R.string.aceptar), new DialogInterface.OnClickListener() {
							        public void onClick(DialogInterface dialog, int which) { 
							        	dialog.dismiss();
							        }
							     }, android.R.drawable.ic_dialog_alert);
					}
	    			break;
	    		case COMPARTIR:
	    			if (Util.isOnline(BaseActivity.this)){
	    				String textoCuerpo = "<html><body>Te recomiendo que descargues:<br><a href=\"http://www.memurillo.com.ar\">Voz Diaria</a></html><br><br>Enviado desde Voz Diaria para Android.</body>";
	    				Intent intent3 = new Intent(Intent.ACTION_SEND);
		    			intent3.setType("text/html");
		    			intent3.putExtra(android.content.Intent.EXTRA_TEXT, Html.fromHtml(textoCuerpo));
		    			intent3.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.compartir_asunto));
		    			startActivity(Intent.createChooser(intent3, getString(R.string.compartir_titulolista)));
					}
					else{
						Util.showAlertDialog(BaseActivity.this, 
								getString(R.string.app_name), getString(R.string.mensaje_sin_conexion_internet), 
								getString(R.string.aceptar), new DialogInterface.OnClickListener() {
							        public void onClick(DialogInterface dialog, int which) { 
							        	dialog.dismiss();
							        }
							     }, android.R.drawable.ic_dialog_alert);
					}
	    			break;
	    		case ACERCA_DE:
					if (Util.isOnline(BaseActivity.this)){
						startActivity(new Intent(BaseActivity.this, AcercaDeActivity.class));
					}
					else{
						Util.showAlertDialog(BaseActivity.this, 
								getString(R.string.app_name), getString(R.string.mensaje_sin_conexion_internet), 
								getString(R.string.aceptar), new DialogInterface.OnClickListener() {
							        public void onClick(DialogInterface dialog, int which) { 
							        	dialog.dismiss();
							        }
							     }, android.R.drawable.ic_dialog_alert);
					}
	    			break;
    		}
        	listaMenuDrawer.setItemChecked(position, true);
            layoutMenuDrawer.closeDrawer(listaMenuDrawer);
        }
    }
}