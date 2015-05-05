package com.memurillo.vozdiaria.ws;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

import com.memurillo.vozdiaria.entidades.Noticia;
import com.memurillo.vozdiaria.util.Constantes;
import com.memurillo.vozdiaria.util.Util;

public class XMLParserLaRazon implements XMLParserInterface {
	// We don't use namespaces
    private static final String ns = null;
    private int cantNoticiasConf;
    
    public XMLParserLaRazon(String cantidadNoticiasConfiguradas){
    	cantNoticiasConf = Integer.parseInt(cantidadNoticiasConfiguradas);
    }
   
    public ArrayList<Noticia> parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readFeed(parser);
        } 
        catch (Exception ex){
        	System.out.println(ex.getMessage());
        }
        finally {
            in.close();
        }
        return new ArrayList<Noticia>();
    }
 
    private ArrayList<Noticia> readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
    	ArrayList<Noticia> noticias = new ArrayList<Noticia>();

        parser.require(XmlPullParser.START_TAG, ns, "rss");
        int cantidadNoticiasProcesadas = 0;
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            parser.require(XmlPullParser.START_TAG, ns, "channel");
            while (parser.next() != XmlPullParser.END_TAG) {
                if (parser.getEventType() != XmlPullParser.START_TAG) {
                    continue;
                }
                String name = parser.getName();
                if (name.equals("item")) {
                	noticias.add(readEntry(parser));
                	cantidadNoticiasProcesadas++;
                	if (cantidadNoticiasProcesadas >= cantNoticiasConf)
                		return noticias;
                } 
                else {
                    skip(parser);
                }
            }
        }  
        return noticias;
    }

    private Noticia readEntry(XmlPullParser parser) throws XmlPullParserException, IOException {
    	parser.require(XmlPullParser.START_TAG, ns, "item");
    	String title = null;
    	String descripcion = null;
    	String link = null;
    	while (parser.next() != XmlPullParser.END_TAG) {
    		if (parser.getEventType() != XmlPullParser.START_TAG) {
    			continue;
    		}
    		String name = parser.getName();
    		if (name.equals("title")) {
    			title = leerTitulo(parser);
    		} 
    		else if (name.equals("description")) {
    			descripcion = leerDescripcion(parser);
    			descripcion = Util.reemplazarCaracteresHtml(descripcion);
    		} 
    		else if (name.equals("link")) {
    			link = leerLink(parser);
    		}
    		else {
    			skip(parser);
    		}
    	}
    	return new Noticia(title, link, descripcion, Constantes.LARAZON);
    }
    
    private String leerTitulo(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "title");
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "title");
        return title;
    }
    
    private String leerLink(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "link");
        String link = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "link");
        return link;
    }

    private String leerDescripcion(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "description");
        String descrip = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "description");
        return descrip;
    }

    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }
    
    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
            case XmlPullParser.END_TAG:
                depth--;
                break;
            case XmlPullParser.START_TAG:
                depth++;
                break;
            }
        }
     }

}
