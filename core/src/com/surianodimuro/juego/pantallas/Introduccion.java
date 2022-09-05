package com.surianodimuro.juego.pantallas;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.surianodimuro.juego.utiles.Imagen;
import com.surianodimuro.juego.utiles.Util;

public final class Introduccion extends PantallaWallpaper {

	private Imagen[] secuencias;
	private String[] rutasSec = { "lore2.png", "lore1.png", "lore4.png", "lore3.png", "lore5.png", "lore6.png"};
	private int numSec = 0;
	
	private Music musica;
	
	public Introduccion(Game juego) {
		super(juego);
		musica = Gdx.audio.newMusic(Gdx.files.internal("sfx/Intro.mp3"));
	}

	@Override
	public void show() {
		
		super.show();
		
		musica.play();
		secuencias = new Imagen[rutasSec.length];
		for (int i = 0; i < rutasSec.length; i++) {
			secuencias[i] = new Imagen("wallpapers/introduccion/" + rutasSec[i], 0, 0, VW, VH);
		}		
	}

	@Override
	public void render(float delta) {

		super.render(delta);
		
		Util.batch.begin();
		if (numSec == 4) {
			efectuarFade(secuencias[numSec], 10.2f);
		} else {
			efectuarFade(secuencias[numSec], 0.003f);	
		}
		secuencias[numSec].dibujar();
		if (fadeOutListo) {
			if (numSec == secuencias.length - 1) {
				juego.setScreen(new ElegirMago(juego));
			} else {
				numSec += 1;			
				resetearFades();
			}
		}
	Util.batch.end();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void hide() {
		super.hide();
		musica.stop();
	}

	@Override
	public void dispose() {
		
		super.dispose();
		for (Imagen wallpaper : secuencias) {
			wallpaper.liberarMemoria();
		}
		musica.dispose();
	}

	
}
