package com.surianodimuro.juego.pociones;

import com.surianodimuro.juego.efectos.Efecto;

public final class PocionVida extends Pocion {

	private static Efecto[] efectos = { Efecto.OBTENER_VIDA };
	
	public PocionVida() {
		super(TipoPocion.VIDA, efectos, 100, 200);
	}
}
