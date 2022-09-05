package com.surianodimuro.juego.entradas;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;

public final class EntradasAcciones extends InputAdapter {

	private ControladorMago controlador;

	public EntradasAcciones(ControladorMago controlador) {
		this.controlador = controlador;
	}

	@Override
	public boolean keyDown(int keycode) {

		/* MOVIMIENTO */

		if ((keycode == Keys.A) || (keycode == Keys.LEFT)) {
			controlador.izquierda = true;
			controlador.caminando = true;
			controlador.mirandoDerecha = false;
		}

		if ((keycode == Keys.D) || (keycode == Keys.RIGHT)) {
			controlador.derecha = true;
			controlador.caminando = true;
			controlador.mirandoDerecha = true;
		}

		if ((keycode == Keys.W) || (keycode == Keys.UP)) {
			controlador.arriba = true;
			controlador.caminando = true;
		}

		if ((keycode == Keys.S) || (keycode == Keys.DOWN)) {
			controlador.abajo = true;
			controlador.caminando = true;
		}

		/* INTERACTUAR */

		if (keycode == Keys.E) {
			controlador.interactuar = true;
		}

		/* CONSUMIR */

		if ((Gdx.input.isKeyJustPressed(Keys.NUM_1)) || Gdx.input.isKeyJustPressed(Keys.NUMPAD_1)) {
			controlador.consumirItem = true;
			controlador.itemElegido = 0;
		}
		if ((Gdx.input.isKeyJustPressed(Keys.NUM_2)) || (Gdx.input.isKeyJustPressed(Keys.NUMPAD_2))) {
			controlador.consumirItem = true;
			controlador.itemElegido = 1;
		}
		if ((Gdx.input.isKeyJustPressed(Keys.NUM_3)) || (Gdx.input.isKeyJustPressed(Keys.NUMPAD_3))) {
			controlador.consumirItem = true;
			controlador.itemElegido = 2;
		}
		if ((Gdx.input.isKeyJustPressed(Keys.NUM_4)) || (Gdx.input.isKeyJustPressed(Keys.NUMPAD_4))) {
			controlador.consumirItem = true;
			controlador.itemElegido = 3;
		}
		if ((Gdx.input.isKeyJustPressed(Keys.NUM_5)) || (Gdx.input.isKeyJustPressed(Keys.NUMPAD_5))) {
			controlador.consumirItem = true;
			controlador.itemElegido = 4;
		}

		return super.keyDown(keycode);
	}

	@Override
	public boolean keyUp(int keycode) {

		/* MOVIMIENTO */

		if ((keycode == Keys.A) || (keycode == Keys.LEFT)) {
			controlador.izquierda = false;
			controlador.caminando = false;
		}

		if ((keycode == Keys.D) || (keycode == Keys.RIGHT)) {
			controlador.derecha = false;
			controlador.caminando = false;
		}

		if ((keycode == Keys.W) || (keycode == Keys.UP)) {
			controlador.arriba = false;
			controlador.caminando = false;
		}

		if ((keycode == Keys.S) || (keycode == Keys.DOWN)) {
			controlador.abajo = false;
			controlador.caminando = false;
		}

		if ((Gdx.input.isKeyPressed(Keys.A)) || (Gdx.input.isKeyPressed(Keys.LEFT))
				|| (Gdx.input.isKeyPressed(Keys.D)) || (Gdx.input.isKeyPressed(Keys.RIGHT))
				|| (Gdx.input.isKeyPressed(Keys.W)) || (Gdx.input.isKeyPressed(Keys.UP))
				|| (Gdx.input.isKeyPressed(Keys.S)) || (Gdx.input.isKeyPressed(Keys.DOWN))) {
			controlador.caminando = true;
		}

		/* INTERACTUAR */

		if (keycode == Keys.E) {
			controlador.interactuar = false;
		}

		/* CONSUMIR */

		if ((keycode == Keys.NUM_1) || (keycode == Keys.NUMPAD_1) || (keycode == Keys.NUM_2)
				|| (keycode == Keys.NUMPAD_2) || (keycode == Keys.NUM_3) || (keycode == Keys.NUMPAD_3)
				|| (keycode == Keys.NUM_4) || (keycode == Keys.NUMPAD_4) || (keycode == Keys.NUM_5)
				|| (keycode == Keys.NUMPAD_5)) {
			controlador.consumirItem = false;
			controlador.itemElegido = -1;
		}

		return super.keyUp(keycode);
	}
}
