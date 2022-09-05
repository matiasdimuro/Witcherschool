package com.surianodimuro.juego.magos;

import com.surianodimuro.juego.habilidades.Caos;
import com.surianodimuro.juego.habilidades.Eclipse;
import com.surianodimuro.juego.habilidades.Pesadilla;

public final class MagoOscuridad extends MagoElemental {

	public MagoOscuridad() {
		
		super(Elemento.OSCURIDAD, "magos/magoOscuro.png");
		
		super.vida = 1400;
		super.vidaMax = super.vida;
		super.mana = 1100;
		super.manaMax = super.mana;
		super.elemento = Elemento.OSCURIDAD;
		super.debilidad = Elemento.LUZ;
		super.habilidades[1] = new Pesadilla();
		super.habilidades[2] = new Caos();
		super.habilidades[3] = new Eclipse();
	}
}
