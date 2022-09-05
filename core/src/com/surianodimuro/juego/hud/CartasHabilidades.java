package com.surianodimuro.juego.hud;

import com.badlogic.gdx.graphics.Color;
import com.surianodimuro.juego.Niveles;
import com.surianodimuro.juego.habilidades.Habilidad;
import com.surianodimuro.juego.magos.MagoElemental;
import com.surianodimuro.juego.utiles.Texto;
import com.surianodimuro.juego.utiles.Util;

public final class CartasHabilidades {

	private MagoElemental mago;
	private Habilidad[] habilidades;
	private float x, y;
	
	private String[] letras = { "Q", "W", "E", "R" };	
	
	public CartasHabilidades() {
		
		mago = Niveles.mago;
		habilidades = mago.getHabilidades();
		
		x = (Util.ANCHO_MAPA_MUNDO / 2 - (habilidades.length * Util.ANCHO_CARTA + (habilidades.length - 1) * (7 / Util.ZOOM_CAM)) / 2);
		y = Util.ALTO_MAPA_MUNDO / 2 - 10;
			
		for (int i = 0; i < habilidades.length; i++) {
			
			Habilidad hab = habilidades[i];
			hab.getCarta().posicionar(x + i * Util.ANCHO_CARTA / 2 / Util.ZOOM_CAM, y);
			hab.letra = new Texto("fuentes/celtg.TTF", letras[i], 15, Color.WHITE);
			
			float espacio = (i * Util.ANCHO_CARTA + i * (7 / Util.ZOOM_CAM));
			float xTexto = (x + Util.ANCHO_CARTA / 2 - hab.letra.getAncho() / 2 - 3.5f) + espacio;
			float yTexto = y - 3;
			
			hab.letra.determinarPos(xTexto, yTexto);
		}
	}
	
	public void mostrarCartas() {
		for (Habilidad habilidad : habilidades) {
			habilidad.mostrarCarta();
		}
	}
	
	public void liberarMemoria() {
		for (Habilidad habilidad : habilidades) {
			habilidad.liberarMemoria();
		}
	}
}
