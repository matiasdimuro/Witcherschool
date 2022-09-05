package com.surianodimuro.juego.pociones;

import com.surianodimuro.juego.efectos.Efecto;
import com.surianodimuro.juego.utiles.Imagen;
import com.surianodimuro.juego.utiles.Util;

public abstract class Pocion {

	private TipoPocion tipo;
	private Efecto[] efectos;
	
	private int valorMin;
	private int valorMax;
	
	private Imagen imagen;
	private String rutaImg;
	
	private final float ANCHO;
	private final float ALTO;
	
	/**
	 * Esta clase representa al objeto poción que el mago podrá adquirir y consumir.
	 * @param tipo De que poción se trata.
	 * @param efectos Que efecto traerá al mago luego de consumir.
	 * @param valorMin Valor minimo que podrá tomar el efecto.
	 * @param valorMax Valor maximo que podrá tomar el efecto.
	 */
	
	public Pocion(TipoPocion tipo, Efecto[] efectos, int valorMin, int valorMax) {
		
		this.tipo = tipo;
		this.efectos = efectos;
		this.valorMin = valorMin;
		this.valorMax = valorMax;
		
		ANCHO = Util.ANCHO_ITEM * 2 * Util.ZOOM_CAM;
		ALTO = Util.ANCHO_ITEM * 2 * Util.ZOOM_CAM;
		
		crearImagen(this.tipo);
	}
	
	/* METODOS UTILES */
	
	private void crearImagen(TipoPocion tipo) {

		switch (tipo) {

		case ELIXIR:
			rutaImg = "pociones/pocionElixir.png";
			break;

		case MANA:
			rutaImg = "pociones/pocionMana.png";
			break;

		case MULTIJUGOS:
			rutaImg = "pociones/pocionMultijugos.png";
			break;

		case VIDA:
			rutaImg = "pociones/pocionVida.png";
			break;
		}
		
		imagen = new Imagen(rutaImg, 0, 0, ANCHO, ALTO);
	}
	
	public void liberarMemoria() {
		imagen.liberarMemoria();
	}
	
	/* GETTERS & SETTERS */
	
	public TipoPocion getTipo() {
		return tipo;
	}

	public Imagen getImagen() {
		return imagen;
	}

	public Efecto[] getEfectos() {
		return efectos;
	}

	public int getValorMin() {
		return valorMin;
	}

	public int getValorMax() {
		return valorMax;
	}
}
