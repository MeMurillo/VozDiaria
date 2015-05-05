package com.memurillo.vozdiaria.ws;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

import com.memurillo.vozdiaria.entidades.ErrorVozDiaria;
import com.memurillo.vozdiaria.entidades.Noticia;
import com.memurillo.vozdiaria.util.Constantes;

public class HttpRequest extends AsyncTask<Void, Void, ArrayList<Noticia>> {
	private HttpRequestListener _listener;
	private ErrorVozDiaria error;
	private String nombreDiarioSel;
	private Context context;
	
	public interface HttpRequestListener {
	    public void respuestaOk(ArrayList<Noticia> array);
	    public void respuestaError(ErrorVozDiaria error);
	}
	
	public HttpRequest(HttpRequestListener list, Context context, String nombreDiarioSel){
		_listener = list;
		this.context = context;
		this.nombreDiarioSel = nombreDiarioSel;
	}
	
	@Override
	protected ArrayList<Noticia> doInBackground(Void...params) {
		ArrayList<Noticia> arrayResp = new ArrayList<Noticia>();
		try {
			XMLParserInterface xmlInterf = null;
			URL url = null;
			SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
			String cantNoticiasConfigurada = sharedPref.getString("pref_key_cant_noticias", Constantes.CANTIDAD_NOTICIAS_XDEFECTO);
			boolean confAmbito = sharedPref.getBoolean("pref_key_reprod_ambito", true);
			boolean confBAE = sharedPref.getBoolean("pref_key_reprod_bae", true);
			boolean confClarin = sharedPref.getBoolean("pref_key_reprod_clarin", true);
			boolean confElCronista = sharedPref.getBoolean("pref_key_reprod_elcronista", true);
			boolean confElLitoral = sharedPref.getBoolean("pref_key_reprod_litoral", true);
			boolean confIzquierda = sharedPref.getBoolean("pref_key_reprod_izquierda", true);
			boolean confLaNacion = sharedPref.getBoolean("pref_key_reprod_lanacion", true);
			boolean confLaPrensa = sharedPref.getBoolean("pref_key_reprod_laprensa", true);
			boolean confPagina12 = sharedPref.getBoolean("pref_key_reprod_pagina12", true);
			boolean confPerfil = sharedPref.getBoolean("pref_key_reprod_perfil", true);
			boolean confTelam = sharedPref.getBoolean("pref_key_reprod_telam", true);
			boolean confInfoNews = sharedPref.getBoolean("pref_key_reprod_infonews", true);
			boolean confInfobae = sharedPref.getBoolean("pref_key_reprod_infobae", true);
			boolean confLaVoz = sharedPref.getBoolean("pref_key_reprod_lavoz", true);
			boolean confDiario26 = sharedPref.getBoolean("pref_key_reprod_diario26", true);
			boolean confLaRazon = sharedPref.getBoolean("pref_key_reprod_larazon", true);
			boolean confLaCapital = sharedPref.getBoolean("pref_key_reprod_lacapital", true);
			boolean confFoxSports = sharedPref.getBoolean("pref_key_reprod_foxsports", true);
			boolean confElGrafico = sharedPref.getBoolean("pref_key_reprod_elgrafico", true);
			boolean confOle = sharedPref.getBoolean("pref_key_reprod_ole", true);
			if (nombreDiarioSel == null){  //Todas las noticias (ver que diarios selec en configuraciones)
				if (confAmbito){
					xmlInterf = new XMLParserAmbito(cantNoticiasConfigurada);
			    	url = new URL(Constantes.URL_AMBITO);
			    	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setReadTimeout(Constantes.READ_TIMEOUT);
				    conn.setConnectTimeout(Constantes.CONNECT_TIMEOUT);
				    conn.setRequestMethod("GET");
				    conn.setDoInput(true);
				    conn.connect();
				    arrayResp.addAll(xmlInterf.parse(conn.getInputStream()));
				    conn.disconnect();
				}
				if (confBAE){
					xmlInterf = new XMLParserBAE(cantNoticiasConfigurada);
			    	url = new URL(Constantes.URL_BAE);
			    	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setReadTimeout(Constantes.READ_TIMEOUT);
				    conn.setConnectTimeout(Constantes.CONNECT_TIMEOUT);
				    conn.setRequestMethod("GET");
				    conn.setDoInput(true);
				    conn.connect();
				    arrayResp.addAll(xmlInterf.parse(conn.getInputStream()));
				    conn.disconnect();
				}
				if (confClarin){
					xmlInterf = new XMLParserClarin(cantNoticiasConfigurada);
			    	url = new URL(Constantes.URL_CLARIN);
			    	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setReadTimeout(Constantes.READ_TIMEOUT);
				    conn.setConnectTimeout(Constantes.CONNECT_TIMEOUT);
				    conn.setRequestMethod("GET");
				    conn.setDoInput(true);
				    conn.connect();
				    arrayResp.addAll(xmlInterf.parse(conn.getInputStream()));
				    conn.disconnect();
				}
				if (confDiario26){
					xmlInterf = new XMLParserDiario26(cantNoticiasConfigurada);
			    	url = new URL(Constantes.URL_DIARIO26);
			    	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setReadTimeout(Constantes.READ_TIMEOUT);
				    conn.setConnectTimeout(Constantes.CONNECT_TIMEOUT);
				    conn.setRequestMethod("GET");
				    conn.setDoInput(true);
				    conn.connect();
				    arrayResp.addAll(xmlInterf.parse(conn.getInputStream()));
				    conn.disconnect();
				}
				if (confElCronista){
					xmlInterf = new XMLParserElCronista(cantNoticiasConfigurada);
			    	url = new URL(Constantes.URL_ELCRONISTA);
			    	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setReadTimeout(Constantes.READ_TIMEOUT);
				    conn.setConnectTimeout(Constantes.CONNECT_TIMEOUT);
				    conn.setRequestMethod("GET");
				    conn.setDoInput(true);
				    conn.connect();
				    arrayResp.addAll(xmlInterf.parse(conn.getInputStream()));
				    conn.disconnect();
				}
				if (confElGrafico){
					xmlInterf = new XMLParserElGrafico(cantNoticiasConfigurada);
			    	url = new URL(Constantes.URL_ELGRAFICO);
			    	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setReadTimeout(Constantes.READ_TIMEOUT);
				    conn.setConnectTimeout(Constantes.CONNECT_TIMEOUT);
				    conn.setRequestMethod("GET");
				    conn.setDoInput(true);
				    conn.connect();
				    arrayResp.addAll(xmlInterf.parse(conn.getInputStream()));
				    conn.disconnect();
				}
				if (confElLitoral){
					xmlInterf = new XMLParserElLitoral(cantNoticiasConfigurada);
			    	url = new URL(Constantes.URL_ELLITORAL);
			    	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setReadTimeout(Constantes.READ_TIMEOUT);
				    conn.setConnectTimeout(Constantes.CONNECT_TIMEOUT);
				    conn.setRequestMethod("GET");
				    conn.setDoInput(true);
				    conn.connect();
				    arrayResp.addAll(xmlInterf.parse(conn.getInputStream()));
				    conn.disconnect();
				}
				if (confFoxSports){
					xmlInterf = new XMLParserFoxSports(cantNoticiasConfigurada);
			    	url = new URL(Constantes.URL_FOXSPORTS);
			    	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setReadTimeout(Constantes.READ_TIMEOUT);
				    conn.setConnectTimeout(Constantes.CONNECT_TIMEOUT);
				    conn.setRequestMethod("GET");
				    conn.setDoInput(true);
				    conn.connect();
				    arrayResp.addAll(xmlInterf.parse(conn.getInputStream()));
				    conn.disconnect();
				}
				if (confInfobae){
					xmlInterf = new XMLParserInfobae(cantNoticiasConfigurada);
			    	url = new URL(Constantes.URL_INFOBAE);
			    	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setReadTimeout(Constantes.READ_TIMEOUT);
				    conn.setConnectTimeout(Constantes.CONNECT_TIMEOUT);
				    conn.setRequestMethod("GET");
				    conn.setDoInput(true);
				    conn.connect();
				    arrayResp.addAll(xmlInterf.parse(conn.getInputStream()));
				    conn.disconnect();
				}
				if (confInfoNews){
					xmlInterf = new XMLParserInfoNews(cantNoticiasConfigurada);
			    	url = new URL(Constantes.URL_INFONEWS);
			    	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setReadTimeout(Constantes.READ_TIMEOUT);
				    conn.setConnectTimeout(Constantes.CONNECT_TIMEOUT);
				    conn.setRequestMethod("GET");
				    conn.setDoInput(true);
				    conn.connect();
				    arrayResp.addAll(xmlInterf.parse(conn.getInputStream()));
				    conn.disconnect();
				}
				if (confLaCapital){
					xmlInterf = new XMLParserLaCapital(cantNoticiasConfigurada);
			    	url = new URL(Constantes.URL_LACAPITAL);
			    	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setReadTimeout(Constantes.READ_TIMEOUT);
				    conn.setConnectTimeout(Constantes.CONNECT_TIMEOUT);
				    conn.setRequestMethod("GET");
				    conn.setDoInput(true);
				    conn.connect();
				    arrayResp.addAll(xmlInterf.parse(conn.getInputStream()));
				    conn.disconnect();
				}
				if (confIzquierda){
					xmlInterf = new XMLParserLaIzquierda(cantNoticiasConfigurada);
			    	url = new URL(Constantes.URL_LAIZQUIERDA);
			    	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setReadTimeout(Constantes.READ_TIMEOUT);
				    conn.setConnectTimeout(Constantes.CONNECT_TIMEOUT);
				    conn.setRequestMethod("GET");
				    conn.setDoInput(true);
				    conn.connect();
				    arrayResp.addAll(xmlInterf.parse(conn.getInputStream()));
				    conn.disconnect();
				}
				if (confLaNacion){
					xmlInterf = new XMLParserLaNacion(cantNoticiasConfigurada);
			    	url = new URL(Constantes.URL_LANACION);
			    	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setReadTimeout(Constantes.READ_TIMEOUT);
				    conn.setConnectTimeout(Constantes.CONNECT_TIMEOUT);
				    conn.setRequestMethod("GET");
				    conn.setDoInput(true);
				    conn.connect();
				    arrayResp.addAll(xmlInterf.parse(conn.getInputStream()));
				    conn.disconnect();
				}
				if (confLaPrensa){
					xmlInterf = new XMLParserLaPrensa(cantNoticiasConfigurada);
			    	url = new URL(Constantes.URL_LAPRENSA);
			    	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setReadTimeout(Constantes.READ_TIMEOUT);
				    conn.setConnectTimeout(Constantes.CONNECT_TIMEOUT);
				    conn.setRequestMethod("GET");
				    conn.setDoInput(true);
				    conn.connect();
				    arrayResp.addAll(xmlInterf.parse(conn.getInputStream()));
				    conn.disconnect();
				}
				if (confLaRazon){
					xmlInterf = new XMLParserLaRazon(cantNoticiasConfigurada);
			    	url = new URL(Constantes.URL_LARAZON);
			    	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setReadTimeout(Constantes.READ_TIMEOUT);
				    conn.setConnectTimeout(Constantes.CONNECT_TIMEOUT);
				    conn.setRequestMethod("GET");
				    conn.setDoInput(true);
				    conn.connect();
				    arrayResp.addAll(xmlInterf.parse(conn.getInputStream()));
				    conn.disconnect();
				}
				if (confLaVoz){
					xmlInterf = new XMLParserLaVoz(cantNoticiasConfigurada);
			    	url = new URL(Constantes.URL_LAVOZ);
			    	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setReadTimeout(Constantes.READ_TIMEOUT);
				    conn.setConnectTimeout(Constantes.CONNECT_TIMEOUT);
				    conn.setRequestMethod("GET");
				    conn.setDoInput(true);
				    conn.connect();
				    arrayResp.addAll(xmlInterf.parse(conn.getInputStream()));
				    conn.disconnect();
				}
				if (confOle){
					xmlInterf = new XMLParserOle(cantNoticiasConfigurada);
			    	url = new URL(Constantes.URL_OLE);
			    	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setReadTimeout(Constantes.READ_TIMEOUT);
				    conn.setConnectTimeout(Constantes.CONNECT_TIMEOUT);
				    conn.setRequestMethod("GET");
				    conn.setDoInput(true);
				    conn.connect();
				    arrayResp.addAll(xmlInterf.parse(conn.getInputStream()));
				    conn.disconnect();
				}
				if (confPagina12){
					xmlInterf = new XMLParserPagina12(cantNoticiasConfigurada);
			    	url = new URL(Constantes.URL_PAGINA12);
			    	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setReadTimeout(Constantes.READ_TIMEOUT);
				    conn.setConnectTimeout(Constantes.CONNECT_TIMEOUT);
				    conn.setRequestMethod("GET");
				    conn.setDoInput(true);
				    conn.connect();
				    arrayResp.addAll(xmlInterf.parse(conn.getInputStream()));
				    conn.disconnect();
				}
				if (confPerfil){
					xmlInterf = new XMLParserPerfil(cantNoticiasConfigurada);
			    	url = new URL(Constantes.URL_PERFIL);
			    	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setReadTimeout(Constantes.READ_TIMEOUT);
				    conn.setConnectTimeout(Constantes.CONNECT_TIMEOUT);
				    conn.setRequestMethod("GET");
				    conn.setDoInput(true);
				    conn.connect();
				    arrayResp.addAll(xmlInterf.parse(conn.getInputStream()));
				    conn.disconnect();
				}
				if (confTelam){
					xmlInterf = new XMLParserTelam(cantNoticiasConfigurada);
			    	url = new URL(Constantes.URL_TELAM);
			    	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setReadTimeout(Constantes.READ_TIMEOUT);
				    conn.setConnectTimeout(Constantes.CONNECT_TIMEOUT);
				    conn.setRequestMethod("GET");
				    conn.setDoInput(true);
				    conn.connect();
				    arrayResp.addAll(xmlInterf.parse(conn.getInputStream()));
				    conn.disconnect();
				}
			}
			else{
				if (nombreDiarioSel.toLowerCase().contains(Constantes.PAGINA12.toLowerCase())){
			    	xmlInterf = new XMLParserPagina12(cantNoticiasConfigurada);
			    	url = new URL(Constantes.URL_PAGINA12);
			    }
			    else if (nombreDiarioSel.toLowerCase().contains(Constantes.CLARIN.toLowerCase())){
			    	xmlInterf = new XMLParserClarin(cantNoticiasConfigurada);
			    	url = new URL(Constantes.URL_CLARIN);
			    }
			    else if (nombreDiarioSel.toLowerCase().contains(Constantes.LACAPITAL.toLowerCase())){
			    	xmlInterf = new XMLParserLaCapital(cantNoticiasConfigurada);
			    	url = new URL(Constantes.URL_LACAPITAL);
			    }
			    else if (nombreDiarioSel.toLowerCase().contains(Constantes.DIARIO26.toLowerCase())){
			    	xmlInterf = new XMLParserDiario26(cantNoticiasConfigurada);
			    	url = new URL(Constantes.URL_DIARIO26);
			    }
			    else if (nombreDiarioSel.toLowerCase().contains(Constantes.LARAZON.toLowerCase())){
			    	xmlInterf = new XMLParserLaRazon(cantNoticiasConfigurada);
			    	url = new URL(Constantes.URL_LARAZON);
			    }
			    else if (nombreDiarioSel.toLowerCase().contains(Constantes.BAE.toLowerCase())){
			    	xmlInterf = new XMLParserBAE(cantNoticiasConfigurada);
			    	url = new URL(Constantes.URL_BAE);
			    }
			    else if (nombreDiarioSel.toLowerCase().contains(Constantes.ELCRONISTA.toLowerCase())){
			    	xmlInterf = new XMLParserElCronista(cantNoticiasConfigurada);
			    	url = new URL(Constantes.URL_ELCRONISTA);
			    }
			    else if (nombreDiarioSel.toLowerCase().contains(Constantes.ELLITORAL.toLowerCase())){
			    	xmlInterf = new XMLParserElLitoral(cantNoticiasConfigurada);
			    	url = new URL(Constantes.URL_ELLITORAL);
			    }
			    else if (nombreDiarioSel.toLowerCase().contains(Constantes.LANACION.toLowerCase())){
			    	xmlInterf = new XMLParserLaNacion(cantNoticiasConfigurada);
			    	url = new URL(Constantes.URL_LANACION);
			    }
			    else if (nombreDiarioSel.toLowerCase().contains(Constantes.LAPRENSA.toLowerCase())){
			    	xmlInterf = new XMLParserLaPrensa(cantNoticiasConfigurada);
			    	url = new URL(Constantes.URL_LAPRENSA);
			    }
			    else if (nombreDiarioSel.toLowerCase().contains(Constantes.TELAM.toLowerCase())){
			    	xmlInterf = new XMLParserTelam(cantNoticiasConfigurada);
			    	url = new URL(Constantes.URL_TELAM);
			    }
			    else if (nombreDiarioSel.toLowerCase().contains(Constantes.INFOBAE.toLowerCase())){
			    	xmlInterf = new XMLParserInfobae(cantNoticiasConfigurada);
			    	url = new URL(Constantes.URL_INFOBAE);
			    }
			    else if (nombreDiarioSel.toLowerCase().contains(Constantes.INFONEWS.toLowerCase())){
			    	xmlInterf = new XMLParserInfoNews(cantNoticiasConfigurada);
			    	url = new URL(Constantes.URL_INFONEWS);
			    }
			    else if (nombreDiarioSel.toLowerCase().contains(Constantes.AMBITO.toLowerCase())){
			    	xmlInterf = new XMLParserAmbito(cantNoticiasConfigurada);
			    	url = new URL(Constantes.URL_AMBITO);
			    }
			    else if (nombreDiarioSel.toLowerCase().contains(Constantes.PERFIL.toLowerCase())){
			    	xmlInterf = new XMLParserPerfil(cantNoticiasConfigurada);
			    	url = new URL(Constantes.URL_PERFIL);
			    }
			    else if (nombreDiarioSel.toLowerCase().contains(Constantes.LAVOZ.toLowerCase())){
			    	xmlInterf = new XMLParserLaVoz(cantNoticiasConfigurada);
			    	url = new URL(Constantes.URL_LAVOZ);
			    }
			    else if (nombreDiarioSel.toLowerCase().contains(Constantes.FOXSPORTS.toLowerCase())){
			    	xmlInterf = new XMLParserFoxSports(cantNoticiasConfigurada);
			    	url = new URL(Constantes.URL_FOXSPORTS);
			    }
			    else if (nombreDiarioSel.toLowerCase().contains(Constantes.ELGRAFICO.toLowerCase())){
			    	xmlInterf = new XMLParserElGrafico(cantNoticiasConfigurada);
			    	url = new URL(Constantes.URL_ELGRAFICO);
			    }
			    else if (nombreDiarioSel.toLowerCase().contains(Constantes.OLE.toLowerCase())){
			    	xmlInterf = new XMLParserOle(cantNoticiasConfigurada);
			    	url = new URL(Constantes.URL_OLE);
			    }
			    else{
			    	xmlInterf = new XMLParserLaIzquierda(cantNoticiasConfigurada);
			    	url = new URL(Constantes.URL_LAIZQUIERDA);
			    }
				
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setReadTimeout(Constantes.READ_TIMEOUT);
			    conn.setConnectTimeout(Constantes.CONNECT_TIMEOUT);
			    conn.setRequestMethod("GET");
			    conn.setDoInput(true);
				conn.connect();
			    arrayResp = xmlInterf.parse(conn.getInputStream());
			    conn.disconnect();
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
			error = new ErrorVozDiaria("", e.getMessage());
		}
		return arrayResp;
	}
	
	protected void onPostExecute(ArrayList<Noticia> array) {
		if (array.size() > 0)
			_listener.respuestaOk(array);
		else
			_listener.respuestaError(error);
    }
}
