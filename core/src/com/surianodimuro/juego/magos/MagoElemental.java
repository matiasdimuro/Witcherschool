package com.surianodimuro.juego.magos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.surianodimuro.juego.efectos.Efecto;
import com.surianodimuro.juego.escencias.Escencia;
import com.surianodimuro.juego.habilidades.Habilidad;
import com.surianodimuro.juego.habilidades.Punietazo;
import com.surianodimuro.juego.hud.Inventario;
import com.surianodimuro.juego.objetos.Cofre;
import com.surianodimuro.juego.pociones.Pocion;
import com.surianodimuro.juego.pociones.TipoPocion;
import com.surianodimuro.juego.utiles.Util;


public abstract class MagoElemental {

	/* ====== ATRIBUTOS ====== */
	public int vida;
	public int vidaMax;

	public int mana;
	protected int manaMax;

	public int elixir;
	private int costeElixirEvol;

	private int indicePoder;
	protected Elemento elemento;
	protected Elemento debilidad;
	private boolean evolucionado;

	public Inventario inventario;
	protected Habilidad[] habilidades;

	/* ====== GRAFICOS ====== */
	private String rutaSkin;
	private TextureRegion frameActual;

	private final int ANCHO = 38;
	private final int ALTO = 48;
	private Vector2 pos;

	/* ====== ANIMACIONES ====== */
	private Animation<TextureRegion> animCaminDerecha;
	private Animation<TextureRegion> animCaminIzquierda;
	protected TextureRegion[] framesCaminIzquierda;
	protected TextureRegion[] framesCaminDerecha;
	private float stateTime = 0;

	public MagoElemental(Elemento elemento, String rutaSkin) {

		this.elixir = 0;
		this.costeElixirEvol = 150;
		this.inventario = new Inventario();
		this.habilidades = new Habilidad[4];
		this.habilidades[0] = new Punietazo();
		this.indicePoder = 1;
//		this.evolucionado = false;
		this.rutaSkin = rutaSkin;
		pos = new Vector2();

		cargarSpriteSheet();
		animCaminDerecha = new Animation<TextureRegion>(0.1f, framesCaminDerecha);
		animCaminIzquierda = new Animation<TextureRegion>(0.1f, framesCaminIzquierda);
	}

	/* ====== COMPORTAMIENTO ====== */

	public void atacar(MagoElemental enemigo, int numHab) {

		Habilidad hab = habilidades[numHab];

		int valor = hab.getValorBase() * indicePoder;

		switch (hab.getEfecto()) {
		case OBTENER_VIDA:
			this.vida += valor;
			this.vidaMax = (this.vida > this.vidaMax) ? this.vida : this.vidaMax;
			break;

		case PERDER_VIDA:
			valor *= ((enemigo.debilidad != null) && (this.elemento == enemigo.debilidad)) ? 2 : 1;
			enemigo.vida -= valor;
			break;

		case OBTENER_MANA:
			this.mana += valor;
			this.manaMax = (this.mana > this.manaMax) ? this.mana : this.manaMax;
			break;

		default:
			break;
		}
		
		this.mana -= hab.getConsumoMana();

	}

	public void moverDerecha() {
		pos.x += 120 * Gdx.graphics.getDeltaTime();
	}

	public void moverIzquierda() {
		pos.x -= 120 * Gdx.graphics.getDeltaTime();
	}

	public void moverArriba() {
		pos.y += 120 * Gdx.graphics.getDeltaTime();
	}

	public void moverAbajo() {
		pos.y -= 120 * Gdx.graphics.getDeltaTime();
	}

	public void caminarIzquierda() {
		stateTime += Gdx.graphics.getDeltaTime();
		frameActual = animCaminIzquierda.getKeyFrame(stateTime, true);
		Util.batch.draw(new Sprite(frameActual), pos.x, pos.y);
	}

	public void caminarDerecha() {
		stateTime += Gdx.graphics.getDeltaTime();
		frameActual = animCaminDerecha.getKeyFrame(stateTime, true);
		Util.batch.draw(new Sprite(frameActual), pos.x, pos.y);
	}

	public void evolucionar() {
		this.indicePoder = 2;
		this.vidaMax *= 1.5;
		this.vida = this.vidaMax;
		this.manaMax += 700;
		this.mana = this.manaMax;
		this.evolucionado = true;
	}

	public void tomarEscencia(Escencia escencia) {

		if ((escencia.getEfecto() == Efecto.OBTENER_MANA)) {
			int nuevaMana = this.mana + escencia.getValor();
			if (nuevaMana > this.manaMax) {
				this.manaMax = nuevaMana;
			}
			this.mana = nuevaMana;
		}

		if ((escencia.getEfecto() == Efecto.OBTENER_ELIXIR)) {
			int nuevoElixir = this.elixir + escencia.getValor();
			nuevoElixir = (nuevoElixir >= this.costeElixirEvol) ? this.costeElixirEvol : nuevoElixir;
			this.elixir = nuevoElixir;
		}
	}

	public void abrirCofre(Cofre cofre) {

		cofre.abrir();
		if (cofre.getPocionOculta() != null) {
			this.inventario.agregarItem(cofre.getPocionOculta());
		}
	}

