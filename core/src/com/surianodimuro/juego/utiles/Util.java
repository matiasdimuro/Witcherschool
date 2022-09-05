package com.surianodimuro.juego.utiles;


import java.util.Random;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.surianodimuro.juego.magos.MagoAgua;
import com.surianodimuro.juego.magos.MagoElemental;
import com.surianodimuro.juego.magos.MagoFuego;
import com.surianodimuro.juego.magos.MagoLuz;
import com.surianodimuro.juego.magos.MagoOscuridad;

public final class Util {
	
	public static Game juego;

	public static SpriteBatch batch = new SpriteBatch();
	public static Random rand = new Random();
	public static ShapeRenderer shapeRender = new ShapeRenderer();
	
	public static final int TAMAÑO_TILES = 16;
	public static final int ANCHO_MAPA_TILES = 35;
	public static final int ALTO_MAPA_TILES = 20;
	
	public static final int ANCHO_MAPA_MUNDO = TAMAÑO_TILES * ANCHO_MAPA_TILES; 
	public static final int ALTO_MAPA_MUNDO = TAMAÑO_TILES * ALTO_MAPA_TILES;

	public static final int ANCHO_ITEM = 16;
	public static final int ALTO_ITEM = 16;
	
	public static final int ANCHO_CARTA = 70;
	public static final int ALTO_CARTA = 80;
	
	public static final int ANCHO_BOTON = 200;
	public static final int ALTO_BOTON = 100;
	
	/**
	 * 1) ANCHO_MAPA = TAMAÑO_TILES * ANCHO_MAPA_TILES = 16 * 35 => VW = 560 / 1400 = 0.4
	 * <p>
	 * 2) ALTO_MAPA = TAMAÑO_TILES * ALTO_MAPA_TILES = 16 * 20 => VH = 540 / 800 = 0.4
	 */
	
	public static final float ZOOM_CAM = 0.4f;
	
	public static final Music musicaMenu = Gdx.audio.newMusic(Gdx.files.internal("sfx/menu.mp3"));
	
	/**
	 * Se encarga de limpiar la pantalla luego de cada renderizado en la misma.
	 * A su vez, pinta la misma con un color dado por el usuario de formato RGBA.
	 * 
	 * @param rojo Valor del color rojo (0 - 255).
	 * @param verde Valor del color verde (0 - 255).
	 * @param azul Valor del color azul (0 - 255).
	 * @param alfa Valor del alfa (0 - 255).
	 */
	
	public static void limpiarPantalla(float rojo, float verde, float azul, float alfa) {
		Gdx.gl.glClearColor(rojo, verde, azul, alfa);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
	
	/**
	 * Esta funcion reproduce un sonido determindado.
	 * @param s Sonido.
	 * @return id del Sonido.
	 */
	public static long repSonido(Sound s) {
        
        return s.play();
    }
	
	public static MagoElemental generarMago() {

		MagoElemental mago = null;
		int num = Util.rand.nextInt(4) + 1;
		
		switch (num) {
		case 1:
			mago = new MagoAgua();
			break;

		case 2:
			mago = new MagoFuego();
			break;

		case 3:
			mago = new MagoLuz();
			break;

		case 4:
			mago = new MagoOscuridad();
			break;
		}
		return mago;
	}
	
	public static void cerrarJuego() {
		Gdx.app.exit();
	}

}
