package com.surianodimuro.juego.escencias;

import com.surianodimuro.juego.efectos.Efecto;
import com.surianodimuro.juego.utiles.Imagen;
import com.surianodimuro.juego.utiles.Util;

public abstract class Escencia {

	private TipoEscencia tipo;
	private Efecto efecto;
	private Imagen imagen;
	private int valor;
	
	private float x, y;
	
	/**
	 * La clase escencia representa los elementos Maná y Elixir que puede obtener el mago.
	 * @param tipo Tipo de la escencia.
	 * @param efecto Efecto sobre el mago al consumirla.
	 * @param x Posicion en el eje de coordenadas X.
	 * @param y Posicion en el eje de coordenadas Y.
	 * @param valor Cuanta escencia adquirirá el mago.
	 */
	
	public Escencia(TipoEscencia tipo, Efecto efecto, float x, float y, int valor) {
		this.tipo = tipo;
		this.efecto = efecto;
		this.x = x;
		this.y = y;
		this.valor = valor;
		crearImagen();
	}
	
	/* METODOS UTILES */
	
	public void dibujar() {
		imagen.dibujar();
	}
	
	public void liberarMemoria() {
		imagen.liberarMemoria();
	}

	private void crearImagen() {
		
		switch (tipo) {
		case MANA:
			imagen = new Imagen("hud/mana.png", x, y, Util.ANCHO_ITEM, Util.ALTO_ITEM);
			break;

		case ELIXIR:
			imagen = new Imagen("hud/elixir.png", x, y, Util.ANCHO_ITEM, Util.ALTO_ITEM);
			break;
		}
	}
	
	
	/* GETTERS & SETTERS */
	
	public Efecto getEfecto() {
		return efecto;
	}
	
	public int getValor() {
		return valor;
	}

	
}
