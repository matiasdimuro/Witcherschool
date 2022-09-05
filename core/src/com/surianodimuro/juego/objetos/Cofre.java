package com.surianodimuro.juego.objetos;

import com.surianodimuro.juego.pociones.Pocion;
import com.surianodimuro.juego.pociones.PocionElixir;
import com.surianodimuro.juego.pociones.PocionMana;
import com.surianodimuro.juego.pociones.PocionMultijugos;
import com.surianodimuro.juego.pociones.PocionVida;
import com.surianodimuro.juego.utiles.Imagen;
import com.surianodimuro.juego.utiles.Util;

public final class Cofre {

	private Pocion pocionOculta;
	private Imagen imagen;
	public boolean cerrado;
	private float x, y;
	
	public Cofre(float x, float y) {
		cerrado = true;
		this.x = x;
		this.y = y;
		imagen = new Imagen("objetos/cofreCerrado.png", this.x, this.y, Util.ANCHO_ITEM * 1.5f, Util.ALTO_ITEM * 1.5f);
		cargarPocion();
	}
	
	public void dibujar() {
		imagen.dibujar();
	}

	public void abrir() {
		cerrado = false;
		imagen = new Imagen("objetos/cofreAbierto.png", this.x, this.y, Util.ANCHO_ITEM * 1.5f, Util.ALTO_ITEM * 1.5f);
	}
	
	private void cargarPocion() {
		
		int num = Util.rand.nextInt(5) + 1;
		switch (num) {
		case 1:
			pocionOculta = null;
			break;

		case 2:
			pocionOculta = new PocionVida();
			break;
			
		case 3:
			pocionOculta = new PocionElixir();
			break;
			
		case 4:
			pocionOculta = new PocionMana();
			break;
			
		case 5:
			pocionOculta = new PocionMultijugos();
			break;
		}
	}
	
	public void liberarMemoria() {
		imagen.liberarMemoria();
		if (pocionOculta != null) {
			pocionOculta.liberarMemoria();
		}
	}
	
	public Pocion getPocionOculta() {
		return pocionOculta;
	}
}
