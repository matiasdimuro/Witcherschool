package com.surianodimuro.juego.habilidades;

import com.surianodimuro.juego.efectos.Efecto;
import com.surianodimuro.juego.utiles.Imagen;
import com.surianodimuro.juego.utiles.Util;

public final class Concentracion extends Habilidad {

	public Concentracion() {
		super("Concentracion", 300, 0, Efecto.OBTENER_MANA);
		carta = new Imagen("habilidades/concentracion.png", 0, 0, Util.ANCHO_CARTA, Util.ALTO_CARTA);
	}

}
