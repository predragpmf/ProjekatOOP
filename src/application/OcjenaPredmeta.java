package application;

import java.util.ArrayList;

public class OcjenaPredmeta {
	private int id, ocjena;
	private Ucenik ucenik;
	private PredmetUSkoli predmetUSkoli;
	private Pitanje pitanje;
	public static ArrayList<OcjenaPredmeta> sveOcjenePredmeta = new ArrayList<>();
	
	public OcjenaPredmeta(int id, int ucenikId, int predmetUSkoliId, int pitanjeId, int ocjena) {
		this.id = id;
		this.ucenik = Ucenik.getUcenik(ucenikId);
		this.predmetUSkoli = PredmetUSkoli.getPredmetUSkoli(predmetUSkoliId);
		this.pitanje = Pitanje.getPitanje(pitanjeId);
		this.ocjena = ocjena;
		sveOcjenePredmeta.add(this);
	}

	public int getId() {
		return id;
	}

	public int getOcjena() {
		return ocjena;
	}

	public Ucenik getUcenik() {
		return ucenik;
	}

	public PredmetUSkoli getPredmetUSkoli() {
		return predmetUSkoli;
	}

	public Pitanje getPitanje() {
		return pitanje;
	}
	
}
