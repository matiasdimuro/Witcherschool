package com.surianodimuro.juego.utiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Imagen {

	private float x;
	private float y;
	private final float WIDTH;
	private final float HEIGHT;
	public Sprite sprite;

	/**
	 * Crea un Sprite a partir de un recurso gráfico, reuniendo
	 * metodos y atributos propios y adicionales.
	 * 
	 * @param ruta Direccion de la textura dentro del directorio.
	 * @param x Posicion en el eje de coordenadas "x".
	 * @param y Posicion en el eje de coordenadas "y".
	 * @param WIDTH El ancho que adoptará la imagen.
	 * @param HEIGHT El alto que adoptará la imagen.
	 * 
	 * @see Sprite
	 */
	
	public Imagen(String ruta, float x, float y, float WIDTH, float HEIGHT) {
		
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		
		this.x = x;
		this.y = y;
		
		sprite = new Sprite(new Texture(ruta));
		sprite.setSize(WIDTH, HEIGHT);
		sprite.setPosition(x, y);
	}
	
	/* METODOS UTILES */

	public void dibujar() {
		sprite.draw(Util.batch);
	}
	
	public void cambiarAlpha(float a) {
		sprite.setAlpha(a);
	}
	
	public void liberarMemoria() {
		sprite.getTexture().dispose();
	}
	
	/* GETTERS & SETTERS */
	
	public Texture obtenerTextura() {
		return sprite.getTexture();
	}

	public float getWIDTH() {
		return WIDTH;
	}

	public float getHEIGHT() {
		return HEIGHT;
	}
	
	public Vector2 getPosicion() {
		return new Vector2(x, y);
	}

	public void posicionar(float x, float y) {
		this.x = x;
		this.y = y;
		sprite.setPosition(x, y);
	}
}
