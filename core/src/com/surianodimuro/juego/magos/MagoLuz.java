package com.surianodimuro.juego.magos;

import com.surianodimuro.juego.habilidades.Concentracion;
import com.surianodimuro.juego.habilidades.HazLuminoso;
import com.surianodimuro.juego.habilidades.ProteccionLunar;

public final class MagoLuz extends MagoElemental {

	public MagoLuz() {
		
		super(Elemento.LUZ, "magos/magoLuz.png");
		
		super.vida = 1000;
		super.vidaMax = super.vida;
		super.mana = 900;
		super.manaMax = super.mana;
		super.elemento = Elemento.LUZ;
		super.debilidad = null;
		super.habilidades[1] = new HazLuminoso();
		super.habilidades[2] = new Concentracion();
		super.habilidades[3] = new ProteccionLunar();
	}
}
