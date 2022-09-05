package com.surianodimuro.juego.habilidades;

import com.surianodimuro.juego.efectos.Efecto;
import com.surianodimuro.juego.utiles.Imagen;
import com.surianodimuro.juego.utiles.Util;

public final class BolaDeAgua extends Habilidad {

	public BolaDeAgua() {
		super("Bola de Agua", 80, 50, Efecto.PERDER_VIDA);
		carta = new Imagen("habilidades/bolaAgua.png", 0, 0, Util.ANCHO_CARTA, Util.ALTO_CARTA);
	}

}
