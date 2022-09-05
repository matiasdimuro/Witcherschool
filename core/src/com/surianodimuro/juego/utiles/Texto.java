package com.surianodimuro.juego.utiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Vector2;

public final class Texto {

	private BitmapFont fuente;
	private GlyphLayout layout;
	private Vector2 pos;
	private String texto;
	private float ancho;
	private float alto;
	
	/**
	 * Esta clase genera un texto en funcion de una fuente dada por el usuario.
	 * 
	 * @param ruta Direccion de la fuente en el directorio.
	 * @param texto Mensaje a generar.
	 * @param tamanio Dimensiones de la fuente.
	 * @param color Color del texto.
	 */

	public Texto(String ruta, String texto, int tamanio, Color color) {
	
		FreeTypeFontGenerator generador = new FreeTypeFontGenerator(Gdx.files.internal(ruta));
		FreeTypeFontParameter params = new FreeTypeFontParameter();
		
		this.layout = new GlyphLayout();
		this.pos = new Vector2();
		
		params.size = tamanio;
		params.color = color;

		this.texto = texto;
		this.fuente = generador.generateFont(params);
		layout.setText(fuente, texto);

		this.alto = layout.height;
		this.ancho = layout.width;
		
	}
	
	/* GETTERS & SETTERS */
	
	public void cambiarTexto(String texto) {
		this.texto = texto;
		
		layout.setText(fuente, texto);
		this.alto = layout.height;
		this.ancho = layout.width;
	}
	
	public float getAncho() {
		return this.ancho;
	}

	public float getAlto() {
		return this.alto;
	}

	public void determinarPos(float x, float y) {
		this.pos.x = x;
		this.pos.y = y;
	}
	
	/* METODOS UTILES */
	
	public void imprimir() {
		fuente.draw(Util.batch, texto, pos.x, pos.y);
	}
	
	public void liberarMemoria() {
		fuente.dispose();
	}
}
