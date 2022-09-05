package com.surianodimuro.juego.hud;

import com.badlogic.gdx.graphics.Color;
import com.surianodimuro.juego.pociones.Pocion;
import com.surianodimuro.juego.utiles.Imagen;
import com.surianodimuro.juego.utiles.Texto;
import com.surianodimuro.juego.utiles.Util;

public class Inventario {

	public Pocion[] pociones;

	public int cantItems = 0;
	private final int CAPACIDAD = 5;
	
	private Texto numero = new Texto("fuentes/celtg.TTF", "", 10, Color.WHITE);
	private Imagen casillero = new Imagen("hud/casillero.png", 0, 0, Util.ANCHO_ITEM, Util.ALTO_ITEM);
	
	private float posInicialX = (Util.ANCHO_MAPA_MUNDO / 2)
			- ((Util.ANCHO_ITEM * 2) * CAPACIDAD + 20 * (CAPACIDAD - 1)) / 2 * Util.ZOOM_CAM
			+ 3 * Util.ZOOM_CAM;
	private float posY = Util.ALTO_MAPA_TILES * Util.ZOOM_CAM;

	public Inventario() {
		pociones = new Pocion[CAPACIDAD];
	}

	public void agregarItem(Pocion pocion) {
		if (cantItems < CAPACIDAD) {
			pociones[cantItems++] = pocion;
		}
	}

	public void ordenar() {
		int i = 0, j = 0;
		boolean ordenado = false;
		while ((!ordenado) && (i < cantItems)) {
			ordenado = true;
			while (j < CAPACIDAD - 1 - i) {
				if (pociones[j] == null) {
					Pocion pocion = pociones[j + 1];
					pociones[j + 1] = pociones[j];
					pociones[j] = pocion;
					ordenado = false;
				}
				j++;
			};
			i++;
		};
	}

	public void dibujarItems() {
		if (cantItems > 0) {
			for (int i = 0; i < CAPACIDAD; i++) {
				float posX = posInicialX + 6.6f + ((Util.ANCHO_ITEM * 2 + 20) * i * Util.ZOOM_CAM);
				
				if (pociones[i] != null) {
					pociones[i].getImagen().posicionar(posX, posY + 1.5f);
					pociones[i].getImagen().dibujar();
				}
			}
		}
	}

	public void crearCasilleros() {

		for (int j = 0; j < pociones.length; j++) {
			
			float posX = posInicialX + ((Util.ANCHO_ITEM * 2 + 20) * j * Util.ZOOM_CAM) + Util.ALTO_ITEM / 2 - 3;
			
			numero.determinarPos(posX + casillero.getWIDTH() / 2 - numero.getAncho() / 2, Util.ALTO_ITEM + 18);
			numero.cambiarTexto(Integer.toString(j + 1));
			numero.imprimir();
			
			casillero.posicionar(posX, posY);
			casillero.dibujar();
		}
	}

	public void liberarMemoria() {
		for (int i = 0; i < cantItems; i++) {
			pociones[i].liberarMemoria();
		}
	}
}
