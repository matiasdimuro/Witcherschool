package com.surianodimuro.juego.pantallas;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.surianodimuro.juego.Niveles;
import com.surianodimuro.juego.entradas.ControladorMago;
import com.surianodimuro.juego.entradas.EntradasAcciones;
import com.surianodimuro.juego.escencias.Elixir;
import com.surianodimuro.juego.escencias.Escencia;
import com.surianodimuro.juego.escencias.Mana;
import com.surianodimuro.juego.hud.HudMapa;
import com.surianodimuro.juego.magos.MagoElemental;
import com.surianodimuro.juego.objetos.Cofre;
import com.surianodimuro.juego.utiles.Texto;
import com.surianodimuro.juego.utiles.Util;

public abstract class PantallaNivel extends Pantalla {

	protected TmxMapLoader mapLoader;
	protected TiledMap map;
	protected OrthogonalTiledMapRenderer mapRenderer;

	protected MapLayer capaColision;
	protected MapObjects objetosColision;

	protected MapLayer capaPuertas;
	protected RectangleMapObject[] puertas;

	protected MapLayer capaEscencias;
	protected Escencia[] escencias;

	protected MapLayer capaCofres;
	protected Cofre[] cofres;

	protected ControladorMago controlador;
	protected InputProcessor entradasAcciones;

	protected final float ANCHO_MAPA;
	protected final float ALTO_MAPA;

	protected MagoElemental mago;
	protected MagoElemental[] magosSecundarios;
	protected boolean[] enemigos;
	protected HudMapa hud;

	protected int numNivel;
	protected Texto txtNivel;
	protected Texto txtInteractuar;
	
	private Sound sfxAbrirCofre;
	private Sound sfxTomarPocion;
	private Sound sfxAgarrarEscencia;

	public PantallaNivel(Game juego) {

		super(juego);

		ANCHO_MAPA = Util.ANCHO_MAPA_MUNDO;
		ALTO_MAPA = Util.ALTO_MAPA_MUNDO;

		mapLoader = new TmxMapLoader();

		cam = new OrthographicCamera(ANCHO_MAPA, ALTO_MAPA);
		cam.setToOrtho(false, ANCHO_MAPA, ALTO_MAPA);

		viewport = new FitViewport(ANCHO_MAPA, ALTO_MAPA, cam);
		cam.update();
		
		sfxAbrirCofre = Gdx.audio.newSound(Gdx.files.internal("sfx/abrirCofre.mp3"));
		sfxTomarPocion = Gdx.audio.newSound(Gdx.files.internal("sfx/tomarPocion.mp3"));
		sfxAgarrarEscencia = Gdx.audio.newSound(Gdx.files.internal("sfx/agarrarEscencias.mp3"));
		
		mago = Niveles.mago;
		hud = new HudMapa(mago);
	}

	@Override
	public void show() {

		super.show();

		txtNivel = new Texto("fuentes/18cents.ttf", "Nivel " + ((numNivel == -1) ? "Final" : numNivel), 28,
				Color.WHITE);
		txtNivel.determinarPos(ANCHO_MAPA - 10 - txtNivel.getAncho(), ALTO_MAPA);

		txtInteractuar = new Texto("fuentes/18cents.ttf", "* Presione E para interactuar *", 17, Color.WHITE);
		txtInteractuar.determinarPos(ANCHO_MAPA / 2 - txtInteractuar.getAncho() / 2, ALTO_MAPA / 2 - 30);

		controlador = new ControladorMago();
		entradasAcciones = new EntradasAcciones(controlador);
		Gdx.input.setInputProcessor(entradasAcciones);

		mago.setPosicion(48, 18);
	}

	@Override
	public void render(float delta) {

		super.render(delta);

		if (mago.vida > 0) {

			Util.batch.setProjectionMatrix(cam.combined);
			cam.update();

			mapRenderer.setView(cam);
			mapRenderer.render();

			Util.batch.begin();
			txtNivel.imprimir();
			if ((mago.elixir >= mago.getCosteElixirEvol()) && (!mago.isEvolucionado())) {
				mago.evolucionar();
			}
			hud.mostrarElementos();
			mostrarEscencias();
			mostrarCofres();
			Util.batch.end();

			procesarEntrada();
		}

		else {
			juego.setScreen(new JuegoPerdido(juego));
		}
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

		map.dispose();
		mapRenderer.dispose();
		mago.liberarMemoria();
		hud.liberarMemoria();
		txtNivel.liberarMemoria();
		txtInteractuar.liberarMemoria();
		sfxAbrirCofre.dispose();
		sfxTomarPocion.dispose();
		sfxAgarrarEscencia.dispose();

		for (int i = 0; i < escencias.length; i++) {
			if (escencias[i] != null) {
				escencias[i].liberarMemoria();
			}
		}
		for (int i = 0; i < cofres.length; i++) {
			if (cofres[i] != null) {
				cofres[i].liberarMemoria();
			}
		}
		for (int i = 0; i < cofres.length; i++) {
			magosSecundarios[i].liberarMemoria();
		}
		for (int i = 0; i < magosSecundarios.length; i++) {
			magosSecundarios[i].liberarMemoria();
		}
	}

	/* ====== CAPAS TILED MAP ====== */

	protected void cargarCapaColision() {
		capaColision = map.getLayers().get("Colision");
		objetosColision = capaColision.getObjects();
	}

