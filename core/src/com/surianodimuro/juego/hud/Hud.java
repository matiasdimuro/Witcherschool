package com.surianodimuro.juego.hud;

import com.surianodimuro.juego.magos.MagoElemental;

public abstract class Hud {

	protected ElementoHud vida;
	protected ElementoHud mana;
	protected ElementoHud elixir;
	
	private MagoElemental mago;
	
	public Hud(MagoElemental mago) {
		this.mago = mago;
	}
	
	public void mostrarElementos() {
		
		String valores = mago.vida + " / " + mago.vidaMax;
		vida.getTexto().cambiarTexto(valores);
		vida.getImagen().dibujar();
		vida.getTexto().imprimir();
		
		valores = mago.mana + " / " + mago.getManaMax();
		mana.getTexto().cambiarTexto(valores);
		mana.getImagen().dibujar();
		mana.getTexto().imprimir();
		
		valores = mago.elixir + " / " + mago.getCosteElixirEvol() + ((mago.elixir == mago.getCosteElixirEvol()) ? " (Evolucionado)" : "");
		elixir.getTexto().cambiarTexto(valores);
		elixir.getImagen().dibujar();
		elixir.getTexto().imprimir();
	}
	
	public void liberarMemoria() {
		vida.liberarMemoria();
		elixir.liberarMemoria();
		mana.liberarMemoria();
	}
}