	public void consumirPocion(int numPocion) {

		Pocion pocion = this.inventario.pociones[numPocion];
//		int valor = Util.rand.nextInt(pocion.getValorMin(), pocion.getValorMax() + 1);
		int valor = Util.rand.nextInt(pocion.getValorMax() - pocion.getValorMin()) + pocion.getValorMin();
		
		if ((pocion.getTipo() == TipoPocion.VIDA)) {
			this.vida = ((this.vida + valor) > this.vidaMax) ? this.vidaMax : (this.vida + valor);
		}

		if ((pocion.getTipo() == TipoPocion.MANA)) {
			if ((this.mana + valor) > this.manaMax) {
				this.manaMax = this.mana + valor;
			}
			this.mana = this.mana + valor;
		}

		if ((pocion.getTipo() == TipoPocion.ELIXIR)) {
			this.elixir = ((this.elixir + valor) >= this.costeElixirEvol) ? this.costeElixirEvol : (this.elixir + valor);
		}

		if ((pocion.getTipo() == TipoPocion.MULTIJUGOS)) {
//			Efecto efecto = pocion.getEfectos()[Util.rand.nextInt(0, pocion.getEfectos().length)];
			Efecto efecto = pocion.getEfectos()[Util.rand.nextInt(pocion.getEfectos().length)];
			
			switch (efecto) {
			case OBTENER_VIDA:
				this.vida = ((this.vida + valor) > this.vidaMax) ? this.vidaMax : (this.vida + valor);
				break;
			case OBTENER_MANA:
				if ((this.mana + valor) > this.manaMax) {
					this.manaMax = this.mana + valor;
				}
				this.mana = this.mana + valor;
				break;
			case OBTENER_ELIXIR:
				this.elixir = ((this.elixir + valor) >= this.costeElixirEvol) ? this.costeElixirEvol
						: (this.elixir + valor);
				break;
			case PERDER_VIDA:
				this.vida -= valor;
				break;
			}
		}

		this.inventario.pociones[numPocion] = null;
		this.inventario.cantItems--;
		this.inventario.ordenar();
	}

	/* ====== GETTERS & SETTERS ====== */

	public int getManaMax() {
		return manaMax;
	}

	public Elemento getElemento() {
		return elemento;
	}

	public int getCosteElixirEvol() {
		return costeElixirEvol;
	}

	public void setStateTime() {
		this.stateTime = 0;
	}

	public void setPosicion(float x, float y) {
		this.pos.x = x;
		this.pos.y = y;
	}

	public Vector2 getPosicion() {
		return pos;
	}

	public Habilidad[] getHabilidades() {
		return habilidades;
	}
	
	public boolean isEvolucionado() {
		return this.evolucionado;
	}

	public int getMinCosteMana() {

		int posMin = 0;
		for (int i = 1; i < habilidades.length; i++) {
			posMin = (habilidades[i].getConsumoMana() < habilidades[posMin].getConsumoMana()) ? i : posMin;
		}
		return habilidades[posMin].getConsumoMana();
	}

	/* ====== OTROS ====== */

	private void cargarSpriteSheet() {

		TextureRegion[][] temp = TextureRegion.split(new Texture(rutaSkin), 190 / 5, 96 / 2);
		framesCaminDerecha = new TextureRegion[temp[0].length];
		framesCaminIzquierda = new TextureRegion[temp[0].length];

		int indice = 0;
		for (int i = 0; i < framesCaminDerecha.length; i++) {
			framesCaminDerecha[indice++] = temp[1][i];
		}

		indice = 0;
		for (int i = 0; i < framesCaminIzquierda.length; i++) {
			framesCaminIzquierda[indice++] = temp[0][i];
		}
	}

	public void dibujarMago(boolean mirandoDerecha) {

		if (mirandoDerecha) {
			frameActual = new TextureRegion(new Texture(rutaSkin), 0, ALTO, ANCHO, ALTO);
		} else {
			frameActual = new TextureRegion(new Texture(rutaSkin), 0, 0, ANCHO, ALTO);
		}
		Util.batch.draw(new Sprite(frameActual), pos.x, pos.y);
	}

	public Rectangle obtenerRectangulo() {
		return new Rectangle(pos.x + 1.5f, pos.y, ANCHO - 5, 3);
	}

	public boolean verSiColisiona(MapObjects objetosColision) {

		int i = 0;
		boolean colision = false;
		Array<RectangleMapObject> objetos = objetosColision.getByType(RectangleMapObject.class);

		do {
			Rectangle rectObj = objetos.get(i).getRectangle();
			if (Intersector.overlaps(rectObj, this.obtenerRectangulo())) {
				colision = true;
			}
		} while ((!colision) && (i++ < objetosColision.getCount() - 1));

		return colision;
	}

	public int verSiInteractua(MapObjects objects) {

		int i = 0;
		boolean colision = false;
		Array<RectangleMapObject> objetos = objects.getByType(RectangleMapObject.class);

		do {
			Rectangle rectObj = objetos.get(i).getRectangle();
			if (Intersector.overlaps(rectObj, this.obtenerRectangulo())) {
				colision = true;
			}
		} while ((!colision) && (i++ < objects.getCount() - 1));

		return (colision) ? i : -1;
	}

	public void liberarMemoria() {
		frameActual.getTexture().dispose();
		for (TextureRegion textureRegion : framesCaminDerecha) {
			textureRegion.getTexture().dispose();
		}
		for (TextureRegion textureRegion : framesCaminIzquierda) {
			textureRegion.getTexture().dispose();
		}
		inventario.liberarMemoria();
	}
}
