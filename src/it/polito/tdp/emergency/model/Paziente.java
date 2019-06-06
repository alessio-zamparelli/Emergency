package it.polito.tdp.emergency.model;

import java.time.LocalTime;

public class Paziente {
	
	public enum StatoPaziente{
		NEW,
		WAITING_WHITE,
		WAITING_YELLOW,
		WAITING_RED,
		TREATING,
		OUT,
		BLACK,
	}
	
	private int id;
	private StatoPaziente stato;
	private LocalTime oraArrivo;
	
	public Paziente(int id, LocalTime oraArrivo) {
		this.id = id;
		this.oraArrivo = oraArrivo;
		this.stato = StatoPaziente.NEW;
	}

	public int getId() {
		return id;
	}

	public StatoPaziente getStato() {
		return stato;
	}

	public LocalTime getOraArrivo() {
		return oraArrivo;
	}

	public void setStato(StatoPaziente stato) {
		this.stato = stato;
	}

	@Override
	public String toString() {
		return "Paziente [id=" + id + ", stato=" + stato + ", oraArrivo=" + oraArrivo + "]";
	}

}
