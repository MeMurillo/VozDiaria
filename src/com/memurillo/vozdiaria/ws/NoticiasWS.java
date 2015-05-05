package com.memurillo.vozdiaria.ws;

import java.util.ArrayList;

import android.content.Context;

import com.memurillo.vozdiaria.entidades.ErrorVozDiaria;
import com.memurillo.vozdiaria.entidades.Noticia;
import com.memurillo.vozdiaria.ws.HttpRequest.HttpRequestListener;

public class NoticiasWS implements HttpRequestListener {
	public NoticiasListener _noticiasListener;
	private String nombreDiarioSel;
	
	public interface NoticiasListener {
		public void onResponseOkNoticias(ArrayList<Noticia> arrayResp, String nombreDiarioSel);
		public void onResponseError(ErrorVozDiaria error);
		public void onResponseException(Exception ex);
	}
	
	public void getNoticias(String nombreDiarioSel, NoticiasListener listener, Context context){
		_noticiasListener = listener;
		this.nombreDiarioSel = nombreDiarioSel;
		HttpRequest request = new HttpRequest(this, context, nombreDiarioSel);
		request.execute();
	}

	@Override
	public void respuestaOk(ArrayList<Noticia> arrayResp) {
		try{
			_noticiasListener.onResponseOkNoticias(arrayResp, nombreDiarioSel);
		}
		catch (Exception ex){
			_noticiasListener.onResponseException(ex);
		}
	}

	@Override
	public void respuestaError(ErrorVozDiaria error) {
		_noticiasListener.onResponseError(error);
	}
}
