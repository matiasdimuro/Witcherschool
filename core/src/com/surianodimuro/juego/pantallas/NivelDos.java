package com.surianodimuro.juego.pantallas;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.surianodimuro.juego.Niveles;
import com.surianodimuro.juego.magos.MagoElemental;
import com.surianodimuro.juego.utiles.Util;

public final class NivelDos extends PantallaNivel {

	public NivelDos(Game juego) {

		super(juego);
				
		map = mapLoader.load("mapas/Mapa1.tmx");
		mapRenderer = new OrthogonalTiledMapRenderer(map);

		puertas = new RectangleMapObject[2];

		cargarCapaColision();
		cargarCapaEscencias();
		cargarCapaCofres();
		cargarCapaPuertas();
		
		magosSecundarios = new MagoElemental[2];
		for (int i = 0; i < cofres.length; i++) { magosSecundarios[i] = Util.generarMago(); }
		magosSecundarios[0].setPosicion(puertas[0].getRectangle().x + 15, puertas[0].getRectangle().y - 5);
		magosSecundarios[1].setPosicion(puertas[1].getRectangle().x + 15, puertas[1].getRectangle().y - 5);
	
		enemigos = new boolean[magosSecundarios.length];
		asignarEnemigos();
		
		numNivel = 2;
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
			magosSecundarios[0].dibujarMago(true);			
		}
		if (magosSecundarios[1].vida > 0) {
			magosSecundarios[1].dibujarMago(false);
		}
		if (mago.vida > 0) {
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

	/* ====== PASAR ENTRE PANTALLAS ======*/

	@Override
	protected void pasarNivel() {
		Niveles.nivelFinal = new NivelFinal(juego);
//		Niveles.nivelDos = null;
		juego.setScreen(Niveles.nivelFinal);
	}
}
