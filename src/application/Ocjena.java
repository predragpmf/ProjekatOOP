package application;

import java.util.ArrayList;
import java.util.Date;

public class Ocjena {
	private int id, ocjena;
	private Date datum;
	private Ucenik ucenik;
	private PredmetUSkoli predmetUSkoli;
	public static ArrayList<Ocjena> sveOcjene = new ArrayList<>();
	
	public Ocjena(int id, int ucenikId, int predmetUSkoliId, int ocjena, Date datum) {
		this.id = id;
		this.ucenik = Ucenik.getUcenik(ucenikId);
		this.predmetUSkoli = PredmetUSkoli.getPredmetUSkoli(predmetUSkoliId);
		this.ucenik.skola = predmetUSkoli.getSkola();
		this.ucenik.razred = predmetUSkoli.getPredmet().getRazred();
		this.ocjena = ocjena;
		this.datum = datum;
		sveOcjene.add(this);
	}
	
}