	protected void cargarCapaPuertas() {
		capaPuertas = map.getLayers().get("Puerta");
		for (int i = 0; i < puertas.length; i++) {
			puertas[i] = capaPuertas.getObjects().getByType(RectangleMapObject.class).get(i);
		}
	}

	protected void cargarCapaEscencias() {
		capaEscencias = map.getLayers().get("Escencia");
		escencias = new Escencia[capaEscencias.getObjects().getCount()];

		for (int i = 0; i < escencias.length; i++) {

			RectangleMapObject rect = capaEscencias.getObjects().getByType(RectangleMapObject.class).get(i);
			float x = rect.getRectangle().getX();
			float y = rect.getRectangle().getY();

			if (Util.rand.nextInt(4) == 3) {
				escencias[i] = new Elixir(x, y);
			} else {
				escencias[i] = new Mana(x, y);
			}
		}
	}

	protected void cargarCapaCofres() {
		capaCofres = map.getLayers().get("Cofre");
		cofres = new Cofre[capaCofres.getObjects().getCount()];

		for (int i = 0; i < cofres.length; i++) {
			RectangleMapObject rect = capaCofres.getObjects().getByType(RectangleMapObject.class).get(i);
			float x = rect.getRectangle().getX();
			float y = rect.getRectangle().getY();
			cofres[i] = new Cofre(x, y);
		}
	}

	/* ====== ENTRADA DEL USUARIO Y COMPOTAMIENTO DEL MAGO */

	protected void procesarEntrada() {

		/* MOVIMIENTO */
		if (controlador.derecha) {
			mago.moverDerecha();
			if (mago.verSiColisiona(objetosColision)) {
				mago.moverIzquierda();
			}
		}
		if (controlador.izquierda) {
			mago.moverIzquierda();
			if (mago.verSiColisiona(objetosColision)) {
				mago.moverDerecha();
			}
		}
		if (controlador.arriba) {
			mago.moverArriba();
			if (mago.verSiColisiona(objetosColision)) {
				mago.moverAbajo();
			}
		}
		if (controlador.abajo) {
			mago.moverAbajo();
			if (mago.verSiColisiona(objetosColision)) {
				mago.moverArriba();
			}
		}

		/* INTERACTUAR */
		int escenciaEnContacto = Niveles.mago.verSiInteractua(capaEscencias.getObjects());
		if ((escenciaEnContacto != -1) && (escencias[escenciaEnContacto] != null)) {
			Util.batch.begin();
			txtInteractuar.imprimir();
			Util.batch.end();
			if (controlador.interactuar) {
				mago.tomarEscencia(escencias[escenciaEnContacto]);
				Util.repSonido(sfxAgarrarEscencia);
				escencias[escenciaEnContacto] = null;
			}
		}
		int cofreEnContacto = Niveles.mago.verSiInteractua(capaCofres.getObjects());
		if ((cofreEnContacto != -1) && (cofres[cofreEnContacto] != null) && (cofres[cofreEnContacto].cerrado)) {
			Util.batch.begin();
			txtInteractuar.imprimir();
			Util.batch.end();
			if (controlador.interactuar) {
				mago.abrirCofre(cofres[cofreEnContacto]);
				Util.repSonido(sfxAbrirCofre);
			}
		}
		int puertaEnContacto = Niveles.mago.verSiInteractua(capaPuertas.getObjects());
		if (puertaEnContacto != -1) {
			Util.batch.begin();
			txtInteractuar.imprimir();
			Util.batch.end();
			if (controlador.interactuar) {
				if (magosSecundarios[puertaEnContacto].vida > 0) {
					if (enemigos[puertaEnContacto]) {
						juego.setScreen(new Batalla(juego, magosSecundarios[puertaEnContacto]));
					} else {
						pasarNivel();
					}
				} else {
					pasarNivel();
				}
			}
		}

		/* CONSUMO ITEMS */
		int numItem = controlador.itemElegido;
		if ((controlador.consumirItem) && (mago.inventario.pociones[numItem] != null)) {
			mago.consumirPocion(numItem);
			controlador.consumirItem = false;
			Util.repSonido(sfxTomarPocion);
		}
		
		/* MOSTRAR MENU DE "PAUSA" */
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			juego.setScreen(new Menu(juego));
		}
	}

	/* ====== MOSTRAR OBJETOS ====== */
	protected void mostrarEscencias() {
		for (int i = 0; i < escencias.length; i++) {
			if (escencias[i] != null) {
				escencias[i].dibujar();
			}
		}
	}

	protected void mostrarCofres() {
		for (int i = 0; i < cofres.length; i++) {
			cofres[i].dibujar();
		}
	}

	protected void mostrarMago() {

		if (!controlador.caminando) {
			mago.dibujarMago(controlador.mirandoDerecha);
		} else {
			if (controlador.mirandoDerecha) {
				mago.caminarDerecha();
			} else {
				mago.caminarIzquierda();
			}
		}
	}

	protected void asignarEnemigos() {

		if (enemigos.length == 1) {
			enemigos[0] = true;
		} else {
			int num = Util.rand.nextInt(enemigos.length);
			enemigos[num] = true;
		}
	}

	/* ====== PASAR ENTRE NIVELES ====== */
	protected void pasarNivel() {

	}
}
