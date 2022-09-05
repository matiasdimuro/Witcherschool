package com.surianodimuro.juego.habilidades;

import com.surianodimuro.juego.efectos.Efecto;
import com.surianodimuro.juego.utiles.Imagen;
import com.surianodimuro.juego.utiles.Util;

public final class Punietazo extends Habilidad {

	public Punietazo() {
		super("Punietazo", 50, 0, Efecto.PERDER_VIDA);
		carta = new Imagen("habilidades/punetazo.png", 0, 0, Util.ANCHO_CARTA, Util.ALTO_CARTA);
	}
}
