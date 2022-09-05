package com.surianodimuro.juego.pociones;

import com.surianodimuro.juego.efectos.Efecto;

public final class PocionMana extends Pocion {

	private static Efecto[] efectos = { Efecto.OBTENER_MANA };
	
	public PocionMana() {
		super(TipoPocion.MANA, efectos, 150, 250);
	}

}
