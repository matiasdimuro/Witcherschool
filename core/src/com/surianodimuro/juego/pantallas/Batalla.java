package com.surianodimuro.juego.pantallas;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.surianodimuro.juego.Niveles;
import com.surianodimuro.juego.entradas.ControladorBatalla;
import com.surianodimuro.juego.entradas.EntradasBatalla;
import com.surianodimuro.juego.hud.CartasHabilidades;
import com.surianodimuro.juego.hud.Hud;
import com.surianodimuro.juego.hud.HudBatallas;
import com.surianodimuro.juego.magos.MagoElemental;
import com.surianodimuro.juego.utiles.Imagen;
import com.surianodimuro.juego.utiles.Texto;
import com.surianodimuro.juego.utiles.Util;

public final class Batalla extends Pantalla {

	private final Imagen background;
	private final float ANCHO_MAPA;
	private final float ALTO_MAPA;

	private MagoElemental enemigo;
	private Hud hudEnemigo;
	private Hud hudMago;
	private CartasHabilidades cartas;

	private ControladorBatalla controlador;
	private EntradasBatalla entradasBatalla;

	private boolean turnoUsuario;
	private Texto txtTurno;
	private Texto txtHabilidad;
	
	private Sound sfxMuerteEnemigo;
	private Sound sfxTomarPocion;

	public Batalla(Game juego, MagoElemental enemigo) {

		super(juego);

		ANCHO_MAPA = Util.ANCHO_MAPA_MUNDO;
		ALTO_MAPA = Util.ALTO_MAPA_MUNDO;

		this.enemigo = enemigo;
		turnoUsuario = true;
		background = new Imagen("mapas/Batalla.png", 0, 0, ANCHO_MAPA, ALTO_MAPA);
		
		sfxMuerteEnemigo = Gdx.audio.newSound(Gdx.files.internal("sfx/muerteEnemigo.mp3"));
		sfxTomarPocion = Gdx.audio.newSound(Gdx.files.internal("sfx/tomarPocion.mp3"));
	}

	@Override
	public void show() {

		super.show();

		cam = new OrthographicCamera(ANCHO_MAPA, ALTO_MAPA);
		cam.setToOrtho(false, ANCHO_MAPA, ALTO_MAPA);

		viewport = new FitViewport(ANCHO_MAPA, ALTO_MAPA, cam);
		cam.update();

		cartas = new CartasHabilidades();
		hudMago = new HudBatallas(Niveles.mago, false);
		hudEnemigo = new HudBatallas(enemigo, true);

		posicionarMago(false);
		posicionarMago(true);

		this.controlador = new ControladorBatalla();
		entradasBatalla = new EntradasBatalla(this.controlador);
		Gdx.input.setInputProcessor(entradasBatalla);

		txtTurno = new Texto("fuentes/18cents.ttf", "HORA DE LA BATALLA! ELIJE TU HABILIDAD!", 15, Color.WHITE);
		txtHabilidad = new Texto("fuentes/18cents.ttf", "", 15, Color.WHITE);
	}

	@Override
	public void render(float delta) {

		super.render(delta);
		
		Util.batch.setProjectionMatrix(cam.combined);
		cam.update();

		Util.batch.begin();
		background.dibujar();

		if ((Niveles.mago.vida > 0) && (enemigo.vida > 0)) {

			if (!fadeInListo) {
				procesarFadeIn(background);
			}

			else {
				
				hudEnemigo.mostrarElementos();
				hudMago.mostrarElementos();
				cartas.mostrarCartas();

				Niveles.mago.dibujarMago(true);
				Niveles.mago.inventario.crearCasilleros();
				Niveles.mago.inventario.dibujarItems();

				enemigo.dibujarMago(false);

				if (turnoUsuario) {

					txtTurno.cambiarTexto("TU TURNO!");
					procesarEntrada();
					controlador.elegirHabilidad = false;
					
					if (enemigo.vida <= 0) {
						sfxMuerteEnemigo.setVolume(Util.repSonido(sfxMuerteEnemigo), 0.3f);
					}

				} else {

					int numHab = Util.rand.nextInt(enemigo.getHabilidades().length);
					if (enemigo.getHabilidades()[numHab].getConsumoMana() <= enemigo.mana) {

						enemigo.atacar(Niveles.mago, numHab);
						turnoUsuario = true;
						txtHabilidad.cambiarTexto("EL MAGO " + enemigo.getElemento() + " EFECTUO "
								+ enemigo.getHabilidades()[numHab].getNombre().toUpperCase() + " (" + enemigo.getHabilidades()[numHab].getValorBase() + " puntos de efecto)");
					}
				}
				
				txtTurno.determinarPos((ANCHO_MAPA / 2 - txtTurno.getAncho() / 2), ALTO_MAPA - 20);
				txtHabilidad.determinarPos((ANCHO_MAPA / 2 - txtHabilidad.getAncho() / 2), ALTO_MAPA - 40);

				txtTurno.imprimir();
				txtHabilidad.imprimir();
			}
		}

		else {
			if (Niveles.mago.vida > 0) {
				recuperarRecursos();
			}
			procesarFadeOut(background);
			if (fadeOutListo) {
				juego.setScreen(Niveles.nivelActual);				
			}
		}
		
		Util.batch.end();
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
	public void dispose() {

		super.dispose();
		background.dibujar();
		enemigo.liberarMemoria();
		hudEnemigo.liberarMemoria();
		hudMago.liberarMemoria();
		cartas.liberarMemoria();
		sfxMuerteEnemigo.dispose();
		sfxTomarPocion.dispose();
	}

	public void procesarEntrada() {

		/* CONSUMO ITEMS */
		int numItem = controlador.itemElegido - 1;
		if ((controlador.consumirItem) && (Niveles.mago.inventario.pociones[numItem] != null)) {
			Niveles.mago.consumirPocion(numItem);
			Util.repSonido(sfxTomarPocion);
		}

		/* ELEGIR HABILIDADES */
		int numHab = controlador.habilidadElegida;
		if (controlador.elegirHabilidad) {
			if (Niveles.mago.getHabilidades()[numHab].getConsumoMana() <= Niveles.mago.mana) {
				Niveles.mago.atacar(enemigo, numHab);
				turnoUsuario = false;
				txtHabilidad.cambiarTexto(
						"HAS EFECTUADO " + Niveles.mago.getHabilidades()[numHab].getNombre().toUpperCase());
			} else {
				txtHabilidad.cambiarTexto("MANA INSUFICIENTE! ELIJA OTRA HABILIDAD!");
			}
		}
	}

	private void posicionarMago(boolean enemigo) {
		if (!enemigo) {
			Niveles.mago.setPosicion((Util.ANCHO_MAPA_MUNDO / 6), ALTO_MAPA / 2 - 75);
		} else {
			this.enemigo.setPosicion((Util.ANCHO_MAPA_MUNDO - (Util.ANCHO_MAPA_MUNDO / 4)), ALTO_MAPA / 2 - 75);
		}
	}

	private void recuperarRecursos() {
		Niveles.mago.vida = (Niveles.mago.vida < Niveles.mago.vidaMax / 2) ? (Niveles.mago.vidaMax / 2)
				: Niveles.mago.vida;
		Niveles.mago.mana = (Niveles.mago.mana < Niveles.mago.getManaMax() / 2) ? (Niveles.mago.getManaMax() / 2)
				: Niveles.mago.mana;
	}
}
