package com.surianodimuro.juego.hud;

public final class VidaHud extends ElementoHud {

	/**
	 * Elemento HUD de vida.
	 * @param vida
	 * @param vidaMaxima
	 * @param x
	 * @param y
	 */
	
	public VidaHud(String valorActual, String valorMax, int x, int y) {
		super("hud/corazon.png", valorActual, valorMax, x, y);
	}
}
