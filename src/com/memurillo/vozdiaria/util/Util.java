package com.memurillo.vozdiaria.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.memurillo.vozdiaria.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.util.Base64;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;

public class Util {
	
	public static String md5(String s){
	    try {
	        // Create MD5 Hash
	        MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
	        digest.update(s.getBytes());
	        byte messageDigest[] = digest.digest();

	        // Create Hex String
	        StringBuffer hexString = new StringBuffer();
	        for (int i = 0; i < messageDigest.length; i++) {
	            String h = Integer.toHexString(0xFF & messageDigest[i]);
	            while (h.length() < 2)
	                h = "0" + h;
	            hexString.append(h);
	        }
	        return hexString.toString();

	    } 
	    catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    }
	    return "";
	}
	
	public static String dobleMD5(String texto){
		return md5(md5(texto));
	}
	
	public static String getFechaddMMAAAAhhmm(){
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("ddMMyyyyHHmm");
		return df.format(c.getTime());
	}
	
	public static AlertDialog showAlertDialog(Context context, String titulo, String mensaje){
		if (Build.VERSION.SDK_INT >= 11) {
			return new AlertDialog.Builder(context, R.style.CustomDialog)
			.setTitle(titulo)
			.setMessage(mensaje)
			.setCancelable(false)
			.show();
		}
		else{
			return new AlertDialog.Builder(context)
			.setTitle(titulo)
			.setMessage(mensaje)
			.setCancelable(false)
			.show();
		}
	}
	
	public static void showAlertDialog(Context context, String titulo, String mensaje, 
			String textoAceptar, DialogInterface.OnClickListener listenerAceptar){
		if (Build.VERSION.SDK_INT >= 11) {
			new AlertDialog.Builder(context, R.style.CustomDialog)
			.setTitle(titulo)
			.setMessage(mensaje)
			.setPositiveButton(textoAceptar, listenerAceptar)
			.show();
		}
		else{
			new AlertDialog.Builder(context)
			.setTitle(titulo)
			.setMessage(mensaje)
			.setPositiveButton(textoAceptar, listenerAceptar)
			.show();
		}
	}
	
	public static void showAlertDialog(Context context, String titulo, String mensaje, 
			String textoAceptar, DialogInterface.OnClickListener listenerAceptar, int drawableIcon){
		if (Build.VERSION.SDK_INT >= 11) {
			new AlertDialog.Builder(context, R.style.CustomDialog)
			.setTitle(titulo)
			.setMessage(mensaje)
			.setPositiveButton(textoAceptar, listenerAceptar)
			.setIcon(drawableIcon)
			.show();
		}
		else{
			new AlertDialog.Builder(context)
			.setTitle(titulo)
			.setMessage(mensaje)
			.setPositiveButton(textoAceptar, listenerAceptar)
			.setIcon(drawableIcon)
			.show();
		}
	}
	
	public static void showAlertDialog(Context context, String titulo, String mensaje, 
			String textoAceptar, DialogInterface.OnClickListener listenerAceptar, 
			String textoCancelar, DialogInterface.OnClickListener listenerCancelar){
		if (Build.VERSION.SDK_INT >= 11) {
			new AlertDialog.Builder(context, R.style.CustomDialog)
			.setTitle(titulo)
			.setMessage(mensaje)
			.setPositiveButton(textoAceptar, listenerAceptar)
			.setNegativeButton(textoCancelar, listenerCancelar)
			.show();
		}
		else{
			new AlertDialog.Builder(context)
			.setTitle(titulo)
			.setMessage(mensaje)
			.setPositiveButton(textoAceptar, listenerAceptar)
			.setNegativeButton(textoCancelar, listenerCancelar)
			.show();
		}
	}

	public static AlertDialog showAlertDialogConOpciones(Context context, 
			String titulo, String textoCancelar, CharSequence opciones[], OnClickListener onClickList){
		if (Build.VERSION.SDK_INT >= 11) {
			return new AlertDialog.Builder(context, R.style.CustomDialog)
			.setTitle(titulo)
			.setItems(opciones, onClickList)
			.setNegativeButton(textoCancelar, null)
			.show();
		}
		else{
			return new AlertDialog.Builder(context)
			.setTitle(titulo)
			.setItems(opciones, onClickList)
			.setNegativeButton(textoCancelar, null)
			.show();
		}
	}
	
	public static Dialog crearCargandoDialog(Context context){
		Dialog dialog;
		if (Build.VERSION.SDK_INT >= 11) 
			dialog = new Dialog(context, R.style.CustomDialog);
		else
			dialog = new Dialog(context);
		   
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
	    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
	    dialog.setContentView(R.layout.cargando);
	    return dialog;
	}
	
	public static boolean isOnline(Context appContext) {
		ConnectivityManager cm = (ConnectivityManager) appContext
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = cm.getActiveNetworkInfo();
		return (info != null && info.isConnected());
	}
	
	public static String imageToBase64(String path){
		Bitmap bm = BitmapFactory.decodeFile(path);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();  
		bm.compress(Bitmap.CompressFormat.JPEG, 25, baos); 
		byte[] byteArrayImage = baos.toByteArray(); 
		return Base64.encodeToString(byteArrayImage, Base64.NO_WRAP);
	}
	
	public static File capturaPantalla(View pantalla){
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	    String nombreArchivo = "SANCSA_" + timeStamp + "_";
		File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		Bitmap bitmap;
		pantalla.setDrawingCacheEnabled(true);
		bitmap = Bitmap.createBitmap(pantalla.getDrawingCache());
		pantalla.setDrawingCacheEnabled(false);

		OutputStream fout = null;
		try {
			File image = File.createTempFile(nombreArchivo, ".jpg", storageDir);
			fout = new FileOutputStream(image);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 30, fout);
			fout.flush();
			fout.close();
			return image;
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Bitmap decodeSampledBitmapFromResource(Resources res, String url, int reqWidth, int reqHeight) {
		try{
		    // First decode with inJustDecodeBounds=true to check dimensions
		    final BitmapFactory.Options options = new BitmapFactory.Options();
		    options.inJustDecodeBounds = true;
		    URL UrlImage = new URL (url);
			HttpURLConnection connection = (HttpURLConnection) UrlImage.openConnection();
		    BitmapFactory.decodeResourceStream(res, new TypedValue(), connection.getInputStream(), 
		    		new Rect(), options);
	
		    // Calculate inSampleSize
		    options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
	
		    connection.disconnect();
		    // Decode bitmap with inSampleSize set
		    options.inJustDecodeBounds = false;
		    HttpURLConnection connection2 = (HttpURLConnection) UrlImage.openConnection(); 
		    return BitmapFactory.decodeStream(connection2.getInputStream(), null, options);
		}
		catch (Exception ex){
			return null;
		}
	}
	
	public static Bitmap decodeSampledBitmapFromResource(String path,int reqWidth, int reqHeight) {
	    // First decode with inJustDecodeBounds=true to check dimensions
	    final BitmapFactory.Options options = new BitmapFactory.Options();
	    options.inJustDecodeBounds = true;
	    BitmapFactory.decodeFile(path, options);

	    // Calculate inSampleSize
	    options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

	    // Decode bitmap with inSampleSize set
	    options.inJustDecodeBounds = false;
	    return BitmapFactory.decodeFile(path, options);
	}
	
	public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
	    // Raw height and width of image
	    final int height = options.outHeight;
	    final int width = options.outWidth;
	    int inSampleSize = 1;
	
	    if (height > reqHeight || width > reqWidth) {
	
	        final int halfHeight = height / 2;
	        final int halfWidth = width / 2;
	
	        // Calculate the largest inSampleSize value that is a power of 2 and keeps both
	        // height and width larger than the requested height and width.
	        while ((halfHeight / inSampleSize) > reqHeight
	                && (halfWidth / inSampleSize) > reqWidth) {
	            inSampleSize *= 2;
	        }
	    }
	
	    return inSampleSize;
	}
	
	public static String reemplazarCaracteresHtml(String html){
		html = html.replaceAll("<(.*?)\\>"," "); //Removes all items in brackets
	    html = html.replaceAll("<(.*?)\\\n"," "); //Must be undeneath
	    html = html.replaceFirst("(.*?)\\>", " "); //Removes any connected item to the last bracket
	    html = html.replaceAll("&nbsp;"," ");
	    html = html.replaceAll("&amp;","&");
	    html = html.replaceAll("\\n;","");
	    html = html.replaceAll("\\t","");
	    html = html.replaceAll("\\r","");
	    html = html.replaceAll("(\r\n|\n)", "");
	    html = html.replaceAll("&aacute;","á");
	    html = html.replaceAll("&eacute;","é");
	    html = html.replaceAll("&iacute;","í");
	    html = html.replaceAll("&oacute;","ó");
	    html = html.replaceAll("&uacute;","ú");
	    html = html.replaceAll("&ntilde;","ñ");
	    html = html.replaceAll("&uuml;","ü");
	    html = html.replaceAll("&ndash;","-");
	    html = html.replaceAll("&mdash;","-");
	    html = html.replaceAll("&ensp;"," ");
	    html = html.replaceAll("&emsp;"," ");
	    html = html.replaceAll("&thinsp;"," ");
	    html = html.replaceAll("&quot;","\"");
	    html = html.replaceAll("&lsquo;","‘");
	    html = html.replaceAll("&rsquo;","’");
	    html = html.replaceAll("&sbquo;","‚");
	    html = html.replaceAll("&ldquo;","“");
	    html = html.replaceAll("&rdquo;","”");
	    html = html.replaceAll("&bdquo;","„");
	    html = html.replaceAll("&hellip;","...");
	    html = html.replaceAll("&permil;","‰");
	    html = html.replaceAll("&quest;","¿");
	    html = html.replaceAll("&iexcl;","¡");
	    html = html.replaceAll("&ordm;","º");
	    html = html.replaceAll(" El país ","");
	    html = html.replaceAll("&#8230;","..");
	    return html.trim();
	}
}
