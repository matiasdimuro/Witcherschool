package com.surianodimuro.juego.pantallas;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.surianodimuro.juego.utiles.Imagen;
import com.surianodimuro.juego.utiles.Util;

public abstract class Pantalla implements Screen {

	public Game juego;
	protected OrthographicCamera cam;
	protected FitViewport viewport;
	
	protected final int VW;
	protected final int VH;
	
	protected float opacidad;
	protected float tiempo;
	protected boolean fadeInListo;
	protected boolean fadeOutListo;

	public Pantalla(Game juego) {
		
		this.juego = juego;
		
		VW = Gdx.graphics.getWidth();
		VH = Gdx.graphics.getHeight();
	}

	@Override
	public void show() {	
		resetearFades();
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void render(float delta) {
		Util.limpiarPantalla(0, 0, 0, 1);
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
		resetearFades();
	}

	@Override
	public void dispose() {
		Util.batch.dispose();
		Util.shapeRender.dispose();
	}
	
	protected void efectuarFade(Imagen imagen) {
		
		procesarFadeIn(imagen);
		tiempo += 0.005f;
		if (tiempo > 1) {
			procesarFadeOut(imagen);			
		}
	}
	
	protected void efectuarFade(Imagen imagen, float segundos) {
		
		procesarFadeIn(imagen);
		tiempo += segundos;
		if (tiempo > 1) {
			procesarFadeOut(imagen);			
		}
	}
	
	protected void procesarFadeIn(Imagen imagen) {
		
		if (!fadeInListo) {
			opacidad += 0.01f;
			if (opacidad > 1) {
				opacidad = 1;
				fadeInListo = true;
			}
		}
		
		imagen.cambiarAlpha(this.opacidad);
	}
	
	protected void procesarFadeOut(Imagen imagen) {
		
		opacidad -= 0.008f;
		if (opacidad < 0) {
			opacidad = 0;
			fadeOutListo = true;
		}
		
		imagen.cambiarAlpha(this.opacidad);
	}

	protected void resetearFades() {
		opacidad = 0; 
		tiempo = 0;
		fadeInListo = false;
		fadeOutListo = false;
	}
}
