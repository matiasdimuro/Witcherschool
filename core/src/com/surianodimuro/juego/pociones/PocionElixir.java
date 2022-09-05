package com.surianodimuro.juego.pociones;

import com.surianodimuro.juego.efectos.Efecto;

public final class PocionElixir extends Pocion {

	private static Efecto[] efectos = { Efecto.OBTENER_ELIXIR };
	
	public PocionElixir() {
		super(TipoPocion.ELIXIR, efectos, 30, 50);
	}

}
