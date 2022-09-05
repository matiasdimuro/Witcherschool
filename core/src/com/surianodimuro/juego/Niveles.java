package com.surianodimuro.juego;

import com.surianodimuro.juego.magos.MagoElemental;
import com.surianodimuro.juego.pantallas.NivelUno;
import com.surianodimuro.juego.pantallas.PantallaNivel;
import com.surianodimuro.juego.utiles.Util;

public final class Niveles {
	
	public static PantallaNivel nivelUno;
	public static PantallaNivel nivelDos;
	public static PantallaNivel nivelFinal;
	
	public static PantallaNivel nivelActual;
	public static MagoElemental mago;
	
	public static void resetearJuego() {
		
		mago = Util.generarMago();
		nivelUno = new NivelUno(Util.juego);
		nivelDos = null;
		nivelFinal = null;
	}
}
