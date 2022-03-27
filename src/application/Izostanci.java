package application;

import java.util.ArrayList;

public class Izostanci {
	private int id;
	private String datum;
	private Ucenik ucenik;
	private PredmetUSkoli predmetUSkoli;
	public static ArrayList<Izostanci> sviIzostanci = new ArrayList<>();
	
	public Izostanci(int id, int ucenikId, int predmetUSkoliId, String datum) {
		this.id = id;
		this.ucenik = Ucenik.getUcenik(ucenikId);
		this.predmetUSkoli = PredmetUSkoli.getPredmetUSkoli(predmetUSkoliId);
		this.datum = datum;
		sviIzostanci.add(this);
		this.ucenik.sviIzostanci.add(this);
	}
	
	public String getDatum() {
		return datum;
	}
	
	public PredmetUSkoli getPredmetUSkoli() {
		return predmetUSkoli;
	}
	
	public Ucenik getUcenik() {
		return ucenik;
	}
	
	public int getId() {
		return id;
	}
	
}
