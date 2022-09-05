package com.surianodimuro.juego.hud;

import com.surianodimuro.juego.magos.MagoElemental;
import com.surianodimuro.juego.utiles.Util;

public final class HudBatallas extends Hud {
	
	private MagoElemental mago;
	
	/**
	 * 
	 * @param mago A qué mago pertenece el HUD.
	 * @param enemigo Si el mago es el usuario o el enemigo para mostrar o no su elixir.
	 */

	public HudBatallas(MagoElemental mago, boolean enemigo) {

		super(mago);
		this.mago = mago;
		
		int x = (!enemigo) ? (Util.ANCHO_MAPA_MUNDO / 6 - 20) : (Util.ANCHO_MAPA_MUNDO - (Util.ANCHO_MAPA_MUNDO / 4));
		int y = Util.ALTO_MAPA_MUNDO - 75;

		vida = new VidaHud(Integer.toString(mago.vida), Integer.toString(mago.vidaMax), x, y);
		mana = new ManaHud(Integer.toString(mago.mana), Integer.toString(mago.getManaMax()), x, y + 20);
		if (!enemigo) {
			elixir = new ElixirHud(Integer.toString(mago.elixir), Integer.toString(mago.getCosteElixirEvol()), x, y + 40);
		}
	}
	
	@Override
	public void mostrarElementos() {
		
		String valores = mago.vida + " / " + mago.vidaMax;
		vida.getTexto().cambiarTexto(valores);
		vida.getImagen().dibujar();
		vida.getTexto().imprimir();
		
		valores = mago.mana + " / " + mago.getManaMax();
		mana.getTexto().cambiarTexto(valores);
		mana.getImagen().dibujar();
		mana.getTexto().imprimir();
	}
}
