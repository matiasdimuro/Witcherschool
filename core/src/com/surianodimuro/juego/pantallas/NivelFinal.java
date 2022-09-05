package com.surianodimuro.juego.pantallas;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.surianodimuro.juego.Niveles;
import com.surianodimuro.juego.magos.MagoElemental;
import com.surianodimuro.juego.magos.MagoMayor;
import com.surianodimuro.juego.utiles.Util;

public final class NivelFinal extends PantallaNivel {

	public NivelFinal(Game juego) {

		super(juego);

		map = mapLoader.load("mapas/Mapa2.tmx");
		mapRenderer = new OrthogonalTiledMapRenderer(map);

		puertas = new RectangleMapObject[1];

		cargarCapaColision();
		cargarCapaEscencias();
		cargarCapaCofres();
		cargarCapaPuertas();

		magosSecundarios = new MagoElemental[1];
		magosSecundarios[0] = new MagoMayor();
		magosSecundarios[0].setPosicion(puertas[0].getRectangle().x, puertas[0].getRectangle().y - 5);

		enemigos = new boolean[magosSecundarios.length];
		asignarEnemigos();
		
		numNivel = -1;
	}

	@Override
	public void show() {
		super.show();
		Niveles.nivelActual = this;
	}

	@Override
	public void render(float delta) {

		super.render(delta);

		Util.batch.begin();
		if (magosSecundarios[0].vida > 0) {
			magosSecundarios[0].dibujarMago(false);			
		}
		if (Niveles.mago.vida > 0) {
			mostrarMago();
		}
		mago.inventario.crearCasilleros();
		mago.inventario.dibujarItems();
		
		Util.batch.end();
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
	}

	/* ====== PASAR ENTRE PANTALLAS ====== */
	@Override
	public void pasarNivel() {
		juego.setScreen(new JuegoGanado(juego));
	}
}
