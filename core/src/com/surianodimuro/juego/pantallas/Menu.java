package com.surianodimuro.juego.pantallas;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.surianodimuro.juego.Niveles;
import com.surianodimuro.juego.utiles.Boton;
import com.surianodimuro.juego.utiles.Texto;
import com.surianodimuro.juego.utiles.Util;

public final class Menu extends PantallaWallpaper {

	private Boton ayuda;
	private Boton controles;
	private Boton salir;
	
	private Texto titulo;
	private Texto comoSalir;
	
	private int numOpcion = 1;
	
	private Sound moverFlecha;
	
	public Menu(Game juego) {
		
		super(juego);
		
		Util.musicaMenu.play();
		moverFlecha = Gdx.audio.newSound(Gdx.files.internal("sfx/MenuMoving2.mp3"));
	}

	@Override
	public void show() {
		
		super.show();
		
		float espacio = 100;
		float x = VW / 2 - (Util.ANCHO_BOTON * 3 + espacio * 2) / 2;
		float y = VH / 2 - Util.ALTO_BOTON / 2;
		
		ayuda = new Boton("botones/ayudaDefault.png", "botones/ayudaHover.png", Util.ANCHO_BOTON, Util.ALTO_BOTON);
		ayuda.posicionar(x, y);
		
		controles = new Boton("botones/controlesDefault.png", "botones/controlesHover.png", Util.ANCHO_BOTON, Util.ALTO_BOTON);
		controles.posicionar(x + (Util.ANCHO_BOTON + espacio), y);
		
		salir = new Boton("botones/salirDefault.png", "botones/salirHover.png", Util.ANCHO_BOTON, Util.ALTO_BOTON);
		salir.posicionar(x + (Util.ANCHO_BOTON + espacio) * 2, y);
		
		titulo = new Texto("fuentes/MorrisRomanBlackAlt.otf", "MENU DE OPCIONES", 80, Color.WHITE);
		titulo.determinarPos(VW / 2 - titulo.getAncho() / 2, VH - 100);
		
		comoSalir = new Texto("fuentes/MorrisRomanBlackAlt.otf", "Presione ESC para volver atras", 40, Color.WHITE);
		comoSalir.determinarPos(VW / 2 - comoSalir.getAncho() / 2, 150);
	}

	@Override
	public void render(float delta) {
		
		super.render(delta);
		
		Util.batch.begin();
		
		if (numOpcion == 1) {
			ayuda.hover = true;
			controles.hover = false;
			salir.hover = false;
			
		} else if (numOpcion == 2) {
			ayuda.hover = false;
			controles.hover = true;
			salir.hover = false;
			
		} else {
			ayuda.hover = false;
			controles.hover = false;
			salir.hover = true;
		}
			ayuda.dibujar();
			controles.dibujar();
			salir.dibujar();
			comoSalir.imprimir();
			titulo.imprimir();
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
		salir.liberarMemoria();
		ayuda.liberarMemoria();
		controles.liberarMemoria();
		comoSalir.liberarMemoria();
		titulo.liberarMemoria();
		moverFlecha.dispose();
		Util.musicaMenu.dispose();
	}

	private void procesarEntrada() {
		
		if (Gdx.input.isKeyJustPressed(Keys.LEFT)) {
			numOpcion = (numOpcion == 1) ? 3 : numOpcion - 1;
			Util.repSonido(moverFlecha);
		}

		if (Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
			numOpcion = (numOpcion == 3) ? 1 : numOpcion + 1;
			Util.repSonido(moverFlecha);
		}
		
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			Util.musicaMenu.stop();
			juego.setScreen(Niveles.nivelActual);
		}
		
		if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			
			switch (numOpcion) {
			case 1:
				juego.setScreen(new Ayuda(juego));
				break;
			case 2:
				juego.setScreen(new Controles(juego));
				break;
			case 3:
				Util.cerrarJuego();
				break;
			}
		}
	}
}
