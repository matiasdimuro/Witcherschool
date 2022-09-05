package com.surianodimuro.juego.utiles;

import com.badlogic.gdx.math.Rectangle;

public class Boton {

	private float x, y;
	public boolean hover = false;
	private Imagen[] skins = new Imagen[2];

	/**
	 * Esta clase permite costruir un boton con dos estados.
	 * @param rutaDefault Ruta del estado estático del botón.
	 * @param rutaHover Ruta del estado cuando se posiciona el mouse por encima.
	 * @param WIDTH
	 * @param HEIGHT
	 */
	public Boton(String rutaDefault, String rutaHover, float WIDTH, float HEIGHT) {
		
		skins[0] = new Imagen(rutaDefault, 0, 0, WIDTH, HEIGHT);
		skins[1] = new Imagen(rutaHover, 0, 0, WIDTH, HEIGHT);
	}
	
	public void dibujar() {
		if (!hover) { skins[0].dibujar(); } 
		else { skins[1].dibujar(); }
	}
	
	public void posicionar(float x, float y) {
		this.x = x;
		this.y = y;
		skins[0].posicionar(x, y);;
		skins[1].posicionar(x, y);
	}
	
	public Rectangle getRectangle() {
		return new Rectangle(x, y, skins[0].getWIDTH(), skins[0].getHEIGHT());
	}
	
	public void liberarMemoria() {
		for (Imagen boton : skins) { boton.liberarMemoria(); }
	}
}
