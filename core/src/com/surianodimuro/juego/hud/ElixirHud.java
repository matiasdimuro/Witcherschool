package com.surianodimuro.juego.hud;

public final class ElixirHud extends ElementoHud {

	/**
	 * Elemento HUD de elixir.
	 * @param elixir
	 * @param elixirMaximo
	 * @param x
	 * @param y
	 */
	
	public ElixirHud(String valorActual, String valorMax, int x, int y) {
		super("hud/elixir.png", valorActual, valorMax, x, y);
	}

}
