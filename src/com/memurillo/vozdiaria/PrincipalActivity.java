package com.memurillo.vozdiaria;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.speech.tts.TextToSpeech.OnUtteranceCompletedListener;
import android.speech.tts.UtteranceProgressListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.memurillo.vozdiaria.adapters.DiariosAdapter;
import com.memurillo.vozdiaria.entidades.Diario;
import com.memurillo.vozdiaria.entidades.ErrorVozDiaria;
import com.memurillo.vozdiaria.entidades.Noticia;
import com.memurillo.vozdiaria.util.Constantes;
import com.memurillo.vozdiaria.util.Util;
import com.memurillo.vozdiaria.ws.NoticiasWS;
import com.memurillo.vozdiaria.ws.NoticiasWS.NoticiasListener;

public class PrincipalActivity extends BaseActivity implements NoticiasListener, OnInitListener, OnUtteranceCompletedListener {
	private ListView listaDiarios;
	private DiariosAdapter diariosAdapter;
	private Dialog cargandoDialog;
	private TextToSpeech ttobj;
	private boolean isPlaying = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.principal);
		
		iniciarBarra();
		
		ttobj = new TextToSpeech(this, this);
		if (Build.VERSION.SDK_INT >= 15) {
			ttobj.setOnUtteranceProgressListener(utteranceProgressListener);
		}
		else{
			ttobj.setOnUtteranceCompletedListener(this);
		}
		
		cargandoDialog = Util.crearCargandoDialog(this);
		
		listaDiarios = (ListView)findViewById(R.id.listaDiarios);
		
		ArrayList<Diario> arrayDiarios = new ArrayList<Diario>();
		arrayDiarios.add(new Diario(Constantes.ADNRAFAELA, R.drawable.logo_adnrafaela));
		arrayDiarios.add(new Diario(Constantes.AMBITO, R.drawable.logo_ambito));
		arrayDiarios.add(new Diario(Constantes.BAE, R.drawable.logo_bae));
		arrayDiarios.add(new Diario(Constantes.CLARIN, R.drawable.logo_clarin));
		arrayDiarios.add(new Diario(Constantes.DIARIO26, R.drawable.logo_diario26));
		arrayDiarios.add(new Diario(Constantes.ELCOLONOOESTE, R.drawable.logo_colonooeste));
		arrayDiarios.add(new Diario(Constantes.ELCRONISTA, R.drawable.logo_elcronista));
		arrayDiarios.add(new Diario(Constantes.ELGRAFICO, R.drawable.logo_elgrafico));
		arrayDiarios.add(new Diario(Constantes.ELLITORAL, R.drawable.logo_ellitoral));
		arrayDiarios.add(new Diario(Constantes.FOXSPORTS, R.drawable.logo_foxsports));
		arrayDiarios.add(new Diario(Constantes.INFONEWS, R.drawable.logo_infonews));
		arrayDiarios.add(new Diario(Constantes.INFOBAE, R.drawable.logo_infobae));
		arrayDiarios.add(new Diario(Constantes.LACAPITAL, R.drawable.logo_lacapital));
		arrayDiarios.add(new Diario(Constantes.LAIZQUIERDA, R.drawable.logo_izquierda));
		arrayDiarios.add(new Diario(Constantes.LANACION, R.drawable.logo_lanacion));
		arrayDiarios.add(new Diario(Constantes.LAOPINIONRAFAELA, R.drawable.logo_laopinionrafaela));
		arrayDiarios.add(new Diario(Constantes.LAPRENSA, R.drawable.logo_laprensa));
		arrayDiarios.add(new Diario(Constantes.LARAZON, R.drawable.logo_larazon));
		arrayDiarios.add(new Diario(Constantes.LAVOZ, R.drawable.logo_lavoz));
		arrayDiarios.add(new Diario(Constantes.PAGINA12, R.drawable.logo_pagina12));
		arrayDiarios.add(new Diario(Constantes.PERFIL, R.drawable.logo_perfil));
		arrayDiarios.add(new Diario(Constantes.R24N, R.drawable.logo_r24n));
		arrayDiarios.add(new Diario(Constantes.TELAM, R.drawable.logo_telam));
		
		diariosAdapter = new DiariosAdapter(this, R.layout.lista_diarios_item,
				arrayDiarios);
		
		listaDiarios.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				cargandoDialog.show();
				if (isPlaying){
					ttobj.stop();
					btnPlay.setImageDrawable(getResources().getDrawable(R.drawable.boton_play_barra));
    				isPlaying = false;
				}
				Diario diario = diariosAdapter.getItem(position);
				NoticiasWS notiWS = new NoticiasWS();
				notiWS.getNoticias(diario.getNombre(), PrincipalActivity.this, PrincipalActivity.this);
			}
		});
		
		listaDiarios.setAdapter(diariosAdapter);
	}
	
	@Override
	public void onResponseOkNoticias(ArrayList<Noticia> arrayResp, String nombreDiarioSel) {
		cargandoDialog.dismiss();
		
		if (nombreDiarioSel == null){  //Todas las noticias
			SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
			String velocidadConfigurada = sharedPref.getString("pref_key_velocidad_voz", getString(R.string.voz_velocidad_media));
			String cantNoticiasConfigurada = sharedPref.getString("pref_key_cant_noticias", Constantes.CANTIDAD_NOTICIAS_XDEFECTO);
			
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
			for (Noticia noti: arrayResp) {
				ttobj.speak("Noticia del diario " + noti.getDiario(), TextToSpeech.QUEUE_ADD, null);
				ttobj.speak("Noticia Número " + nroNoticia, TextToSpeech.QUEUE_ADD, null);
				ttobj.speak("Título: " + noti.getTitulo(), TextToSpeech.QUEUE_ADD, null);
				if (!noti.getDescripcion().trim().equals(""))
					ttobj.speak("Detalle: " + noti.getDescripcion(), TextToSpeech.QUEUE_ADD, null);
				nroNoticia++;
				if (nroNoticia > Integer.parseInt(cantNoticiasConfigurada)){
					nroNoticia = 1;
				}
			}
			HashMap<String, String> map = new HashMap<String, String>();
			map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "fin");
			ttobj.speak(getString(R.string.gracias_voz_diaria), TextToSpeech.QUEUE_ADD, map);
			btnPlay.setImageDrawable(getResources().getDrawable(R.drawable.boton_stop_barra));
			isPlaying = true;
		}
		else{
			Intent intent = new Intent(PrincipalActivity.this, 
					NoticiasActivity.class);
			intent.putExtra(getString(R.string.noticias_array), arrayResp);
			intent.putExtra(getString(R.string.diario_selec), nombreDiarioSel);
			startActivity(intent);
		}
	}

	@Override
	public void onResponseError(ErrorVozDiaria error) {
		cargandoDialog.dismiss();
		if (error == null){
			Toast.makeText(this, getString(R.string.no_hay_noticias_ahora), Toast.LENGTH_SHORT).show();
		}
		else{
			Toast.makeText(this, "Error Nro: " 
					+ error.getCode() + " - " + error.getDetalle(), Toast.LENGTH_SHORT).show();
		}
	}
	
	@Override
	public void onResponseException(Exception ex) {
		cargandoDialog.dismiss();
		Toast.makeText(this, "Ocurrio un error inesperado \n " + ex.getMessage(), Toast.LENGTH_SHORT).show();
	}
	
	@Override
	protected void playButtonClick() {
		if (!isPlaying){
			Toast.makeText(getApplicationContext(), getString(R.string.iniciando_lectura), Toast.LENGTH_LONG).show();
			NoticiasWS notiTodasWS = new NoticiasWS();
			notiTodasWS.getNoticias(null, this, this);
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
	public void onBackPressed() {
		Util.showAlertDialog(this, getString(R.string.app_name), 
				getString(R.string.confirmacion_salir), 
				getString(R.string.aceptar), 
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) { 
				finish();
			}
		},
		getString(R.string.cancelar),
		new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {}
        });
	}

	@Override
	public void onUtteranceCompleted(String utteranceId) {
		PrincipalActivity.this.runOnUiThread(new Runnable() {
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
        	PrincipalActivity.this.runOnUiThread(new Runnable() {
        	    public void run() {
        	    	btnPlay.setImageDrawable(getResources().getDrawable(R.drawable.boton_play_barra));
        			isPlaying = false;
        	    }
        	});
        }
    };
}
