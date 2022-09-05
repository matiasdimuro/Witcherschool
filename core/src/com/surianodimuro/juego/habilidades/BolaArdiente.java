package com.surianodimuro.juego.habilidades;

import com.surianodimuro.juego.efectos.Efecto;
import com.surianodimuro.juego.utiles.Imagen;
import com.surianodimuro.juego.utiles.Util;

public final class BolaArdiente extends Habilidad {

	public BolaArdiente() {
		super("Bola Ardiente", 100, 75, Efecto.PERDER_VIDA);
		carta = new Imagen("habilidades/bolaArdiente.png", 0, 0, Util.ANCHO_CARTA, Util.ALTO_CARTA);
	}

}
