package com.surianodimuro.juego.pantallas;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.surianodimuro.juego.Niveles;
import com.surianodimuro.juego.utiles.Boton;
import com.surianodimuro.juego.utiles.Imagen;
import com.surianodimuro.juego.utiles.Util;

public abstract class Resultado extends PantallaWallpaper {

	private Imagen background;
	protected Boton btnJugar;
	protected Boton btnSalir;
	
	protected Sound sfxResultado;
	private Sound moverFlecha;
	
	private int numOpcion = 1;
	
	public Resultado(Game juego, String rutaBackground, Boton btnJugar, Boton btnSalir, Sound sfxResultado) {
		
		super(juego);
		
		this.juego = juego;
		this.background = new Imagen(rutaBackground, 0, 0, VW, VH);
		this.btnJugar = btnJugar;
		this.btnSalir = btnSalir;
		this.sfxResultado = sfxResultado;
		
		moverFlecha = Gdx.audio.newSound(Gdx.files.internal("sfx/MenuMoving2.mp3"));
	}

	@Override
	public void show() {
		
		super.show();
		
		float ancho = btnJugar.getRectangle().getWidth();
		float x = VW / 2 - (ancho * 2 + 50) / 2;
		btnJugar.posicionar(x, VH / 4);
		btnSalir.posicionar(x + ancho + 50, VH / 4);
		
		Util.repSonido(sfxResultado);
	}

	@Override
	public void render(float delta) {
		
		super.render(delta);
		
		Util.batch.begin();
		procesarFadeIn(background);
		background.dibujar();
		if (numOpcion == 1) {
			btnJugar.hover = true;
			btnSalir.hover = false;
		} else {
			btnJugar.hover = false;
			btnSalir.hover = true;
		}
		btnJugar.dibujar();
		btnSalir.dibujar();
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
		background.liberarMemoria();
		btnJugar.liberarMemoria();
		btnSalir.liberarMemoria();
		sfxResultado.dispose();
		moverFlecha.dispose();
	}
	
	public void procesarEntrada() {
		
		if (Gdx.input.isKeyJustPressed(Keys.LEFT)) {
			numOpcion = 1;
			Util.repSonido(moverFlecha);
		}
		
		if (Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
			numOpcion = 2;
			Util.repSonido(moverFlecha);
		}
		
		if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			if (numOpcion == 1) {
				Niveles.resetearJuego();
				juego.setScreen(new ElegirMago(juego));
			} else {
				Util.cerrarJuego();
			}
		}
	}
}
