package com.surianodimuro.juego.pantallas;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.surianodimuro.juego.Niveles;
import com.surianodimuro.juego.magos.Elemento;
import com.surianodimuro.juego.magos.MagoAgua;
import com.surianodimuro.juego.magos.MagoFuego;
import com.surianodimuro.juego.magos.MagoLuz;
import com.surianodimuro.juego.magos.MagoOscuridad;
import com.surianodimuro.juego.utiles.Imagen;
import com.surianodimuro.juego.utiles.Texto;
import com.surianodimuro.juego.utiles.Util;

public final class ElegirMago extends PantallaWallpaper {

	private Imagen fondo;
	private int numOpcion;
	private boolean seleccionado;

	private Texto txtSeleccionar;
	private Imagen flecha;
	private Sprite[] magos;
	private Texto[] elementos;
	
	private Sound moverFlecha;
    private Sound seleccionarMago;

	public ElegirMago(Game juego) {
		
		super(juego);
		numOpcion = 1;
		opacidad = 1;
		seleccionado = false;
		fondo = new Imagen("wallpapers/fondoSeleccion.png", 0, 0, VW, VH);

		txtSeleccionar = new Texto("fuentes/MorrisRomanBlackAlt.otf", "SELECCIONE UN MAGO", 50, Color.WHITE);
		txtSeleccionar.determinarPos(VW / 2 - txtSeleccionar.getAncho() / 2, VH - VH / 5);

		magos = new Sprite[Elemento.values().length - 1];
		elementos = new Texto[magos.length];
		cargarSpritesMagos();
		cargarElementosMagos();

		flecha = new Imagen("objetos/flecha.png", magos[0].getX() - 10, magos[0].getY() - magos[0].getHeight() * 2.5f - elementos[0].getAlto() - 5,
				64, 64);
		
		moverFlecha = Gdx.audio.newSound(Gdx.files.internal("sfx/MenuMoving2.mp3"));
        seleccionarMago = Gdx.audio.newSound(Gdx.files.internal("sfx/InGameMultiplayerSound2.mp3"));
	}

	@Override
	public void show() {
		super.show();
	}

	@Override
	public void render(float delta) {

		super.render(delta);

		Util.batch.begin();

		procesarFadeIn(fondo);
		fondo.dibujar();
		if (seleccionado) {
			procesarFadeOut(fondo);
			if (fadeOutListo) {
				asignarMago(numOpcion);
				Niveles.nivelUno = new NivelUno(juego);
				juego.setScreen(Niveles.nivelUno);
			}
		}

		for (Sprite sprite : magos) {
			sprite.draw(Util.batch);
		}
		for (Texto elemento : elementos) {
			elemento.imprimir();
		}
		txtSeleccionar.imprimir();
		flecha.dibujar();

		Util.batch.end();

		if ((!seleccionado) && (fadeInListo)) {
			procesarEntrada();
		}
		moverFlecha();
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
		fondo.liberarMemoria();
		txtSeleccionar.liberarMemoria();
		flecha.liberarMemoria();
		for (TextureRegion mago : magos) {
			mago.getTexture().dispose();
		}
		moverFlecha.dispose();
		seleccionarMago.dispose();
	}

	private void procesarEntrada() {

        if (Gdx.input.isKeyJustPressed(Keys.LEFT)) {
            numOpcion = (numOpcion == 1) ? Elemento.values().length - 1 : numOpcion - 1;
            Util.repSonido(moverFlecha);
        }

        if (Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
            numOpcion = (numOpcion == 4) ? 1 : numOpcion + 1;
            Util.repSonido(moverFlecha);
        }

        if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
            seleccionado = true;
            Util.repSonido(seleccionarMago);
        }
    }

	private void moverFlecha() {
		
		float x = VW / 2 - ((magos[0].getWidth() * 2.f * magos.length) + ((magos.length - 1) * 100)) / 2;
		x += (numOpcion - 1) * (magos[0].getWidth() * 2.f + 100);		
		float y = flecha.getPosicion().y;
		flecha.posicionar(x, y);
	}

	private void cargarSpritesMagos() {

		String[] archivos = { "magoFuego.png", "magoAgua.png", "magoLuz.png", "magoOscuro.png" };

		for (int i = 0; i < magos.length; i++) {

			TextureRegion textura = new TextureRegion(new Texture("magos/" + archivos[i]), 0, 0, 38, 48);
			magos[i] = new Sprite(textura);

			float x = VW / 2 - ((magos[i].getWidth() * 2.f * magos.length) + ((magos.length - 1) * 100)) / 2;
			float y = VH / 2;

			magos[i].setScale(2.5f);
			magos[i].setPosition(x + i * (magos[i].getWidth() * 2.f + 100), y);
		}
	}
	
	private void cargarElementosMagos() {
		
		for (int i = 0; i < elementos.length; i++) {
			
			Texto elemento = new Texto("fuentes/MorrisRomanBlackAlt.otf", Elemento.values()[i].toString(), 20, Color.WHITE);
			
			float x = magos[i].getX() + magos[i].getWidth() / 2 - elemento.getAncho() / 2 + 5;
			float y = magos[i].getY() - magos[i].getHeight();
			
			elemento.determinarPos(x, y);
			elementos[i] = elemento;
		}
	}
	
	private void asignarMago(int num) {

		switch (num) {
		case 1:
			Niveles.mago = new MagoFuego();
			break;

		case 2:
			Niveles.mago = new MagoAgua();
			break;

		case 3:
			Niveles.mago = new MagoLuz();
			break;

		case 4:
			Niveles.mago = new MagoOscuridad();
			break;
		}
	}
}
