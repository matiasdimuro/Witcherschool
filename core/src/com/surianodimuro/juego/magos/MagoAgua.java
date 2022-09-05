package com.surianodimuro.juego.magos;

import com.surianodimuro.juego.habilidades.BolaDeAgua;
import com.surianodimuro.juego.habilidades.LluviaAcida;
import com.surianodimuro.juego.habilidades.Tsunami;

public final class MagoAgua extends MagoElemental {

	public MagoAgua() {
		
		super(Elemento.AGUA, "magos/magoAgua.png");
		
		super.vida = 1150;
		super.vidaMax = super.vida;
		super.mana = 800;
		super.manaMax = super.mana;
		super.elemento = Elemento.AGUA;
		super.debilidad = null;
		super.habilidades[1] = new BolaDeAgua();
		super.habilidades[2] = new LluviaAcida();
		super.habilidades[3] = new Tsunami();
	}
}
