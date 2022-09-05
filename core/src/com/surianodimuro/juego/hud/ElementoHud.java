package com.surianodimuro.juego.hud;

import com.badlogic.gdx.graphics.Color;
import com.surianodimuro.juego.utiles.Imagen;
import com.surianodimuro.juego.utiles.Texto;
import com.surianodimuro.juego.utiles.Util;

public abstract class ElementoHud {

	private Imagen imagen;
	private Texto texto;
	
	/**
	 * Esta clase crea los elementos propios del HUD del juego. Representando los atributos del "mago".
	 * @param rutaImg Direccion de la ruta de la imagen dentro del directorio.
	 * @param valorActual Valor actual de un atributo determinado.
	 * @param valorMax Valor tope de un atributo determinado.
	 * @param x Posicion en el eje de coordenadas X.
	 * @param y Posicion en el eje de coordenadas Y.
	 */
	
	public ElementoHud(String rutaImg, String valorActual, String valorMax, int x, int y) {
		
		String valores = valorActual + " / " + valorMax;
		
		float posY = Util.ALTO_MAPA_MUNDO - y;
		
		imagen = new Imagen(rutaImg, 0, 0, Util.ANCHO_ITEM, Util.ALTO_ITEM);
		imagen.posicionar(x - 2, posY - imagen.getHEIGHT() + 3);
		texto = new Texto("fuentes/celtg.TTF", valores, 10, Color.WHITE);
		texto.determinarPos(x + imagen.getWIDTH(), posY);
	}
	
	/* METODOS UTILES */
	
	public void liberarMemoria() {
		imagen.liberarMemoria();
		texto.liberarMemoria();
	}
	
	/* GETTERS & SETTERS */
	
	public Imagen getImagen() {
		return imagen;
	}
	
	public Texto getTexto() {
		return texto;
	}
}
