package com.surianodimuro.juego.pantallas;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.surianodimuro.juego.utiles.Imagen;
import com.surianodimuro.juego.utiles.Texto;
import com.surianodimuro.juego.utiles.Util;

public final class Controles extends PantallaWallpaper {

	private Texto comoSalir;
	private Imagen texto;

	public Controles(Game juego) {
		super(juego);
	}

	@Override
	public void show() {

		super.show();
		comoSalir = new Texto("fuentes/MorrisRomanBlackAlt.otf", "Presione ESC para volver atras", 40, Color.WHITE);
		comoSalir.determinarPos(VW / 2 - comoSalir.getAncho() / 2, 150);
		texto = new Imagen("menu/controlesTexto.png", 0, 0, VW, VH);
	}

	@Override
	public void render(float delta) {
		
		super.render(delta);
		
		Util.batch.begin();
			texto.dibujar();
			comoSalir.imprimir();
		Util.batch.end();
		
		procesarEntrada();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void hide() {
		super.hide();
	}

	@Override
	public void dispose() {
		
		super.dispose();
		comoSalir.liberarMemoria();
		texto.dibujar();
	}
	
	private void procesarEntrada() {
		
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			juego.setScreen(new Menu(juego));
		}
	}
}
