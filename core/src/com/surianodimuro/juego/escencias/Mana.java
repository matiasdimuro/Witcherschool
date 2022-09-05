package com.surianodimuro.juego.escencias;

import com.surianodimuro.juego.efectos.Efecto;

public final class Mana extends Escencia {
	
	public Mana(float x, float y) {
		super(TipoEscencia.MANA, Efecto.OBTENER_MANA, x, y, 50);
	}

}
