package com.surianodimuro.juego.pantallas;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.surianodimuro.juego.utiles.Util;

public abstract class PantallaWallpaper extends Pantalla {
	
	public PantallaWallpaper(Game juego) {
		super(juego);
	}

	@Override
	public void show() {
		
		super.show();
		
		cam = new OrthographicCamera(VW, VH);
		cam.setToOrtho(false, VW, VH);
		viewport = new FitViewport(VW, VH, cam);
		cam.update();
	}

	@Override
	public void render(float delta) {
		
		super.render(delta);
		Util.batch.setProjectionMatrix(cam.combined);
		cam.update();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		cam.viewportWidth = width;
		cam.viewportHeight = height;
		viewport.update(width, height);
		cam.update();
	}

	@Override
	public void hide() {
		super.hide();
	}

	@Override
	public void dispose() {
		super.dispose();
	}

}
