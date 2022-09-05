package com.surianodimuro.juego;

import com.badlogic.gdx.Game;
import com.surianodimuro.juego.pantallas.Introduccion;
import com.surianodimuro.juego.utiles.Util;

public class Juego extends Game {
	
	@Override
	public void create () {

		Util.juego = this;
		setScreen(new Introduccion(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		super.dispose();
	}
}
