package com.surianodimuro.juego.habilidades;

import com.surianodimuro.juego.efectos.Efecto;
import com.surianodimuro.juego.utiles.Imagen;
import com.surianodimuro.juego.utiles.Util;

public final class LluviaAcida extends Habilidad {

	public LluviaAcida() {
		super("Lluvia Acida", 200, 150, Efecto.PERDER_VIDA);
		carta = new Imagen("habilidades/lluviaAcida.png", 0, 0, Util.ANCHO_CARTA, Util.ALTO_CARTA);
	}

}
