package com.surianodimuro.juego.habilidades;

import com.surianodimuro.juego.efectos.Efecto;
import com.surianodimuro.juego.utiles.Imagen;
import com.surianodimuro.juego.utiles.Util;

public final class Tsunami extends Habilidad {

	public Tsunami() {
		super("Tsunami", 300, 150, Efecto.PERDER_VIDA);
		carta = new Imagen("habilidades/tsunami.png", 0, 0, Util.ANCHO_CARTA, Util.ALTO_CARTA);
	}

}
