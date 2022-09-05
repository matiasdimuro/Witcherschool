package com.surianodimuro.juego.hud;

import com.surianodimuro.juego.magos.MagoElemental;

public final class HudMapa extends Hud {
	
	public HudMapa(MagoElemental mago) {
		
		super(mago);
		
		vida = new VidaHud(Integer.toString(mago.vida), Integer.toString(mago.vidaMax), 20, 5);
		mana = new ManaHud(Integer.toString(mago.mana), Integer.toString(mago.getManaMax()), 120, 5);
		elixir = new ElixirHud(Integer.toString(mago.elixir), Integer.toString(mago.getCosteElixirEvol()), 220, 5);
	}
}
