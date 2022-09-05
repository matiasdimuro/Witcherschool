package com.surianodimuro.juego.magos;

import com.surianodimuro.juego.habilidades.BolaArdiente;
import com.surianodimuro.juego.habilidades.Erupcion;
import com.surianodimuro.juego.habilidades.LluviaDeMagma;

public final class MagoFuego extends MagoElemental {

	public MagoFuego() {
		
		super(Elemento.FUEGO, "magos/magoFuego.png");
		
		super.vida = 1300;
		super.vidaMax = super.vida;
		super.mana = 900;
		super.manaMax = super.mana;
		super.elemento = Elemento.FUEGO;
		super.debilidad = Elemento.AGUA;
		super.habilidades[1] = new BolaArdiente();
		super.habilidades[2] = new LluviaDeMagma();
		super.habilidades[3] = new Erupcion();
	}
}
