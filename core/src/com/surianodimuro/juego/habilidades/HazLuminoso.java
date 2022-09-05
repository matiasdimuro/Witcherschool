package com.surianodimuro.juego.habilidades;

import com.surianodimuro.juego.efectos.Efecto;
import com.surianodimuro.juego.utiles.Imagen;
import com.surianodimuro.juego.utiles.Util;

public final class HazLuminoso extends Habilidad {

	public HazLuminoso() {
		super("Haz Luminoso", 500, 350, Efecto.PERDER_VIDA);
		carta = new Imagen("habilidades/hazLuminoso.png", 0, 0, Util.ANCHO_CARTA, Util.ALTO_CARTA);
	}

}
