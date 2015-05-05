package com.memurillo.vozdiaria;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.memurillo.vozdiaria.util.Util;

public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		cargarIngreso();
	}
	
	private void cargarIngreso(){
		if (Util.isOnline(SplashActivity.this)){
			Thread thread = new Thread(){
				@Override
				public void run(){
					try {
						synchronized(this){
							wait(1000);
						}
					}
					catch(InterruptedException ex){                    
					}
					
					iniciarAplicacion();
				}
			};
			thread.start();
		}
		else{
			Util.showAlertDialog(SplashActivity.this, 
					getString(R.string.app_name), 
					getString(R.string.mensaje_sin_conexion_internet),
							getString(R.string.aceptar), new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						finish();
					}
			}, android.R.drawable.ic_dialog_alert);
		}
	}
	
	private void iniciarAplicacion(){
		startActivity(new Intent(SplashActivity.this, PrincipalActivity.class));
		finish();
	}
}
