package com.memurillo.vozdiaria;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class AcercaDeActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acercade);
		
		String text = "<html><body>"
				  + "<p align=\"center\"><font size=\"5\"><b>Voz Diaria</b></font>"
				  + "<br><font size=\"2\">Versión 1.0</font><br>"
				  + "<p align=\"justify\" style=\"padding-left:20px;padding-right:20px;\"><font size=\"3\">" 
				  + "<b>Aplicación gratuita, sin fines de lucro, anuncios ni publicidad.</b><br><br>"
				  + "<i>Voz Diaria</i> consulta canales de noticias RSS de acceso público con el fin de:<br>"
				  + "&#149; Ofrecer información actualizada de distintas fuentes.<br>"
				  + "&#149; Posibilitar la lectura y escucha de las noticias.<br>"
				  + "<br>NINGUNA NOTICIA PERTENECE NI ES ALTERADA POR <i>VOZ DIARIA</i>. LOS CONTENIDOS SON OBTENIDOS DESDE LAS FUENTES RSS.<br>"
				  + "<br>EL ORDEN DE APARICIÓN DE LAS FUENTES ES ALFABÉTICO. NO SE PRIORIZA DE NINGUNA FORMA.<br>"
				  + "<br>LOS LOGOS PERTENECEN A LAS FUENTES Y NO FUERON ALTERADOS. SE EXPONEN CON EL ÚNICO FIN ORIENTATIVO DE LOS USUARIOS.<br>"
				  + "<br><i>Voz Diaria</i> acepta y respeta los Términos y Condiciones de las fuentes RSS:<br>"
				  + "<a href=\"http://adn979.com/rss\">RSS ADN Rafaela</a><br>"
				  + "<a href=\"http://www.ambito.com/rss/\">RSS Ámbito Financiero</a><br>"
				  + "<a href=\"http://www.diariobae.com/\">RSS BAE</a><br>"
				  + "<a href=\"http://www.clarin.com/rss.html\">RSS Clarín</a><br>"
				  + "<a href=\"http://www.diario26.com/index.php?p=rss_terminos\">RSS Diario 26</a><br>"
				  + "<a href=\"http://www.cronista.com/pages/terminosYcondiciones.html\">RSS El Cronista</a><br>"
				  + "<a href=\"http://www.elcolonodeloeste.com.ar\">RSS El Colono del Oeste</a><br>"
				  + "<a href=\"http://www.elecodesunchales.com.ar/rss\">RSS El Eco de Sunchales</a><br>"
				  + "<a href=\"http://www.elgrafico.com.ar/privacidad.php\">RSS El Gráfico</a><br>"
				  + "<a href=\"http://www.ellitoral.com/index.php/servicios/sindicacion\">RSS El Litoral</a><br>"
				  + "<a href=\"http://www.el-periodico.com.ar/rss\">RSS El Periódico de San Francisco</a><br>"
				  + "<a href=\"http://www.foxsportsla.com/terminos-de-uso/\">RSS Fox Sports</a><br>"
				  + "<a href=\"http://www.infonews.com/politica-de-privacidad\">RSS Info News</a><br>"
				  + "<a href=\"http://www.infobae.com/terminos-condiciones\">RSS Infobae</a><br>"
				  + "<a href=\"http://www.lacapital.com.ar/servicios/rss.html\">RSS La Capital</a><br>"
				  + "<a href=\"http://www.laizquierdadiario.com\">RSS La Izquierda Diario</a><br>"
				  + "<a href=\"https://registracion.lanacion.com.ar/condiciones\">RSS La Nación</a><br>"
				  + "<a href=\"http://diariolaopinion.com.ar/rss\">RSS La Opinión Rafaela</a><br>"
				  + "<a href=\"http://www.laprensa.com.ar/RssList.aspx\">RSS La Prensa</a><br>"
				  + "<a href=\"http://www.larazon.com.ar/\">RSS La Razón</a><br>"
				  + "<a href=\"http://www.lavoz.com.ar/terminos-condiciones\">RSS La Voz del Interior</a><br>"
				  + "<a href=\"http://www.pagina12.com.ar/usuarios/politica_privacidad.php\">RSS Página12</a><br>"
				  + "<a href=\"http://www.perfil.com/estaticas/canalesrss.html\">RSS Perfil</a><br>"
				  + "<a href=\"http://r24n.com.ar/rss\">RSS R24N</a><br>"
				  + "<a href=\"http://feeds.feedburner.com/sunchaleshoy-rss\">RSS Sunchales Hoy</a><br>"
				  + "<a href=\"http://www.telam.com.ar/rss\">RSS Télam</a><br>"
				  + "<br><b>Desarrollada por Miguel E. Murillo.</b><br>"
				  + "Sugerencias, contacto o más información:<br>"
				  + "<a href=\"http://www.memurillo.com.ar\">www.memurillo.com.ar</a>"
				  + "</font></p>"
				  + "</body></html>";
	
		WebView acercaDe = (WebView) findViewById(R.id.acercaDe);
		acercaDe.loadData(text, "text/html; charset=utf-8", "utf-8");
	}
}
