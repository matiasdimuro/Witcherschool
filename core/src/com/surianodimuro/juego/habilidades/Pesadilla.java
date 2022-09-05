package com.surianodimuro.juego.habilidades;

import com.surianodimuro.juego.efectos.Efecto;
import com.surianodimuro.juego.utiles.Imagen;
import com.surianodimuro.juego.utiles.Util;

public final class Pesadilla extends Habilidad {

	public Pesadilla() {
		super("Pesadilla", 70, 50, Efecto.PERDER_VIDA);
		carta = new Imagen("habilidades/pesadilla.png", 0, 0, Util.ANCHO_CARTA, Util.ALTO_CARTA);
	}

}
