package com.surianodimuro.juego.hud;

public final class ManaHud extends ElementoHud {

	/**
	 * Elemento HUD de mana.
	 * @param mana
	 * @param vidaMana
	 * @param x
	 * @param y
	 */
	
	public ManaHud(String valorActual, String valorMax, int x, int y) {
		super("hud/mana.png", valorActual, valorMax, x, y);
	}

}
