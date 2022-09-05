package com.surianodimuro.juego.escencias;

import com.surianodimuro.juego.efectos.Efecto;

public final class Elixir extends Escencia {

	public Elixir(float x, float y) {
		super(TipoEscencia.ELIXIR, Efecto.OBTENER_ELIXIR, x, y, 25);
	}

}
