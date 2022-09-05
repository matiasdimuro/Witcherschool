package com.surianodimuro.juego.habilidades;

import com.surianodimuro.juego.efectos.Efecto;
import com.surianodimuro.juego.utiles.Imagen;
import com.surianodimuro.juego.utiles.Util;

public final class Erupcion extends Habilidad {

	public Erupcion() {
		super("Erupcion", 400, 250, Efecto.PERDER_VIDA);
		carta = new Imagen("habilidades/erupcion.png", 0, 0, Util.ANCHO_CARTA, Util.ALTO_CARTA);
	}

}
