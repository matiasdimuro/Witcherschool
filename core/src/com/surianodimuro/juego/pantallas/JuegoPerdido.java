package com.surianodimuro.juego.pantallas;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.surianodimuro.juego.utiles.Boton;
import com.surianodimuro.juego.utiles.Util;

public final class JuegoPerdido extends Resultado {

	public JuegoPerdido(Game juego) {
		super(juego, "wallpapers/resultado/gameover.png", new Boton("botones/btnJugarDefault.png", "botones/btnJugarPerdiste.png", Util.ANCHO_BOTON, Util.ALTO_BOTON),
				new Boton("botones/btnSalirDefault.png", "botones/btnSalirHoverPerdiste.png", Util.ANCHO_BOTON, Util.ALTO_BOTON), Gdx.audio.newSound(Gdx.files.internal("sfx/derrota.mp3")));
	}
}
