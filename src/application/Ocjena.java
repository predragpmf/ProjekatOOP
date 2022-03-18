package application;

import java.util.ArrayList;
import java.util.Date;

public class Ocjena {
	private int id, ocjena;
	private Date datum;
	private Ucenik ucenikId;
	private PredmetUSkoli predmetUSkoliId;
	public static ArrayList<Ocjena> sveOcjene = new ArrayList<>();
	
	public Ocjena(int id, int ucenikId, int predmetUSkoliId, int ocjena, Date datum) {
		this.id = id;
		this.ucenikId = Ucenik.getUcenik(id);
		this.predmetUSkoliId = PredmetUSkoli.getPredmetUSkoli(predmetUSkoliId);
		this.ocjena = ocjena;
		this.datum = datum;
		sveOcjene.add(this);
	}
	
}
