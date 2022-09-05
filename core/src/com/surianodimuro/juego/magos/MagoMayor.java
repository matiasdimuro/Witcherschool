package com.surianodimuro.juego.magos;

import com.surianodimuro.juego.habilidades.Concentracion;
import com.surianodimuro.juego.habilidades.Eclipse;
import com.surianodimuro.juego.habilidades.Erupcion;
import com.surianodimuro.juego.habilidades.LluviaAcida;

public final class MagoMayor extends MagoElemental {

	public MagoMayor() {
		
		super(Elemento.MAYOR, "magos/magoMayor.png");
		
		super.vida = 2000;
		super.vidaMax = super.vida;
		super.mana = 1000;
		super.manaMax = super.mana;
		super.elemento = Elemento.MAYOR;
		super.debilidad = null;
		super.habilidades[0] = new LluviaAcida();
		super.habilidades[1] = new Concentracion();
		super.habilidades[2] = new Erupcion();
		super.habilidades[3] = new Eclipse();
	}
}
