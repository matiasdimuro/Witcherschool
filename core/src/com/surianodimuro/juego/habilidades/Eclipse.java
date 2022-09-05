package com.surianodimuro.juego.habilidades;

import com.surianodimuro.juego.efectos.Efecto;
import com.surianodimuro.juego.utiles.Imagen;
import com.surianodimuro.juego.utiles.Util;

public final class Eclipse extends Habilidad {

	public Eclipse() {
		super("Eclipse", 800, 800, Efecto.PERDER_VIDA);
		carta = new Imagen("habilidades/eclipse.png", 0, 0, Util.ANCHO_CARTA, Util.ALTO_CARTA);
	}

}
