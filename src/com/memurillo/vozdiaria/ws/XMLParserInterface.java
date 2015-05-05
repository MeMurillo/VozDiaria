package com.memurillo.vozdiaria.ws;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParserException;

import com.memurillo.vozdiaria.entidades.Noticia;

public interface XMLParserInterface {
	public ArrayList<Noticia> parse(InputStream in) throws XmlPullParserException, IOException ;
}
