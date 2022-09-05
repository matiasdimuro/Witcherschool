package com.surianodimuro.juego.habilidades;

import com.surianodimuro.juego.efectos.Efecto;
import com.surianodimuro.juego.utiles.Imagen;
import com.surianodimuro.juego.utiles.Util;

public final class ProteccionLunar extends Habilidad {

	public ProteccionLunar() {
		super("Proteccion Lunar", 150, 400, Efecto.OBTENER_VIDA);
		carta = new Imagen("habilidades/proteccionLunar.png", 0, 0, Util.ANCHO_CARTA, Util.ALTO_CARTA);
	}

}
