package application;

import java.util.ArrayList;

public class OcjenaPredmeta {
	private int id, ocjena;
	private Ucenik ucenikId;
	private PredmetUSkoli predmetUSkoliId;
	private Pitanje pitanjeId;
	public static ArrayList<OcjenaPredmeta> sveOcjenePredmeta = new ArrayList<>();
	
	public OcjenaPredmeta(int id, int predmetUSkoliId, int ucenikId, int pitanjeId, int ocjena) {
		this.id = id;
		this.predmetUSkoliId = PredmetUSkoli.getPredmetUSkoli(predmetUSkoliId);
		this.ucenikId = Ucenik.getUcenik(ucenikId);
		this.pitanjeId = Pitanje.getPitanje(pitanjeId);
		this.ocjena = ocjena;
		sveOcjenePredmeta.add(this);
	}
	
	
}
