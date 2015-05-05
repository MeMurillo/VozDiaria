package com.memurillo.vozdiaria;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.speech.tts.TextToSpeech.OnUtteranceCompletedListener;
import android.speech.tts.UtteranceProgressListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.memurillo.vozdiaria.adapters.NoticiasAdapter;
import com.memurillo.vozdiaria.entidades.Noticia;
import com.memurillo.vozdiaria.util.Constantes;

public class NoticiasActivity extends BaseActivity implements OnInitListener, OnUtteranceCompletedListener{ 
	private NoticiasAdapter noticiasAdapter;
	private ListView listaNoticias;
	private ArrayList<Noticia> arrayNoticias;
	private TextToSpeech ttobj;
	private String nombreDiarioSel;
	private boolean isPlaying = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.noticias);
		iniciarBarra();
		
		Bundle extras = getIntent().getExtras();
		
		ttobj = new TextToSpeech(this, this);
		if (Build.VERSION.SDK_INT >= 15) {
			ttobj.setOnUtteranceProgressListener(utteranceProgressListener);
		}
		else{
			ttobj.setOnUtteranceCompletedListener(this);
		}
		
		nombreDiarioSel = (String) extras.get(getString(R.string.diario_selec));
		ImageButton botonLogoDiario = (ImageButton)findViewById(R.id.botonLogoDiario);
		if (nombreDiarioSel.equalsIgnoreCase(Constantes.CLARIN)){
			botonLogoDiario.setImageResource(R.drawable.logo_detalle_clarin);
			botonLogoDiario.setTag(Constantes.URL_CLARIN_WEB);
		}
		else if (nombreDiarioSel.equalsIgnoreCase(Constantes.AMBITO)){
			botonLogoDiario.setImageResource(R.drawable.logo_detalle_ambito);
			botonLogoDiario.setTag(Constantes.URL_AMBITO_WEB);
		}
		else if (nombreDiarioSel.equalsIgnoreCase(Constantes.LACAPITAL)){
			botonLogoDiario.setImageResource(R.drawable.logo_detalle_lacapital);
			botonLogoDiario.setTag(Constantes.URL_LACAPITAL_WEB);
		}
		else if (nombreDiarioSel.equalsIgnoreCase(Constantes.OLE)){
			botonLogoDiario.setImageResource(R.drawable.logo_detalle_ole);
			botonLogoDiario.setTag(Constantes.URL_OLE_WEB);
		}
		else if (nombreDiarioSel.equalsIgnoreCase(Constantes.ELGRAFICO)){
			botonLogoDiario.setImageResource(R.drawable.logo_detalle_elgrafico);
			botonLogoDiario.setTag(Constantes.URL_ELGRAFICO_WEB);
		}
		else if (nombreDiarioSel.equalsIgnoreCase(Constantes.FOXSPORTS)){
			botonLogoDiario.setImageResource(R.drawable.logo_detalle_foxsports);
			botonLogoDiario.setTag(Constantes.URL_FOXSPORTS_WEB);
		}
		else if (nombreDiarioSel.equalsIgnoreCase(Constantes.LARAZON)){
			botonLogoDiario.setImageResource(R.drawable.logo_detalle_larazon);
			botonLogoDiario.setTag(Constantes.URL_LARAZON_WEB);
		}
		else if (nombreDiarioSel.equalsIgnoreCase(Constantes.DIARIO26)){
			botonLogoDiario.setImageResource(R.drawable.logo_detalle_diario26);
			botonLogoDiario.setTag(Constantes.URL_DIARIO26_WEB);
		}
		else if (nombreDiarioSel.equalsIgnoreCase(Constantes.BAE)){
			botonLogoDiario.setImageResource(R.drawable.logo_detalle_bae);
			botonLogoDiario.setTag(Constantes.URL_BAE_WEB);
		}
		else if (nombreDiarioSel.equalsIgnoreCase(Constantes.ELLITORAL)){
			botonLogoDiario.setImageResource(R.drawable.logo_detalle_ellitoral);
			botonLogoDiario.setTag(Constantes.URL_ELLITORAL_WEB);
		}
		else if (nombreDiarioSel.equalsIgnoreCase(Constantes.LANACION)){
			botonLogoDiario.setImageResource(R.drawable.logo_detalle_lanacion);
			botonLogoDiario.setTag(Constantes.URL_LANACION_WEB);
		}
		else if (nombreDiarioSel.equalsIgnoreCase(Constantes.PAGINA12)){
			botonLogoDiario.setImageResource(R.drawable.logo_detalle_pagina12);
			botonLogoDiario.setTag(Constantes.URL_PAGINA12_WEB);
		}
		else if (nombreDiarioSel.equalsIgnoreCase(Constantes.TELAM)){
			botonLogoDiario.setImageResource(R.drawable.logo_detalle_telam);
			botonLogoDiario.setTag(Constantes.URL_TELAM_WEB);
		}
		else if (nombreDiarioSel.equalsIgnoreCase(Constantes.INFONEWS)){
			botonLogoDiario.setImageResource(R.drawable.logo_detalle_infonews);
			botonLogoDiario.setTag(Constantes.URL_INFONEWS_WEB);
		}
		else if (nombreDiarioSel.equalsIgnoreCase(Constantes.ELCRONISTA)){
			botonLogoDiario.setImageResource(R.drawable.logo_detalle_elcronista);
			botonLogoDiario.setTag(Constantes.URL_ELCRONISTA_WEB);
		}
		else if (nombreDiarioSel.equalsIgnoreCase(Constantes.INFOBAE)){
			botonLogoDiario.setImageResource(R.drawable.logo_detalle_infobae);
			botonLogoDiario.setTag(Constantes.URL_INFOBAE_WEB);
		}
		else if (nombreDiarioSel.equalsIgnoreCase(Constantes.LAPRENSA)){
			botonLogoDiario.setImageResource(R.drawable.logo_detalle_laprensa);
			botonLogoDiario.setTag(Constantes.URL_LAPRENSA_WEB);
		}
		else if (nombreDiarioSel.equalsIgnoreCase(Constantes.LAVOZ)){
			botonLogoDiario.setImageResource(R.drawable.logo_detalle_lavoz);
			botonLogoDiario.setTag(Constantes.URL_LAVOZ_WEB);
		}
		else if (nombreDiarioSel.equalsIgnoreCase(Constantes.PERFIL)){
			botonLogoDiario.setImageResource(R.drawable.logo_detalle_perfil);
			botonLogoDiario.setTag(Constantes.URL_PERFIL_WEB);
		}
		else{
			botonLogoDiario.setImageResource(R.drawable.logo_detalle_laizquierda);
			botonLogoDiario.setTag(Constantes.URL_LAIZQUIERDA_WEB);
		}
		
		botonLogoDiario.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse((String) v.getTag()));
				startActivity(i);
			}
		});
		arrayNoticias = (ArrayList<Noticia>) extras.get(getString(R.string.noticias_array));
		noticiasAdapter = new NoticiasAdapter(this, R.layout.lista_noticias_item, arrayNoticias);
		
		listaNoticias = (ListView)findViewById(R.id.listaNoticias);
		
		listaNoticias.setAdapter(noticiasAdapter);
		
		listaNoticias.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Noticia noti = noticiasAdapter.getItem(position);
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(noti.getLink()));
				startActivity(i);
			}
		});
	}

	@Override
	protected void playButtonClick() {
		if (!isPlaying){
			Toast.makeText(getApplicationContext(), getString(R.string.iniciando_lectura), Toast.LENGTH_LONG).show();
			
			SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
			String velocidadConfigurada = sharedPref.getString("pref_key_velocidad_voz", getString(R.string.voz_velocidad_media));
			if (velocidadConfigurada.equalsIgnoreCase(getString(R.string.voz_velocidad_rapida))){
				ttobj.setSpeechRate(2f); //rápida
			}
			else if (velocidadConfigurada.equalsIgnoreCase(getString(R.string.voz_velocidad_lenta))){
				ttobj.setSpeechRate(0.5f); //lenta
			}
			else{
				ttobj.setSpeechRate(1f); //media
			}
			
			int nroNoticia = 1;
			ttobj.speak("Noticias del diario " + nombreDiarioSel, TextToSpeech.QUEUE_ADD, null);
			for (Noticia noti: arrayNoticias) {
				ttobj.speak("Noticia Número " + nroNoticia, TextToSpeech.QUEUE_ADD, null);
				ttobj.speak("Título: " + noti.getTitulo(), TextToSpeech.QUEUE_ADD, null);
				if (!noti.getDescripcion().trim().equals(""))
					ttobj.speak("Detalle: " + noti.getDescripcion(), TextToSpeech.QUEUE_ADD, null);
				nroNoticia++;
			}
			HashMap<String, String> map = new HashMap<String, String>();
			map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "fin");
			ttobj.speak(getString(R.string.gracias_voz_diaria), TextToSpeech.QUEUE_ADD, map);
			btnPlay.setImageDrawable(getResources().getDrawable(R.drawable.boton_stop_barra));
			isPlaying = true;
		}
		else{
			ttobj.stop();
			btnPlay.setImageDrawable(getResources().getDrawable(R.drawable.boton_play_barra));
			isPlaying = false;
		}
	}
	
	@Override
	public void onInit(int status) {
		if(status != TextToSpeech.ERROR){
			ttobj.setLanguage(Locale.US);
			if (ttobj.isLanguageAvailable(new Locale("spa", "ESP")) >= 0){
				ttobj.setLanguage(new Locale("spa", "ESP"));
			}
		}
	}
	
	@Override
	protected void onDestroy() {
		if(ttobj !=null){
			if (isPlaying)
				ttobj.stop();
			ttobj.shutdown();
		}
		super.onDestroy();
	}
	
	@Override
	public void onUtteranceCompleted(String utteranceId) {
		NoticiasActivity.this.runOnUiThread(new Runnable() {
    	    public void run() {
    	    	btnPlay.setImageDrawable(getResources().getDrawable(R.drawable.boton_play_barra));
    			isPlaying = false;
    	    }
    	});
	}
	
	UtteranceProgressListener utteranceProgressListener = new UtteranceProgressListener() {

        @Override
        public void onStart(String utteranceId) {
        }

        @Override
        public void onError(String utteranceId) {
        }

        @Override
        public void onDone(String utteranceId) {
        	NoticiasActivity.this.runOnUiThread(new Runnable() {
        	    public void run() {
        	    	btnPlay.setImageDrawable(getResources().getDrawable(R.drawable.boton_play_barra));
        			isPlaying = false;
        	    }
        	});
        }
    };
}
