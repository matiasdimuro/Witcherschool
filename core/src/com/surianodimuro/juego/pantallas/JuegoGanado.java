package com.surianodimuro.juego.pantallas;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.surianodimuro.juego.utiles.Boton;
import com.surianodimuro.juego.utiles.Util;

public final class JuegoGanado extends Resultado {

	public JuegoGanado(Game juego) {
		super(juego, "wallpapers/resultado/wallpGanaste.png", new Boton("botones/btnJugarDefault.png", "botones/btnJugarGanaste.png", Util.ANCHO_BOTON, Util.ALTO_BOTON),
				new Boton("botones/btnSalirDefault.png", "botones/btnSalirHoverGanaste.png", Util.ANCHO_BOTON, Util.ALTO_BOTON), Gdx.audio.newSound(Gdx.files.internal("sfx/victoria.mp3")));
	}
}
