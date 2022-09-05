package com.surianodimuro.juego.entradas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Keys;

public final class EntradasBatalla extends InputAdapter {

	private ControladorBatalla controlador;
	
	public EntradasBatalla(ControladorBatalla controlador) {
		this.controlador = controlador;
	}

	@Override
	public boolean keyDown(int keycode) {

		if ((keycode == Keys.NUM_1) || (keycode == Keys.NUMPAD_1)) {
			controlador.consumirItem = true;
			controlador.itemElegido = 1;
		}
		if ((keycode == Keys.NUM_2) || (keycode == Keys.NUMPAD_2)) {
			controlador.consumirItem = true;
			controlador.itemElegido = 2;
		}
		if ((keycode == Keys.NUM_3) || (keycode == Keys.NUMPAD_3)) {
			controlador.consumirItem = true;
			controlador.itemElegido = 3;
		}
		if ((keycode == Keys.NUM_4) || (keycode == Keys.NUMPAD_4)) {
			controlador.consumirItem = true;
			controlador.itemElegido = 4;
		}
		
		
		if (Gdx.input.isKeyJustPressed(Keys.Q)) {
			controlador.elegirHabilidad = true;
			controlador.habilidadElegida = 0;
		}
		if (Gdx.input.isKeyJustPressed(Keys.W)) {
			controlador.elegirHabilidad = true;
			controlador.habilidadElegida = 1;
		}
		if (Gdx.input.isKeyJustPressed(Keys.E)) {
			controlador.elegirHabilidad = true;
			controlador.habilidadElegida = 2;
		}
		if (Gdx.input.isKeyJustPressed(Keys.R)) {
			controlador.elegirHabilidad = true;
			controlador.habilidadElegida = 3;
		}
		
		return super.keyDown(keycode);
	}

	@Override
	public boolean keyUp(int keycode) {

		if ((keycode == Keys.NUM_1) || (keycode == Keys.NUMPAD_1) ||
			(keycode == Keys.NUM_2) || (keycode == Keys.NUMPAD_2) ||
			(keycode == Keys.NUM_3) || (keycode == Keys.NUMPAD_3) ||
			(keycode == Keys.NUM_4) || (keycode == Keys.NUMPAD_4)) {
			controlador.consumirItem = false;
			controlador.itemElegido = 0;
		}
		
		if ((keycode == Keys.Q) || (keycode == Keys.W) || (keycode == Keys.E) || (keycode == Keys.R)) {
			controlador.elegirHabilidad = false;
			controlador.habilidadElegida = -1;
		}
		
		return super.keyUp(keycode);
	}

	
}
