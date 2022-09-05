package com.surianodimuro.juego.habilidades;

import com.surianodimuro.juego.efectos.Efecto;
import com.surianodimuro.juego.utiles.Imagen;
import com.surianodimuro.juego.utiles.Util;

public final class LluviaDeMagma extends Habilidad {

	public LluviaDeMagma() {
		super("Lluvia de Magma", 200, 100, Efecto.PERDER_VIDA);
		carta = new Imagen("habilidades/lluviaMagma.png", 0, 0, Util.ANCHO_CARTA, Util.ALTO_CARTA);
	}
}
