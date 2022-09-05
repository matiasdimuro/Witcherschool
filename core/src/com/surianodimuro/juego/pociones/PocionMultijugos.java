package com.surianodimuro.juego.pociones;

import com.surianodimuro.juego.efectos.Efecto;

public final class PocionMultijugos extends Pocion {

	private static Efecto[] efectos = { Efecto.OBTENER_VIDA, Efecto.PERDER_VIDA, Efecto.OBTENER_MANA, Efecto.OBTENER_ELIXIR };
	
	public PocionMultijugos() {
		super(TipoPocion.MULTIJUGOS, efectos, 50, 300);
	}

}
