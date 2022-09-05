package com.surianodimuro.juego.habilidades;

import com.surianodimuro.juego.efectos.Efecto;
import com.surianodimuro.juego.utiles.Imagen;
import com.surianodimuro.juego.utiles.Texto;

public abstract class Habilidad {

	private String nombre;
	private int valorBase;
	private int consumoMana;
	private Efecto efecto;

	protected Imagen carta;
	public Texto letra;

	/**
	 * Habilidad que posee un mago para efectuar durante las batallas.
	 * 
	 * @param nombre         Nombre de la habilidad.
	 * @param danioBase       Danio base de la habilidad.
	 * @param consumoEnergia Cuanta energía cuesta efectuar la habilidad.
	 */

	public Habilidad(String nombre, int danioBase, int consumoEnergia, Efecto efecto) {
		this.nombre = nombre;
		this.valorBase = danioBase;
		this.consumoMana = consumoEnergia;
		this.efecto = efecto;
	}

	/* METODOS UTILES */

	public void mostrarCarta() {
		carta.dibujar();
		letra.imprimir();
	}

	public void liberarMemoria() {
		carta.liberarMemoria();
	}

	/* GETTERS & SETTERS */

	public Imagen getCarta() {
		return carta;
	}

	public int getValorBase() {
		return valorBase;
	}

	public int getConsumoMana() {
		return consumoMana;
	}

	public String getNombre() {
		return nombre;
	}

	public Efecto getEfecto() {
		return efecto;
	}
}
